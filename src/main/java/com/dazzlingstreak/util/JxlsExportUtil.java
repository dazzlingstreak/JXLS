package com.dazzlingstreak.util;

import com.alibaba.fastjson.JSON;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xls.XlsCommentAreaBuilder;
import org.jxls.builder.xml.XmlAreaBuilder;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.expression.JexlExpressionEvaluator;
import org.jxls.transform.Transformer;
import org.jxls.util.TransformerFactory;
import org.springframework.util.StringUtils;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * JxlsExportUtil：excel导出工具类
 */
public class JxlsExportUtil {

    private static final Logger logger = LogManager.getLogger(JxlsExportUtil.class);

    /**
     * 根据excel模板，结合xml的配置，将数据导出
     * @param templateStream excel模板文件流
     * @param configStream xml配置文件文件流
     * @param os
     * @param model
     * @throws IOException
     */
    public static void exportExcel(InputStream templateStream,InputStream configStream, OutputStream os, Map<String, Object> model) throws IOException {
        Context context = new Context(model);
        Transformer transformer = TransformerFactory.createTransformer(templateStream, os);

        //添加自定义函数供excel使用
        JexlExpressionEvaluator evaluator = (JexlExpressionEvaluator) transformer.getTransformationConfig().getExpressionEvaluator();
        Map<String,Object> funcs = new HashMap<>();
        funcs.put("utils",new JxlsExportUtil());
        evaluator.getJexlEngine().setFunctions(funcs);
        //根据xml的配置信息，构建区域信息
        AreaBuilder areaBuilder = new XmlAreaBuilder(configStream, transformer);
        List<Area> xlsAreaList = areaBuilder.build();
        //获取第一个区域块，将context中的数据进行填充后，输出到Sheet1页中
        Area xlsArea = xlsAreaList.get(0);
        xlsArea.applyAt(new CellRef("Sheet1!A1"), context);
        xlsArea.processFormulas();
        xlsArea.reset();
        transformer.write();
    }

    /**
     * 根据excel模板，结合xml的配置，将数据导出
     * @param templatePath excel模板在resource中路径
     * @param configPath xml配置文件在resource中路径
     * @param os 输出文件流
     * @param model 数据模型
     * @throws IOException
     */
    public static void exportExcel(String templatePath,String configPath, OutputStream os, Map<String, Object> model) throws IOException {
        try(InputStream templateStream = JxlsExportUtil.class.getClassLoader().getResourceAsStream(templatePath);
            InputStream configStream = JxlsExportUtil.class.getClassLoader().getResourceAsStream(configPath);){
            exportExcel(templateStream,configStream,os,model);
        }
    }

    /**
     * 根据excel模板，结合xml的配置，将数据导出
     * @param templatePath excel模板在resource中路径
     * @param configPath xml配置文件在resource中路径
     * @param file 输出文件
     * @param model 数据模型
     * @throws IOException
     */
    public static void exportExcel(String templatePath, String configPath, File file, Map<String, Object> model) throws IOException {
        try(InputStream templateStream = JxlsExportUtil.class.getClassLoader().getResourceAsStream(templatePath);
            InputStream configStream = JxlsExportUtil.class.getClassLoader().getResourceAsStream(configPath);
            FileOutputStream outputStream = new FileOutputStream(file)){
            exportExcel(templateStream,configStream,outputStream,model);
        }
    }

    /**
     * 根据模板excel文件，模板中设置好xls markup（批注），导出到一个file文件中
     * @param templateStream 模板文件流
     * @param os
     * @param model
     * @throws IOException
     */
    public static void exportExcel(InputStream templateStream,OutputStream os,Map<String,Object> model) throws IOException{
        Context context = new Context(model);

        Transformer transformer = TransformerFactory.createTransformer(templateStream, os);

        //添加自定义函数
        JexlExpressionEvaluator evaluator = (JexlExpressionEvaluator) transformer.getTransformationConfig().getExpressionEvaluator();
        Map<String,Object> funcs = new HashMap<>();
        funcs.put("utils",new JxlsExportUtil());
        evaluator.getJexlEngine().setFunctions(funcs);
        //直接构建区域信息，需要：excel中已设置好批注信息
        AreaBuilder areaBuilder = new XlsCommentAreaBuilder(transformer);
        List<Area> xlsAreaList = areaBuilder.build();
        Area xlsArea = xlsAreaList.get(0);
        xlsArea.applyAt(new CellRef("Sheet1!A1"), context);
        transformer.write();
    }

    /**
     * 根据模板excel文件，模板中设置好xls markup（批注），导出到一个file文件中
     * @param templatePath 模板文件在resource中的路径
     * @param file
     * @param model
     * @throws IOException
     */
    public static void exportExcel(String templatePath,File file,Map<String,Object> model) throws IOException{
        try(InputStream is= JxlsExportUtil.class.getClassLoader().getResourceAsStream(templatePath);
            FileOutputStream os = new FileOutputStream(file)){
            exportExcel(is,os,model);
        }
    }

    //region Functions for excel

    /**
     * 日期格式化
     * @param date
     * @param format
     * @return
     */
    public String dateFormat(Date date, String format){
        if(Objects.isNull(date)){
            return "";
        }
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(date);
        }catch (Exception e){
            logger.warn("JxlsExportUtil.dateFormat error {}", JSON.toJSONString(date));
        }
        return "";
    }

    /**
     * 金额格式化，例如："###,###.00" 格式：123,456.00
     * @param value
     * @param format
     * @param unit
     * @return
     */
    public String decimalFormat(double value,String format,String unit){
        if(value > 0){
            DecimalFormat df   =   new DecimalFormat(format);
            return df.format(value)+unit;
        }
        return "0.00"+unit;
    }

    /**
     * 将excel中枚举值转换为相应的文案输出,例如(1,"[1:\"男\“,2:\"女\"]") ->男
     * 1 表示原值, 后面的"[...]"是文案对应, 想法是将后面的文案能转化为map,利用map.get(key)输出,
     * 但是map原json的string为：{1:"男",2:"女"},但是用jxls导出时，excel取值是用${model.property}取值的,所以花括号会影响解析,所以excel中用[]替代
     * 另外，后面的文案本来就是String，用双引号包含，所以里面的双引号需要进行转义
     * @param key 枚举值
     * @param valueMap 枚举值对应的文案
     * @return
     */
    public String getEnumValue(Integer key,String valueMap){
        if(!StringUtils.isEmpty(valueMap)){
            String replace = valueMap.replace("[", "{").replace("]", "}");
            Map<String, String> values = (Map<String, String>)(JSON.parse(replace));

            if(values.containsKey(key.toString())){
                return values.get(key.toString());
            }
        }
        return "";
    }


    //endregion
}
