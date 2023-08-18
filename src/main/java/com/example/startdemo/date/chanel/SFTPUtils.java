package com.example.startdemo.date.chanel;

/**
 * @author 徐炜航
 * @version 1.0
 * @date 2023-03-27 15:46
 * @mail xuweihang@joyy.sg
 */

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SFTPUtils {
    private static final String HOST = "34.101.131.89";
    private static final int PORT = 22;
    private static final String USERNAME = "shopline";
    // private static final String PWD = "YKMXQW7KdXwAf*To";
    private static final String privateKey = "/Users/xuweihang/Desktop/shoplineTimeTest.key";

    private static final String Inbound = "/home/shopline/Inbound/";
    private static final String Outbound = "/home/shopline/Outbound/";
    private static final String downPath = "/Users/xuweihang/Desktop/";

    public static void main(String[] args) throws JSchException {

        JSch jsch = new JSch();
        jsch.addIdentity(privateKey);
        Session session = null;

        session = jsch.getSession(USERNAME, HOST, PORT);
        session.setConfig("StrictHostKeyChecking", "no");

        session.connect();

        ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
        channel.connect();
        System.out.println("连接成功");

//            // 上传文件
//            // 设置传输模式为二进制
//            File localFile = new File(downPath + "API-21058594164386923876462107_20230323200224.SALES");
//
//            channel.put(new FileInputStream(localFile), Inbound + localFile.getName());
//            System.out.println("File uploaded successfully.");
//
//
//            // 获取指定目录的文件信息
//            Vector<ChannelSftp.LsEntry> files = channel.ls(Outbound);
//            for (ChannelSftp.LsEntry file : files) {
//                if (!file.getAttrs().isDir()) {
//                    System.out.println("文件名：" + file.getFilename() + ", 大小：" + file.getAttrs().getSize() + " bytes");
//                }
//            }

//            // 下载文件
//            String name = "D13_Inventory_Movement_2023_03_03_140607.xml";
//            OutputStream outputStream = new FileOutputStream(downPath + name);
//            channel.get(Outbound + name, outputStream);
//            System.out.println("文件下载成功！");

        channel.disconnect();
        session.disconnect();

    }
}

