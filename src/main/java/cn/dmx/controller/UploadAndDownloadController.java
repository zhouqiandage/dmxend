package cn.dmx.controller;

import cn.dmx.entity.TestModel;
import cn.dmx.utils.EasyExcelUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 上传下载  导入导出
 */
@RequestMapping(value = "uadc")
@Controller
public class UploadAndDownloadController {
    //导入excel文件
    @RequestMapping(value = "excelImport", method = {RequestMethod.POST})
    @ResponseBody
    public List<Object> excelImport(HttpServletRequest request, Model model, @RequestParam("uploadFile") MultipartFile file) throws Exception {
        Map<String,Object> result = EasyExcelUtil.readExcel(file, new TestModel(),1);
        List<Object> list = (List<Object>) result.get("datas");
        if(list != null && list.size() > 0){
            for(Object o : list){
                TestModel xfxx = (TestModel) o;
                System.out.println(xfxx.getUsId()+"/"+xfxx.getUserName()+"/"+xfxx.getUserSex()+"/"+xfxx.getUserRole()+"/"+xfxx.getCreateDate());
            }
        }
        return list;
    }
}
