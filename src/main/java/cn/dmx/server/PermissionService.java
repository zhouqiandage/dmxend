package cn.dmx.server;

import cn.dmx.entity.Permission;

import java.util.List;

public interface PermissionService {
    /**
     * 根据角色查询权限
     * @param roleId  角色id
     * @return 权限集合
     */
    List<Permission> queryPerByroleId(Integer rid);

}
