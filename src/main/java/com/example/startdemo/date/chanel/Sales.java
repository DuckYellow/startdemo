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
public class Sales {

    @XmlElement(name = "BO")
    private Bo bo = new Bo();

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Bo {
        @XmlElement(name = "AdmInfo")
        private AdmInfo admInfo = new AdmInfo();

        @XmlElement(name = "Documents")
        private Documents documents = new Documents();

        @XmlElement(name = "Document_Lines")
        private DocumentLines documentLines = new DocumentLines();

        @XmlElement(name = "ADDONINFO")
        private AddOnInfo addOnInfo = new AddOnInfo();
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Documents {
        @XmlElement(name = "row")
        private DocumentsRow row = new DocumentsRow();
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
        private String object = "13";
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
    public static class DocumentsRow {
        @XmlElement(name = "DocType")
        private String docType = "dDocument_Items";
        @XmlElement(name = "HandWritten")
        private String handWritte = "tNO";
        /**
         * Posting Sales Date (Format : yyyymmdd)
         */
        @XmlElement(name = "DocDate")
        private String docDate = "20200601";
        /**
         * Posting Sales Date (Format : yyyymmdd)
         */
        @XmlElement(name = "DocDueDate")
        private String docDueDate = "20200601";
        @XmlElement(name = "CardCode")
        private String cardCode = "D13";

        /**
         * Posting Sales Date (Format : yyyymmdd)
         */
        @XmlElement(name = "Comments")
        private String comments = "100009685";
        /**
         * Posting Sales Date (Format : yyyymmdd)
         */
        @XmlElement(name = "JournalMemo")
        private String journalMemo = "100009685";
        @XmlElement(name = "PaymentGroupCode")
        private String paymentGroupCode = "-1";
        @XmlElement(name = "SalesPersonCode")
        private String salesPersonCode = "-1";
        @XmlElement(name = "Confirmed")
        private String confirmed = "tYES";
        @XmlElement(name = "ImportFileNum")
        private String importFileNum = "0";
        @XmlElement(name = "SummeryType")
        private String summeryType = "dNoSummary";
        @XmlElement(name = "ContactPersonCode")
        private String contactPersonCode = "0";
        @XmlElement(name = "ShowSCN")
        private String showScn = "tNO";
        /**
         * Posting Sales Date (Format : yyyymmdd)
         */
        @XmlElement(name = "TaxDate")
        private String taxDate = "20200601";
        @XmlElement(name = "PartialSupply")
        private String partialSupply = "tYES";
        @XmlElement(name = "DocObjectCode")
        private String docObjectCode = "13";
        @XmlElement(name = "DiscountPercent")
        private String discountPercent = "0.000000";
        @XmlElement(name = "RevisionPo")
        private String revisionPo = "tNO";
        @XmlElement(name = "BlockDunning")
        private String blockDunning = "tNO";
        @XmlElement(name = "Pick")
        private String pick = "tNO";
        @XmlElement(name = "PaymentBlock")
        private String paymentBlock = "tNO";
        @XmlElement(name = "MaximumCashDiscount")
        private String maximumCashDiscount = "tNO";
        @XmlElement(name = "WareHouseUpdateType")
        private String wareHouseUpdateType = "dwh_Stock";
        @XmlElement(name = "Rounding")
        private String rounding = "tNO";
        @XmlElement(name = "DeferredTax")
        private String deferredTax = "tNO";
        @XmlElement(name = "DocumentSubType")
        private String documentSubType = "bod_None";
        @XmlElement(name = "UseShpdGoodsAct")
        private String useShpdGoodsAct = "tNO";
        @XmlElement(name = "IsPayToBank")
        private String isPayToBank = "tNO";
        @XmlElement(name = "DownPayment")
        private String downPayment = "0.000000";
        @XmlElement(name = "SequenceModel")
        private String sequenceModel = "0";
        @XmlElement(name = "UseCorrectionVATGroup")
        private String useCorrectionVATGroup = "tNO";
        /**
         * Reference Shopline Sales Number
         */
        @XmlElement(name = "U_ST_REF")
        private String uStRef = "100009685";
        /**
         * AWB No
         */
        @XmlElement(name = "AWB")
        private String awb = "800000011";
        @XmlElement(name = "AWBType")
        private String awbType = "JNE";
        @XmlElement(name = "InsurancePrice")
        private String insurancePrice = "0";
        /**
         * Customer Name
         */
        @XmlElement(name = "Name")
        private String name = "Iwan";
        /**
         * Customer Address - Street
         */
        @XmlElement(name = "Destination")
        private String destination = "Jl Jauh Dekat No 10";
        /**
         * Customer Phone Number
         */
        @XmlElement(name = "Phone")
        private String phone = "123123123";
        /**
         * Customer Address - Postal Code
         */
        @XmlElement(name = "PostalCode")
        private String postalCode = "222111";
        /**
         * Customer Address - Province
         */
        @XmlElement(name = "Province")
        private String province = "DKI Jakarta";
        /**
         * Customer Address - City
         */
        @XmlElement(name = "City")
        private String city = "Jakarta Selatan";
        /**
         * Customer Address - District
         */
        @XmlElement(name = "District")
        private String district = "Setiabudi";
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class DocumentsLinesRow {
        /**
         * LineNumber (start from zero )
         */
        @XmlElement(name = "LineNum")
        private String lineNum = "0";
        /**
         * ItemCode
         */
        @XmlElement(name = "ItemCode")
        private String itemCode = "647554276.5";
        /**
         * Quantity Sales
         */
        @XmlElement(name = "Quantity")
        private String quantity = "1.0";
        @XmlElement(name = "Price")
        private String price = "0.000000";
        @XmlElement(name = "PriceAfterVAT")
        private String priceAfterVat = "0.000000";
        @XmlElement(name = "VendorNum")
        private String vendorNum = "RUBINA";
        @XmlElement(name = "WarehouseCode")
        private String warehouseCode = "D13";
        @XmlElement(name = "SalesPersonCode")
        private String salesPersonCode = "SLSCFBOS";
        @XmlElement(name = "CommisionPercent")
        private String commisionPercent = "0.000000";
        @XmlElement(name = "TreeType")
        private String treeType = "iNotATree";
        @XmlElement(name = "UseBaseUnits")
        private String useBaseUnits = "tNO";
        @XmlElement(name = "BarCode")
        private String barCode = "0";
        @XmlElement(name = "VatGroup")
        private String vatGroup = "OO-10";
        @XmlElement(name = "BaseType")
        private String baseType = "-1";
        @XmlElement(name = "TaxType")
        private String taxType = "tt_Yes";
        @XmlElement(name = "TaxLiable")
        private String taxLiable = "tYES";
        @XmlElement(name = "CorrectionInvoiceItem")
        private String correctionInvoiceItem = "ciis_ShouldBe";
        @XmlElement(name = "WTLiable")
        private String wtLiable = "tNO";
        @XmlElement(name = "DeferredTax")
        private String deferredTax = "tNO";
        /**
         * Qty * (Unit Price (Include tax))
         */
        @XmlElement(name = "LineTotal")
        private String lineTotal = "2080000.00";
        @XmlElement(name = "ConsumerSalesForecast")
        private String consumerSalesForecast = "tNO";
        @XmlElement(name = "DistributeExpense")
        private String distributeExpense = "tNO";
        @XmlElement(name = "TaxOnly")
        private String taxOnly = "tNO";
        /**
         * Unit Price (Include tax)
         */
        @XmlElement(name = "UnitPrice")
        private String unitPrice = "2080000";
        @XmlElement(name = "Currency")
        private String currency = "IDR";
        @XmlElement(name = "Rate")
        private String rate = "1.0";
        @XmlElement(name = "LineStatus")
        private String lineStatus = "bost_Open";
        @XmlElement(name = "LineType")
        private String lineType = "dlt_Regular";
        /**
         * Posting Sales Date (Format : yyyymmdd)
         */
        @XmlElement(name = "ActualDeliveryDate")
        private String actualDeliveryDate = "20200601";
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
