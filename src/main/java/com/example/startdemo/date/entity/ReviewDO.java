package com.example.startdemo.date.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ReviewDO {
    /**
     * 相关联的业务id
     */
    private String bussinessId;
    /**
     * 是否通过标志 0-不通过/1-通过
     */
    private Integer passFlag;
    /**
     * 审核前状态
     */
    private Integer preStatus;
    /**
     * 审核后状态
     */
    private Integer sufStatus;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建人
     */
    private String creator;

    private Date createTime;
}
