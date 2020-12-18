package com.example.startdemo.date;

import com.alibaba.excel.EasyExcel;
import com.btime.util.DateUtil;
import com.btime.util.StringUtil;
import com.example.startdemo.date.entity.ExcelUtil;
import com.example.startdemo.date.entity.ReviewDO;
import com.example.startdemo.date.entity.WorkflowExcelDO;
import com.google.gson.Gson;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.*;

public class PurchaseOrderDeal {

    private static Map<String, List<ReviewDO>> dateMap = new HashMap<>();

    private static List<WorkflowExcelDO> excelDOS = new ArrayList<>();

    private static Gson gson = new Gson();

    public static void main(String[] args) {
        readFile("/Users/xuweihang/Desktop/审批流元数据.xlsx");
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
                if (index > 0) {
                    process.setParentNode(String.valueOf(i - 1));
                }
                if (index == 0) {
                    excelDO.setPurchaseOrderNo(reviewDO.getBussinessId());
                    excelDO.setCreateUid(reviewDO.getCreator());
                    excelDO.setCreateTime(DateUtil.getDateTimeString(reviewDO.getCreateTime()));
                }
                process.setNode(String.valueOf(i));
                process.setOptUid(Long.valueOf(reviewDO.getCreator()));
                process.setPass(reviewDO.getPassFlag());
                process.setDate(DateUtil.getDateTimeString(reviewDO.getCreateTime()));
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
                if (i == stringListEntry.getValue().size() - 1) {
                    index = 0;
                    if (!StringUtils.isEmpty(excelDO.getPurchaseOrderNo())) {
                        excelDOS.add(excelDO);
                    }
                    excelDO = new WorkflowExcelDO();
                }
            }

        }
    }

    private static void readFile(String filePath) {
        List<Object> reviewDOS = ExcelUtil.readMoreThan1000Row(filePath);
        for (int i = 1; i < reviewDOS.size(); i++) {
            Object object = reviewDOS.get(i);
            List<String> list = (List<String>) object;

            ReviewDO reviewDO = new ReviewDO();
            reviewDO.setBussinessId(list.get(0));
            reviewDO.setPassFlag(Integer.valueOf(list.get(1)));

            reviewDO.setRemark(list.get(4));
            reviewDO.setCreator(list.get(5));
            reviewDO.setCreateTime(DateUtil.parseDate(list.get(6)));

            if (!dateMap.containsKey(reviewDO.getBussinessId())) {
                dateMap.put(reviewDO.getBussinessId(), new ArrayList<>());
            }
            dateMap.get(reviewDO.getBussinessId()).add(reviewDO);
        }
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
