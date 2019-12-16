package cn.dmx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataType {
      private Integer did;   //类别字典id
      private String typeCode;  //类型编码
      private String typeName;  //类型名称
      private Integer valueId;  //值id
      private String valueName;  //类型值名称


}
