package cn.dmx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 周乾
 * @Date: 2019/6/28 09:47
 * @Description: 权限的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
    private Integer pid; //权限ID
    private String perms; //权限地址
    private String name;  //权限名称

    @Override
    public String toString() {
        return "Permission{" +
                "pid=" + pid +
                ", perms='" + perms + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
