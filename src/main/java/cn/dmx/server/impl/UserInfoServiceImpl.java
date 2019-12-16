package cn.dmx.server.impl;

import cn.dmx.entity.UserBO;
import cn.dmx.entity.UserInfo;
import cn.dmx.entity.Userrole;
import cn.dmx.mapper.UserInfoMapper;
import cn.dmx.mapper.UserroleMapper;
import cn.dmx.server.UserInfoService;
import cn.dmx.utils.MD5Util;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import org.apache.commons.collections4.SplitMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;


    @Autowired
    private UserroleMapper userroleMapper;

    @Override
    public UserInfo loginUser(UserInfo userInfo) {
        return userInfoMapper.loginUser(userInfo);
    }

    @Override
    public UserInfo selectUserByUserName(String userName) {

        return userInfoMapper.selectUserByUserName(userName);
    }

    @Override
    public List<UserInfo> queryAllUser(String userName, Integer status) {
        return userInfoMapper.queryAllUser(userName, status);
    }


    @Override
    public UserInfo queryUser(Integer userId) {
        return userInfoMapper.queryUser(userId);
    }

    @Override
    public Integer updateUserStatus(Integer userId) {
        return userInfoMapper.updateUserStatus(userId);
    }

    @Override
    public Integer updateUserStatus2(Integer userId) {
        return userInfoMapper.updateUserStatus2(userId);
    }



    @Override
    public Integer updatePwd(UserInfo userInfo) {
        if(StringUtils.isEmpty(userInfo.getUserName())){
            return 0;
        }
        if(StringUtils.isEmpty(userInfo.getPassword())){
            return 0;
        }
        //密码加密
        String[] passwords = MD5Util.encryptPassword(userInfo.getUserName(), userInfo.getPassword());
        //盐值
        String salt=passwords[0];
        //新的密码
        String password=passwords[1];
        //重新set
        userInfo.setSalt(salt);
        userInfo.setPassword(password);
        return userInfoMapper.updatePwd(userInfo);
    }

    @Override
    @Transactional
    public Integer addUser(UserBO Userbo) {

        System.out.println(" bo++++++++++++"+Userbo);

        //先添加用户
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(Userbo.getUserName());
        //加密
        //密码加密
        String[] passwords = MD5Util.encryptPassword(Userbo.getUserName(), Userbo.getPassword());
        //盐值
        String salt=passwords[0];
        //新的密码
        String password=passwords[1];
        userInfo.setPassword(password);
        userInfo.setSalt(salt);
        userInfo.setStatus(1);
        userInfoMapper.addUser(userInfo);

        System.out.println("全部完成的"+userInfo);

        //已经插入成功的用户
        Integer userId = userInfo.getUserId();
        //创建用户跟角色的关联
        Userrole userrole = new Userrole();
        userrole.setRid(Userbo.getRid());
        userrole.setUid(userId);
        //添加数据

        System.out.println("userrole"+userrole);
        Integer result = userroleMapper.addUserrole(userrole);

        System.out.println("111111"+result);
        return result;
    }


}
