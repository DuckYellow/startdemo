package com.example.startdemo.date;

import com.alibaba.excel.EasyExcel;
import com.btime.util.DateUtil;
import com.btime.util.XlsReader;
import com.example.startdemo.date.entity.ReviewDO;
import com.example.startdemo.date.entity.WorkflowExcelDO;
import com.google.gson.Gson;
import lombok.Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class PurchaseOrderDeal {

    private static Map<String, List<ReviewDO>> dateMap = new HashMap<>();

    private static List<WorkflowExcelDO> excelDOS = new ArrayList<>();

    private static Gson gson = new Gson();

    public static void main(String[] args) {
        readFile("/Users/xuweihang/Desktop/审批流元数据.csv");
        dealDate();
        EasyExcel.write("/Users/xuweihang/Desktop/审批流元数据处理结果.xlsx", WorkflowExcelDO.class).sheet("sheet1").doWrite(excelDOS);
    }

    private static void dealDate() {
        for (Map.Entry<String, List<ReviewDO>> stringListEntry : dateMap.entrySet()) {
            for (ReviewDO reviewDO : stringListEntry.getValue()) {
                if (reviewDO.getCreateTime() == null) {
                    System.out.println("error  " + gson.toJson(reviewDO));
                }
            }
            //排序升序
            Collections.sort(stringListEntry.getValue(), (o1, o2) -> {
                if (o1.getCreateTime().after(o2.getCreateTime())) {
                    return 1;
                } else if (o1.getCreateTime().equals(o2.getCreateTime())) {
                    return 0;
                }
                return -1;
            });

            int index = 0;
            WorkflowExcelDO excelDO = new WorkflowExcelDO();
            for (int i = 0; i < stringListEntry.getValue().size(); i++) {
                ReviewDO reviewDO = stringListEntry.getValue().get(i);
                Process process = new Process();
                if (index == 0) {
                    process.setParentNode(String.valueOf(i - 1));
                }
                if (index != 0) {
                    excelDO.setPurchaseOrderNo(reviewDO.getBussinessId());
                    excelDO.setCreateUid(reviewDO.getCreator());
                    excelDO.setCreateTime(DateUtil.getDateString(reviewDO.getCreateTime()));
                }
                process.setNode(String.valueOf(i));
                process.setOptUid(Long.valueOf(reviewDO.getCreator()));
                process.setPass(reviewDO.getPassFlag());
                process.setDate(DateUtil.getDateString(reviewDO.getCreateTime()));
                //直接拒绝 结束当前审批流
                switch (index) {
                    case 0:
                        excelDO.setWorkflow1(gson.toJson(process));
                        break;
                    case 1:
                        excelDO.setWorkflow2(gson.toJson(process));
                        break;
                    case 2:
                        excelDO.setWorkflow3(gson.toJson(process));
                        break;
                    case 3:
                        excelDO.setWorkflow4(gson.toJson(process));
                        break;
                    case 4:
                        excelDO.setWorkflow5(gson.toJson(process));
                        break;
                    case 5:
                        excelDO.setWorkflow6(gson.toJson(process));
                        break;
                    case 6:
                        excelDO.setWorkflow7(gson.toJson(process));
                        break;
                }
                index++;
                //拒绝了 重新开始
                if (reviewDO.getPassFlag() == 0) {
                    index = 0;
                    excelDOS.add(excelDO);
                    excelDO = new WorkflowExcelDO();
                }
            }

        }
    }

    private static void readFile(String filePath) {


        XlsReader reader = new XlsReader();
        List<ReviewDO> res = reader.load(filePath, ReviewDO.class);
        System.out.println("a");
    }

    @Data
    static class Process {
        private String parentNode;
        private String node;
        private Integer pass;
        private Long optUid;
        private String remark;
        private String date;
    }
}
