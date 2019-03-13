package com.example.startdemo.FileOpera;

import com.google.common.base.Strings;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class test {
    public static Set<String> zhangdanSet = new HashSet<>();
    public static Set<String> kPaySet = new HashSet<>();

    public static void main(String[] args) throws Exception {
        String kikuu1 = "C:\\Users\\77982\\Desktop\\0000.csv";
        String kikuu2 = "C:\\Users\\77982\\Desktop\\1111.xlsx";
        readCSV(kikuu1);
        readExcel(kikuu2);
        Iterator<String> it = kPaySet.iterator();
        while (it.hasNext()) {
            String str = it.next();
            if(!zhangdanSet.contains(str)){
                System.out.println(str);
            }
        }
    }

    private static void readExcel(String str) throws Exception {
        File file = new File(str);
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
        Sheet sheet = workbook.getSheetAt(2);//读取第一个 sheet
        Row row = null;
        int count = sheet.getPhysicalNumberOfRows();
        //逐行处理 excel 数据
        for (int i = 2; i < count; i++) {
            row = sheet.getRow(i);
            Cell cell0 = row.getCell(0);
            zhangdanSet.add(cell0.toString().trim());
        }
    }

    private static void readCSV(String path) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(path));//换成你的文件名
        reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉
        String line = null;
        while ((line = reader.readLine()) != null) {
            String item[] = line.split("\",\"");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
            String orderNo = item[4];
            String type = item[6].substring(0, item[6].length() - 1);
            if ("3".equals(type) && !Strings.isNullOrEmpty(orderNo)) {
                kPaySet.add(orderNo);
            }
        }
    }

    private static void writeFile(List<String> result, String path) throws Exception {
        RandomAccessFile rf = new RandomAccessFile(path, "rw");
        for (String s : result) {
            rf.write((s + "\n").getBytes());
        }
        rf.close();
    }
}
