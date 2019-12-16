package cn.dmx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskLog {
    private Integer tid;  //日志id-
    private  String jobCode;//任务编号
    private String volumeLabel;//卷标
    private String discType;//光盘类型
    private String writingSpeed;//写入速度
    private String copyNumber;//刻印数量
    private String checkIsNo;//是否校验
    private String format;//格式
    private String coverId;//封面模板ID
    private String tlstatus;//状态--执行状态
    private String operationTime; //操作时间
    private String userId; //操作用户ID
}
