package com.example.startdemo.sf;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

/**
 * 商品
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "Request")
@XmlAccessorType(XmlAccessType.FIELD)
public class Request {


    @XmlAttribute(name = "service")
    private String service;

    @XmlAttribute(name = "lang")
    private String lang;

    @XmlElement(name = "Body")
    private SFRequestBody body;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class SFRequestBody {

        @XmlElement(name = "WaybillRoute")
        private WaybillRoute WaybillRoute;
    }

    public static class WaybillRoute {

        @XmlAttribute(name = "id")
        private String id;
        @XmlAttribute(name = "mailno")
        private String mailno;
        @XmlAttribute(name = "orderid")
        private String orderid;
        @XmlAttribute(name = "acceptTime")
        private String acceptTime;
        @XmlAttribute(name = "acceptAddress")
        private String acceptAddress;
        @XmlAttribute(name = "remark")
        private String remark;
        @XmlAttribute(name = "opCode")
        private String opCode;
    }
}
