package cn.dmx.server.impl;

import cn.dmx.entity.Role;
import cn.dmx.mapper.RoleMapper;
import cn.dmx.server.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> selectRoleByUserId(Integer userId) {
        return roleMapper.selectRoleByUserId(userId);
    }

    @Override
    public List<Role> queryRole() {
        return roleMapper.queryRole();
    }
}
