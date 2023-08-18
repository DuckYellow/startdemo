package com.example.startdemo.date.chanel;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Author: Yilia
 * @Date: 2022/5/6/0006 14:42
 */

public class FtpUtils {
    protected final static Logger logger = Logger.getLogger(FtpUtils.class);

    private static final String HOST = "34.101.131.89";
    private static final int PORT = 21;
    private static final String name = "shopline";
    private static final String pwd = "YKMXQW7KdXwAf*To";
    private static final String testUrl = "/Users/xuweihang/Downloads/";
    private static final String Inbound = "/Inbound";
    private static final String Outbound = "/Outbound";
    private static final String downPath = "/Users/xuweihang/Desktop/";

    public static void main(String[] args) throws Exception {

        Inventory inventory = (Inventory) xmlToBean(downPath + "111copy.xml", Inventory.class);
        System.out.println(inventory.toString());
        //FTPClient ftpClientc = getFTPClient();
        //
//        downloadFileOption(Inbound, downPath);
//        String salefile = salefile();
//        uploadFile(new File(salefile), Inbound);
//
        //       String mopfile = mopfile();
//        uploadFile(new File(mopfile), Inbound);


    }

    /**
     * 这里主要是忽略xml文件的命名空间
     * @param file
     * @return
     * @throws FileNotFoundException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    protected static SAXSource newSAxSource(File file) throws FileNotFoundException, ParserConfigurationException, SAXException {
        FileReader fileReader = new FileReader(file);
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        saxParserFactory.setNamespaceAware(false);
        XMLReader xmlReader = saxParserFactory.newSAXParser().getXMLReader();
        SAXSource saxSource = new SAXSource(xmlReader, new InputSource(fileReader));
        return saxSource;
    }

    public static String mopfile() throws Exception {
        Mop mop = new Mop();
        //创建存储目录
        File file = new File(downPath);
        if (!(file.exists() && file.isDirectory())) {
            file.mkdirs();
        }
        String fileName = "API-100009685_20200601000000" + ".MOP";
        beanToXml(mop, downPath + File.separator + fileName);
        return downPath + File.separator + fileName;
    }

    /**
     * XML转换为javabean
     * Jul 25, 2013 9:39:05 PM xuejiangtao添加此方法
     *
     * @return
     * @throws JAXBException
     */
    public static Object xmlToBean(String xmlPath, Class<?> clazz) throws Exception {
        Object xmlObject = null;
        String content = new String(Files.readAllBytes(Paths.get(xmlPath)));
        System.out.println(content);
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            SAXSource saxSource = newSAxSource(new File(xmlPath));
            xmlObject = unmarshaller.unmarshal(saxSource);
        } catch (Exception e) {

            e.printStackTrace();

        }
        return xmlObject;
    }

    /**
     * javabean转换为XML
     * Jul 25, 2013 9:39:09 PM xuejiangtao添加此方法
     *
     * @return
     * @throws JAXBException
     * @throws FileNotFoundException
     */
    public static <T> StringWriter beanToXml(T t, String filePath) throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(t.getClass());
        Marshaller m = context.createMarshaller();
        StringWriter sw = new StringWriter();
        m.marshal(t, sw);
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);//是否格式化
        m.marshal(t, new FileOutputStream(filePath));
        return sw;
    }


    public static String salefile() throws Exception {
        Sales sales = new Sales();
        System.out.println(sales.toString());
        //创建存储目录
        File file = new File(downPath);
        if (!(file.exists() && file.isDirectory())) {
            file.mkdirs();
        }
        String fileName = "API-100009685_20200601000000" + ".SALES";
        file = new File(downPath + File.separator + fileName);

        // 将封装的document转为xml
        JAXBContext context = JAXBContext.newInstance(Sales.class);
        Marshaller marshaller = context.createMarshaller();
        //格式化输入
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(sales, file);
        return downPath + File.separator + fileName;
    }

    /**
     * 创建ftp连接
     *
     * @return
     * @throws IOException
     */
    public static FTPClient getFTPClient() throws IOException {
        //创建ftp客户端
        FTPClient ftpClient = new FTPClient();
        ftpClient.setControlEncoding("UTF-8");
        //链接ftp服务器
        ftpClient.connect(HOST, PORT);
        // 修改连接模式 服务器需要使用被动模式
        ftpClient.enterLocalPassiveMode();
        //登录ftp
        ftpClient.login("shopline", "YKMXQW7KdXwAf*To");
        ftpClient.setDefaultTimeout(20000);//毫秒
        //设置两个服务连接超时时间
        ftpClient.setConnectTimeout(10000);//毫秒
        //被动模式下设置数据传输的超时时间
        ftpClient.setDataTimeout(15000);//毫秒


        int reply = ftpClient.getReplyCode();
        //如果reply返回230就算成功了，如果返回530密码用户名错误或当前用户无权限下面有详细的解释。
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftpClient.disconnect();
            System.out.println("连接失败");
            return null;
        } else {
            System.out.println("连接成功");
            System.out.println("数量 " + ftpClient.listFiles().length);
            return ftpClient;
        }
    }

    /**
     * 关闭FTP方法
     *
     * @param ftp
     * @return
     */
    public static void closeFTP(FTPClient ftp) {
        try {
            ftp.logout();
        } catch (Exception e) {
            logger.error("FTP关闭失败");
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                    logger.error("FTP关闭失败");
                }
            }
        }
    }


    public static void uploadFile(File file, String toPath) throws IOException {
        FTPClient ftpClient = FtpUtils.getFTPClient();
        // 设置PassiveMode传输
        ftpClient.enterLocalPassiveMode();
        //设置二进制传输，使用BINARY_FILE_TYPE，ASC容易造成文件损坏
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        ftpClient.changeWorkingDirectory(toPath);

        boolean done = false;
        try (final InputStream input = new FileInputStream(file)) {
            // 设置上传到ftp上使用的文件名和路径
            String remote = toPath + "/" + file.getName();
            // 上传文件
            done = ftpClient.storeFile(remote, input);
        }
        FtpUtils.closeFTP(ftpClient);
        System.out.println("uploadFile success done=" + done);
    }


    /**
     * 文件下载
     *
     * @param fromPath 下载路径
     * @throws IOException
     */
    public static void downloadFileOption(String fromPath, String localPath) throws IOException {

        FTPClient ftpClient = FtpUtils.getFTPClient();
        // 设置PassiveMode传输
        ftpClient.enterLocalPassiveMode();
        ftpClient.changeWorkingDirectory(fromPath);
        for (FTPFile ftpFile : ftpClient.listFiles()) {
            File localFile = new File(localPath + "/" + ftpFile.getName());
            OutputStream is = null;
            File path = new File(fromPath);
            if (!((new File(localPath)).exists())) {
                path.mkdirs();
            }
            is = new FileOutputStream(localFile);
            boolean flag = ftpClient.retrieveFile(fromPath + "/" + ftpFile.getName(), is);
            if (flag) {
                System.out.println("下载成功！");
            } else {
                System.out.println("下载失败！");
            }
            if (ftpClient.getReplyCode() != 226) {
                System.out.println(ftpFile.getName() + "下载失败,失败原因:" + ftpClient.getReplyString());
                throw new RuntimeException(ftpFile.getName() + "下载失败,失败原因:" + ftpClient.getReplyString());
            }
        }


        FtpUtils.closeFTP(ftpClient);
    }
}