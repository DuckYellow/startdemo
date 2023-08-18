package com.example.startdemo.date.chanel;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author 徐炜航
 * @version 1.0
 * @date 2023-03-08 14:54
 * @mail xuweihang@joyy.sg
 */
@Data
@XmlRootElement(name = "BOM")
@XmlAccessorType(XmlAccessType.FIELD)
public class Mop {

    @XmlElement(name = "BO")
    private Bo bo = new Bo();

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Bo {
        @XmlElement(name = "AdmInfo")
        private AdmInfo admInfo = new AdmInfo();
        @XmlElement(name = "JournalEntries")
        private JournalEntries journalEntries = new JournalEntries();
        @XmlElement(name = "JournalEntries_Lines")
        private DocumentLines documentLines = new DocumentLines();
        @XmlElement(name = "ADDONINFO")
        private AddOnInfo addoninfo = new AddOnInfo();
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class JournalEntries {
        @XmlElement(name = "row")
        private JournalEntriesRow row = new JournalEntriesRow();
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class DocumentLines {
        @XmlElement(name = "row")
        private DocumentsLinesRow row = new DocumentsLinesRow();
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AdmInfo {
        @XmlElement(name = "Object")
        private String object = "30";
        @XmlElement(name = "Version")
        private String version = "2";
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AddOnInfo {
        @XmlElement(name = "TransType")
        private String transType = "A";
        @XmlElement(name = "ChangeTime")
        private String changeTime = "2020-06-01 20:29:55";
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class JournalEntriesRow {
        @XmlElement(name = "ReferenceDate")
        private String referenceDate = "20200601";
        @XmlElement(name = "Memo")
        private String memo = "100009685";
        @XmlElement(name = "TaxDate")
        private String taxDate = "20200601";
        @XmlElement(name = "UseAutoStorno")
        private String useAutoStorno = "tNO";
        @XmlElement(name = "StampTax")
        private String stampTax = "tNO";
        @XmlElement(name = "DueDate")
        private String dueDate = "20200601";
        @XmlElement(name = "AutoVAT")
        private String autoVAT = "tYes";
        @XmlElement(name = "ReportEU")
        private String reportEU = "tNO";
        @XmlElement(name = "Report347")
        private String report347 = "tNO";
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class DocumentsLinesRow {
        @XmlElement(name = "Debit")
        private String debit = "10000000.00";
        @XmlElement(name = "Credit")
        private String credit = "0000.000000";
        @XmlElement(name = "DueDate")
        private String dueDate = "20200601";
        /**
         * Payment Type_Boutique Code (Refer to appendix)
         */
        @XmlElement(name = "ShortName")
        private String shortName = "MNDVS_D13";
        @XmlElement(name = "LineMemo")
        private String lineMemo = "100009685";
        @XmlElement(name = "Reference2")
        private String reference2 = "100009685";
        @XmlElement(name = "ReferenceDate1")
        private String referenceDate1 = "20200601";
        @XmlElement(name = "Reference1")
        private String reference1 = "MNDVS";
        @XmlElement(name = "TaxDate")
        private String taxDate = "20200601";
        @XmlElement(name = "BaseSum")
        private String baseSum = "0.000000";
        @XmlElement(name = "VatLine")
        private String vatLine = "tNO";
        @XmlElement(name = "SystemBaseAmount")
        private String systemBaseAmount = "0.000000";
        @XmlElement(name = "VatAmount")
        private String vatAmount = "0.000000";
        @XmlElement(name = "SystemVatAmount")
        private String systemVatAmount = "0.000000";
        @XmlElement(name = "GrossValue")
        private String grossValue = "0.000000";
        @XmlElement(name = "CostingCode")
        private String costingCode = "API";
        @XmlElement(name = "CostingCode2")
        private String costingCode2 = "CFB";
        @XmlElement(name = "CostingCode3")
        private String costingCode3 = "CFBOS";
        @XmlElement(name = "CostingCode4")
        private String costingCode4 = "CHA";
    }
}
