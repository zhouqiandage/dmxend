package cn.dmx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 周乾
 * @Date: 2019/6/28 09:52
 * @Description: 用户角色的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    private Integer id; //用户角色id
    private Integer uid; //用户id
    private Integer rid; //角色id
}
