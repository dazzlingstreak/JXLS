package com.dazzlingstreak.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReader;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/6.
 */
public class JxlsImportUtil {

    /**
     * 读取excel文件，根据配置文件，返回解析后的实体信息
     * @param dataStream 文件流
     * @param configXmlPath 配置文件路径
     * @param key 实体的标识名称
     * @param c  实体的class
     * @param <T>
     * @return 返回的实体信息
     * @throws Exception
     */
    public static<T> T importFromExcelFile(InputStream dataStream,String configXmlPath,String key,Class<T> c) throws Exception {
        InputStream configXmlStream = JxlsImportUtil.class.getClassLoader().getResourceAsStream(configXmlPath);
        XLSReader xlsReader = ReaderBuilder.buildFromXML(configXmlStream);
        Map<String,T> map = new HashMap<>();
        T t = c.newInstance();
        map.put(key,t);
        xlsReader.read(dataStream,map);
        return t;
    }
}
