package com.example.startdemo.date.chanel;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author 徐炜航
 * @version 1.0
 * @date 2023-03-10 09:33
 * @mail xuweihang@joyy.sg
 */
@Data
@XmlRootElement(name = "Report")
@XmlAccessorType(XmlAccessType.FIELD)
public class Inventory {

    @XmlElement(name = "Tablix1")
    private Tablix1 tablix1;

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Tablix1 {
        @XmlElement(name = "Details_Collection")
        private DetailsCollection detailsCollections;
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class DetailsCollection {
        @XmlElement(name = "Details")
        private List<Detail> details;
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Detail {
        @XmlAttribute(name = "ItemCode")
        private String itemCode;

        @XmlAttribute(name = "ItemName")
        private String itemName;

        @XmlAttribute(name = "Qty")
        private String qty;
    }
}
