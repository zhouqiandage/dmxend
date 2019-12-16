package cn.dmx.server.impl;

import cn.dmx.entity.Permission;
import cn.dmx.mapper.PermissionMapper;
import cn.dmx.server.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public List<Permission> queryPerByroleId(Integer rid) {
        return permissionMapper.queryPerByroleId(rid);
    }
}
