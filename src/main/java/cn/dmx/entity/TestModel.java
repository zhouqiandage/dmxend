package cn.dmx.entity;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.util.Date;

public class TestModel extends BaseRowModel {

    @ExcelProperty(value = "用户ID",index = 0)
    private String usId;
    @ExcelProperty(value = "用户名字",index = 1)
    private String userName;
    @ExcelProperty(value = "用户性别",index = 2)
    private String userSex;
    @ExcelProperty(value = "用户角色",index = 3)
    private String userRole;
    @ExcelProperty(value = "创建时间",index = 4)
    private Date createDate;

    @Override
    public String toString() {
        return "TestModel{" +
                "usId='" + usId + '\'' +
                ", userName='" + userName + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userRole='" + userRole + '\'' +
                ", createDate=" + createDate +
                '}';
    }

    public String getUsId() {
        return usId;
    }

    public void setUsId(String usId) {
        this.usId = usId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {

        this.createDate = createDate;
    }
}
