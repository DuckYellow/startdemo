package com.example.startdemo.date;

import com.alibaba.excel.EasyExcel;
import com.example.startdemo.date.entity.WorkflowExcelDO;

import java.util.List;

public class WriteDemo {

    private static void writeExcel(String filePath, List<WorkflowExcelDO> date) {
        try {
            EasyExcel.write(filePath, WorkflowExcelDO.class).sheet("学生列表").doWrite(date);
        } catch (Exception e) {

        }
    }
}
