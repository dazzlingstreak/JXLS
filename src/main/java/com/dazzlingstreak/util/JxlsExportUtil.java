package com.dazzlingstreak.util;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * JxlsExportUtil：excel导出工具类
 */
public class JxlsExportUtil {

    /**
     * 导出execl
     * @param dataMap 数据
     * @param templatePath 模板在resources下的路径
     * @param outputStream 输出的流
     * @throws IOException
     */
    public static void exportExcelFile(Map<String,?> dataMap,String templatePath, OutputStream outputStream) throws IOException {
        InputStream templateStream = JxlsExportUtil.class.getClassLoader().getResourceAsStream(templatePath);
        Context context = new Context();
        for(Map.Entry<String,?> entry:dataMap.entrySet()){
            context.putVar(entry.getKey(),entry.getValue());
        }
        JxlsHelper.getInstance().processTemplate(templateStream,outputStream,context);
    }
}
