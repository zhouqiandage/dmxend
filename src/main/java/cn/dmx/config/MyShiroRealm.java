package cn.dmx.config;


import cn.dmx.entity.Permission;
import cn.dmx.entity.Role;
import cn.dmx.entity.UserInfo;
import cn.dmx.server.PermissionService;
import cn.dmx.server.RoleService;
import cn.dmx.server.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: 张帅轲
 * @Date: 2019/6/29 12:43
 * @Description: shiro的授权和装配权限的类
 */

@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        //获取用户id
        int userid = userInfo.getUserId();
        System.out.println("userId" + userid);
        //这里可以进行授权和处理
        Set<String> roleSet = new HashSet<>();
        Set<String> permSet = new HashSet<>();
        System.out.println("分配完成准备存储的set");
        List<Role> roleList = roleService.selectRoleByUserId(userid);
        System.out.println("角色集合已经完成查询");
        for (Role role : roleList) {
            roleSet.add(role.getRoleName());
            //根究角色id 查询角色下所有的权限
            List<Permission> permissionList = permissionService.queryPerByroleId(role.getRid());
            //遍历权限 add到容器中
            for (Permission permission : permissionList) {
                permSet.add(permission.getPerms());
            }
        }
        //将查到的权限和角色分别传入authorizationInfo中
        authorizationInfo.setStringPermissions(permSet);
        authorizationInfo.setRoles(roleSet);
        return authorizationInfo;
    }


    /**
     * 身份认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        //获取用户输入的账号
        String username = (String) token.getPrincipal();
        System.out.println("用户输入的用户名" + username);
        //获取用户输入的密码
        System.out.println(token.getCredentials());
        //通过username从数据库中查找User对象，如果找到，没找到
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserInfo userInfo = userInfoService.selectUserByUserName(username);
        if (userInfo == null) {
            throw new AuthenticationException();
        }
        if (userInfo.getStatus() == 2) {
            throw new LockedAccountException();
        }
        // 进行认证，将正确数据给shiro处理
        // 密码不用自己比对，AuthenticationInfo认证信息对象，一个接口，new他的实现类对象SimpleAuthenticationInfo
        /*	第一个参数随便放，可以放user对象，程序可在任意位置获取 放入的对象
         *  第二个参数必须放密码，
         *  第三个参数放 当前realm的名字，因为可能有多个realm*/
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo.getUserName(), //用户名
                userInfo.getPassword(), //密码
                ByteSource.Util.bytes(userInfo.getSalt()),
                getName()
        );//realm name
        //清除之前的授权信息
        super.clearCachedAuthorizationInfo(authenticationInfo.getPrincipals());
        // 存入用户对象
        SecurityUtils.getSubject().getSession().setAttribute("userInfo", userInfo);
        // 返回给安全管理器，securityManager，由securityManager比对数据库查询出的密码和页面提交的密码
        // 如果有问题，向上抛异常，一直抛到控制器
        return authenticationInfo;
    }
}
