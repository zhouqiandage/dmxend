package cn.dmx.mapper;

import cn.dmx.entity.UserBO;
import cn.dmx.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户mapper
 */
public interface UserInfoMapper {
    //登录信息
    UserInfo loginUser(UserInfo userInfo);

    //查询所有用户与角色
    List<UserInfo> queryAllUser(@Param("userName") String userName, @Param("status") Integer status);

    //根据用户名查询实体
    UserInfo selectUserByUserName(String userName);

    //查看个人信息
    UserInfo queryUser(Integer userId);

    //修改用户密码
    Integer updatePwd(UserInfo userInfo);

    //修改用户状态
    Integer updateUserStatus(Integer userId);

    //修改用户状态2
    Integer updateUserStatus2(Integer userId);

    //添加用户
    Integer addUser(UserInfo userInfo);





}
