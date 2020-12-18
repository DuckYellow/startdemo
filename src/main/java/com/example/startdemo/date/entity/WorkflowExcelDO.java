package com.example.startdemo.date.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class WorkflowExcelDO {

    @ExcelProperty(index = 0, value = {"itemCode"})
    private String purchaseOrderNo;

    @ExcelProperty(index = 1, value = {"发起人"})
    private String createUid;

    @ExcelProperty(index = 2, value = {"发起时间"})
    private String createTime;

    @ExcelProperty(index = 3, value = {"审核流程1"})
    private String workflow1;

    @ExcelProperty(index = 4, value = {"审核流程2"})
    private String workflow2;

    @ExcelProperty(index = 5, value = {"审核流程3"})
    private String workflow3;

    @ExcelProperty(index = 6, value = {"审核流程4"})
    private String workflow4;

    @ExcelProperty(index = 7, value = {"审核流程5"})
    private String workflow5;

    @ExcelProperty(index = 8, value = {"审核流程6"})
    private String workflow6;

    @ExcelProperty(index = 9, value = {"审核流程7"})
    private String workflow7;
}
