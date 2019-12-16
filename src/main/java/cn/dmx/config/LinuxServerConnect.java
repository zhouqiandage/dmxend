package cn.dmx.config;

import ch.ethz.ssh2.Connection;

import java.io.IOException;

public class LinuxServerConnect {

    public static Connection conn;

    /**
     * 连接Linux服务器
     * @param ip ip地址
     * @param port 端口号 一般为22
     * @param user 登录用户名
     * @param password 登录密码
     * @return 登录成功返回true，失败返回false
     */
    public static boolean isAuthenticateWithPassword(String ip,int port,String user,String password){
        try {
            conn = new Connection(ip,port);
            conn.connect();
            return conn.authenticateWithPassword(user,password);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


}
