package cn.dmx.server;

import cn.dmx.entity.Role;

import java.util.List;

public interface RoleService {
    /**
     * 通过用户id查询角色集合
     *
     * @param userId
     * @return
     */
    List<Role> selectRoleByUserId(Integer userId);

    //通过用户id查询所有角色
    List<Role> queryRole();
}
