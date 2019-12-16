package cn.dmx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *
 * @Author: 周乾
 * @Date: 2019/12/9 09:42
 * @Description: 用户的实体类
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private Integer userId;// 用户id
    private String userName; //用户姓名
    private String password; //用户密码
    private Integer status; //用户状态
    private String salt;  //加密盐值
    private Role role;  //角色实体
    private UserRole userrole; //用户角色实体

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserRole getUserrole() {
        return userrole;
    }

    public void setUserrole(UserRole userrole) {
        this.userrole = userrole;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", salt='" + salt + '\'' +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
