package cn.dmx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 周乾
 * @Date: 2019/6/28 09:51
 * @Description: 角色权限的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rolepermission {
    private Integer id;//角色权限id
    private Integer rid; //角色id
    private Integer pid; //权限id


}
