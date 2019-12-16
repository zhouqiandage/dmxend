package cn.dmx.mapper;

import cn.dmx.entity.Userrole;

public interface UserroleMapper {


    /**
     * 新增用户跟角色的关联
     * @param userrole 管理关联实体类
     * @return  rows
     */
    Integer addUserrole(Userrole userrole);

}
