package cn.dmx.server;

import cn.dmx.entity.UserBO;
import cn.dmx.entity.UserInfo;

import java.util.List;

public interface UserInfoService {
    //登录信息
    UserInfo loginUser(UserInfo userInfo);

    //根据用户名查询实体
    UserInfo selectUserByUserName(String userName);

    //查询所有用户与角色
    List<UserInfo> queryAllUser(String userName, Integer status);

    //查看个人信息
    UserInfo queryUser(Integer userId);

    //修改用户状态
    Integer updateUserStatus(Integer userId);

    //修改用户状态2
    Integer updateUserStatus2(Integer userId);

    // 管理员修改用户密码
    Integer updatePwd(UserInfo userInfo);


    //添加用户
    Integer addUser(UserBO Userbo);

}
