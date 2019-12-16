package cn.dmx.utils;

import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import cn.dmx.config.LinuxServerConnect;

import java.io.*;
import java.util.Calendar;

public class LinuxOperation {


    /**
     * 获取单个文件
     * @param ip ip
     * @param port 端口
     * @param user 账号
     * @param password 密码
     * @param file 远程文件绝对路径
     * @param localTargetDirecTory 下载到本地的盘符位置
     */
    public static void getSingleFile(String ip,int port,String user,String password,String file,String localTargetDirecTory){
        try {
            Long d1 =Calendar.getInstance().getTime().getTime();
            Boolean is = LinuxServerConnect.isAuthenticateWithPassword(ip, port, user, password);
            if (is == false)
                throw new IOException("Linux服务器连接失败");
            //建立SCP客户端
            SCPClient scpClient = LinuxServerConnect.conn.createSCPClient();
            //服务器端的文件下载到本地的目录下 file:远程文件绝对路径  localTargetDirectory:本地路径
            scpClient.get(file, localTargetDirecTory);
            Long d2 =Calendar.getInstance().getTime().getTime();
            Long m=(d2-d1)/1000;
            System.out.println("下载完成，总耗时==="+m+"秒");
            LinuxServerConnect.conn.close();
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    /**
     * 获取所有文件
     * @param ip ip
     * @param port 端口 一般为22
     * @param user 账号
     * @param password 密码
     * @param filePath 远程文件绝对路径
     * @param localTargetDirecTory 下载到本地的盘符位置
     */
    public static void getFileAll(String ip,int port,String user,String password,String filePath,String localTargetDirecTory){
        Boolean is = LinuxServerConnect.isAuthenticateWithPassword(ip, port, user, password);

        try {
            File folder = new File("G:\\opt");
            if(!folder.exists()){
                folder.mkdir();
            }
            if (is == false)
                throw new IOException("Linux服务器连接失败");
            SCPClient scpClient = LinuxServerConnect.conn.createSCPClient();
            Session session = LinuxServerConnect.conn.openSession();
            session.execCommand("ls "+filePath);  //进入目录
            //显示执行命令后的信息
            InputStream stdout = new StreamGobbler(session.getStdout());
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
            while (true)
            {
                Long d1 =Calendar.getInstance().getTime().getTime();
                String line = br.readLine();
                if (line == null)
                    break;
                System.out.println("远程服务器返回信息:" + line);
                scpClient.get("filePath"+line,localTargetDirecTory);
                Long d2 =Calendar.getInstance().getTime().getTime();
                Long m=(d2-d1)/1000;
                System.out.println("耗时："+m+"秒，下载完成："+line);
            }
            System.out.println("ExitCode: " + session.getExitStatus());
            //关闭远程连接
            session.close();
            LinuxServerConnect.conn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
