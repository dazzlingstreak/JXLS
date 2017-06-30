Jxls-2.4.0 
===============

官方信息备注
----------------
官网路径：http://jxls.sourceforge.net/<br/>
官方Demo：https://bitbucket.org/leonate/jxls-demo

项目案例简要说明
----------------
1、模板化配置导出，缩短开发时间，不采用POI方式。<br/>
2、支持条件化显示excel中部分栏目。<br/>
3、支持自定义函数，供excel中调用，进行格式化输出，以及枚举转换。<br/>
4、利用xml配置化，可以指定采用哪个sheet页的模板，指定输出到哪个sheet页。 参照：单元测试 JxlsExportUtilTest/testExportExcel<br/>
5、简单的导出，可以不用xml配置，直接用excel批注的方式导出。参照：单元测试 JxlsExportUtilTest/testExportExcelWithoutXml<br/>

建议：
1、模板页设置为隐藏，这样导出的excel中，不会有个空的模板sheet页。<br/>
2、列表数据绑定，建议使用xml配置，序号采用excel的公式就行。例如：=TEXT(ROW()-ROW(A10),"00")<br/>
3、数据展示需要格式化的情况，优化考虑excel是否本身就能实现。<br/>
