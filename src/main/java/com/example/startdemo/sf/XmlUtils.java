package com.example.startdemo.sf;

import com.btime.util.StringUtil;
import org.springframework.util.StringUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * @author xuweihang@qbb.com
 * @date 2020-04-13 16:34
 */
public final class XmlUtils {

    /**
     * java对象转换为xml文件
     *
     * @param obj  对象数据
     * @param load java对象.Class
     * @return xml文件的String
     */
    public static String beanToXml(Object obj, Class<?> load) throws JAXBException {
        if (obj == null) {
            return null;
        }
        JAXBContext context = JAXBContext.newInstance(load);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        StringWriter writer = new StringWriter();
        marshaller.marshal(obj, writer);
        return writer.toString();
    }

    /**
     * xml文件配置转换为对象
     *
     * @param xml  xml数据
     * @param load java对象.Class
     * @return java对象
     */
    public static Object xmlToBean(String xml, Class<?> load) throws JAXBException {
        if (StringUtils.isEmpty(xml)) {
            return null;
        }
        JAXBContext context = JAXBContext.newInstance(load);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object object = unmarshaller.unmarshal(new StringReader(xml));
        return object;
    }
}
