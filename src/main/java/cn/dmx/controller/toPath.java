package cn.dmx.controller;

import cn.dmx.entity.UserInfo;
import cn.dmx.server.UserInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/toPath")
public class toPath {
    @Autowired
    private UserInfoService userInfoService;
   /* @Autowired
    private TbUserService tbUserService;*/


    @RequestMapping("login")
    public String toLogin(){
        return "/user/login";
    }


    @RequestMapping("welcome")
    public String loginOut(){
        return "/welcome";
    }
    //手动刻录
    @RequestMapping("toManualBurn")
    public String toManualBurn() {
        return "/burn/manual/manualBurn";
    }
    //远程抓取
    @RequestMapping("toRemote")
    public String toRemote() {
        return "/burn/remote/remote";
    }
    //光盘到光盘
    @RequestMapping("toDiscToDisc")
    public String toDiscToDisc() {
        return "/burn/manual/discToDisc";
    }
    //光盘到文件
    @RequestMapping("toDiscToFile")
    public String toDiscToFile() {
        return "/burn/manual/discToFile";
    }
    //光盘到ISO
    @RequestMapping("toDiscToISO")
    public String toDiscToISO() {
        return "/burn/manual/discToISO";
    }
    //文件到光盘
    @RequestMapping("toFileToDisc")
    public String toFileToDisc() {
        return "/burn/manual/fileToDisc";
    }
    //ISO到光盘
    @RequestMapping("toIsoToDisc")
    public String toIsoToDisc() {
        return "/burn/manual/isoToDisc";
    }

    //自动刻录
    @RequestMapping("autoBurn")
    public String toAutoBurn(){
        return "/burn/auto/autoBurn";
    }
    //批量刻录
    @RequestMapping("toBatchBurn")
    public String toBatchBurn(){
        return "/burn/batch/batch";
    }

    //查看所有用户
    @RequestMapping("toUserMain")
    public String toUserMain(Model model){





        return "/rbac-user-list";
    }
    //角色判断
    @RequiresPermissions("admin:queryUser")
    @RequestMapping("queryAllUser")
    public String queryAll(String userName,Integer status,Model model){
        System.out.println("-----------------------------");
       /* ConcurrentMap<String, Object> concurrentMap = new ConcurrentHashMap<String,Object>();
        //List<TbUser> tbUsers = tbUserService.queryAll();
        List<UserInfo> list=userInfoService.queryAllUser();
        concurrentMap.put("code", 0);
        concurrentMap.put("msg", "成功");
        concurrentMap.put("count", list.size());
        concurrentMap.put("data", list);
        for (UserInfo info : list) {
            System.out.println(info.getRole());
        }
        return JSON.toJSONString(concurrentMap);*/
        List<UserInfo> list=userInfoService.queryAllUser(userName,status);
        model.addAttribute("list",list);

       return "/user/user_list";
    }



}
