package cn.dmx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @Author: 周乾
 * @Date: 2019/12/9 09:42
 * @Description: 角色的实体类
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private Integer rid; //角色id
    private String roleName; //角色名称
    private String roleValue; //角色值
    private UserRole userrole;

    public UserRole getUserrole() {
        return userrole;
    }

    public void setUserrole(UserRole userrole) {
        this.userrole = userrole;
    }

    @Override
    public String toString() {
        return "Role{" +
                "rid=" + rid +
                ", roleName='" + roleName + '\'' +
                ", roleValue='" + roleValue + '\'' +
                '}';
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleValue() {
        return roleValue;
    }

    public void setRoleValue(String roleValue) {
        this.roleValue = roleValue;
    }
}
