package cn.dmx.controller;

import cn.dmx.entity.Role;
import cn.dmx.entity.UserBO;
import cn.dmx.entity.UserInfo;
import cn.dmx.server.RoleService;
import cn.dmx.server.UserInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: 周乾
 * @Date: 2019/12/9 11：08
 * @Description: 用户的控制层
 */
@Controller
@RequestMapping("user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private RoleService roleService;
    /**
     * 用户去登陆
     *
     * @param redirectUrl 登陆成功之后跳转那个页面
     * @return 登陆页面
     */
    @RequestMapping("tologin")
    public String tologin(String redirectUrl) {
        return "/user/login";
    }
    /**
     * 处理登陆
     * @param users   用户的对象
     * @param model
     * @param session session
     * @return 用户去的页面
     */
    @PostMapping("doLogin")
    public String dologin(UserInfo users, Model model, HttpSession session) {
        System.out.println(users);
        //进行身份验证
        try {
            //验证身份和登陆
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(users.getUserName(), users.getPassword());

            //验证成功进行登录操作
            subject.login(token);
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "用户不存在或者密码错误");
            return "user/login";
        } catch (LockedAccountException e) {
            model.addAttribute("msg", "登录失败，该用户已被冻结");
            return "user/login";
        } catch (AuthenticationException e) {
            model.addAttribute("msg", "该用户不存在");
            return "user/login";
        } catch (Exception e) {
            model.addAttribute("msg", "未知异常");
            return "user/login";
        }
        //拿出来session  进行比对他所属职位
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        return "/index";
    }
      //根据userid查看个人信息(去修改)
    @RequestMapping("queryUser")
    public String queryUser(Integer userId,Model model){
        UserInfo userInfo= userInfoService.queryUser(userId);
        model.addAttribute("info",userInfo);
        return "user/user_update";
    }
    @RequestMapping("toUpdateUser")
    public String toUpdateUser(){
        return "user/user_password";
    }

    //修改用户状态
    @ResponseBody
    @RequestMapping("updateUserState1")
    public String updateUserState1(Integer userId){
        int count=userInfoService.updateUserStatus(userId);
        if(count>0){
            return "ok";
        }else{
            return "error";
        }
    }
    @ResponseBody
    @RequestMapping("updateUserState2")
    public String updateUserState2(Integer userId){
        int count=userInfoService.updateUserStatus2(userId);
        if(count>0){
            return "ok";
        }else{
            return "error";
        }
    }
    //退出
    @ResponseBody
    @RequestMapping("logout")
    public String logout(){
        //登出清除缓存
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return "";
    }

    //去添加用户
    //根据角色id查询出来所有角色
    @RequestMapping("toAddUser")
    public String  toAddUser(Model model){
        List<Role> list=roleService.queryRole();
        model.addAttribute("rolelist",list);
        return "/user/user_add";
    }


    //做添加用户

    @ResponseBody
    @RequestMapping("doAddUser")
    public String addUser(UserBO userBO){
        Integer count =userInfoService.addUser(userBO);
        if(count>0){
            return "ok";
        }else{
            return "error";
        }
    }
    //去修改密码
    @RequestMapping("toUpdatePwd")
    public String toUpdatepwd(){
        return "/user/user_updatePwd";
    }

    //修改用户密码
    @ResponseBody
    @RequestMapping("doUpdatePwd")
    public String updatePwd(UserInfo userInfo){
        Integer count=userInfoService.updatePwd(userInfo);
        if(count>0){
            return "ok";
        }else{
            return "error";
        }
    }








}
