package com.dazzlingstreak.util;

import com.dazzlingstreak.domain.Employee;
import com.dazzlingstreak.domain.Employer;
import com.dazzlingstreak.domain.Spouse;
import com.dazzlingstreak.enums.GenderEnum;
import com.dazzlingstreak.enums.MarriageEnum;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * JxlsExportUtilTest
 * @author huangdawei
 */
public class JxlsExportUtilTest {

    @Test
    public void testExportExcel() throws IOException {
        Employer employer = new Employer();
        employer.setName("Employer");
        employer.setPhone("Employer-phone");
        employer.setIdCard("Employer-idcard");
        employer.setBirthday(parseStringToDate("1999-10-01","yyyy-MM-dd"));
        employer.setGender(GenderEnum.MALE.getCode());
//        employer.setMarriage(MarriageEnum.UNMARRIED.getCode());  //非已婚情况【隐藏】配偶信息
        employer.setMarriage(MarriageEnum.MARRIED.getCode()); //已婚情况【显示】配偶信息
        Spouse spouse = new Spouse("Employer-spouse","Employer-spouse-idcard","Employer-spouse-phone");
        employer.setSpouse(spouse);

        List<Employee> employeeList = new ArrayList<>();
        Employee employee1 = new Employee();
        employee1.setName("Employee-01");
        employee1.setIdCard("idcard-01");
        employee1.setPhone("phone-01");
        employee1.setSalary(5000.59);

        Employee employee2 = new Employee();
        employee2.setName("Employee-02");
        employee2.setIdCard("idcard-02");
        employee2.setPhone("phone-02");
        employee2.setSalary(3000.19);

        Employee employee3 = new Employee();
        employee3.setName("Employee-03");
        employee3.setIdCard("idcard-03");
        employee3.setPhone("phone-03");
        employee3.setSalary(3000);

        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);

        Map<String,Object> model = new HashMap<>();
        model.put("employer",employer);
        model.put("employees",employeeList);

        //采用临时文件作为输出路径,路径为：C:\Users\Administrator\AppData\Local\Temp
        File exportFile = File.createTempFile("EmployInfoExport",".xlsx");
        JxlsExportUtil.exportExcel("META-INF/雇佣情况表.xlsx","META-INF/雇佣情况表.xml",exportFile,model);
    }

    @Test
    public void testExportExcelWithoutXml() throws IOException {
        Employer employer = new Employer();
        employer.setName("Employer");
        employer.setPhone("Employer-phone");
        employer.setIdCard("Employer-idcard");
        employer.setBirthday(parseStringToDate("1999-10-01","yyyy-MM-dd"));
        employer.setGender(GenderEnum.MALE.getCode());
        employer.setMarriage(MarriageEnum.UNMARRIED.getCode());  //非已婚情况【隐藏】配偶信息
//        employer.setMarriage(MarriageEnum.MARRIED.getCode()); //已婚情况【显示】配偶信息
        Spouse spouse = new Spouse("Employer-spouse","Employer-spouse-idcard","Employer-spouse-phone");
        employer.setSpouse(spouse);

        List<Employee> employeeList = new ArrayList<>();
        Employee employee1 = new Employee();
        employee1.setName("Employee-01");
        employee1.setIdCard("idcard-01");
        employee1.setPhone("phone-01");
        employee1.setSalary(5000.59);

        Employee employee2 = new Employee();
        employee2.setName("Employee-02");
        employee2.setIdCard("idcard-02");
        employee2.setPhone("phone-02");
        employee2.setSalary(3000.19);

        Employee employee3 = new Employee();
        employee3.setName("Employee-03");
        employee3.setIdCard("idcard-03");
        employee3.setPhone("phone-03");
        employee3.setSalary(3000);

        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);

        Map<String,Object> model = new HashMap<>();
        model.put("employer",employer);
        model.put("employees",employeeList);

        //采用临时文件作为输出路径,路径为：C:\Users\Administrator\AppData\Local\Temp
        File exportFile = File.createTempFile("EmployInfoExport",".xlsx");
        JxlsExportUtil.exportExcel("META-INF/雇佣情况表_excel_markup.xlsx",exportFile,model);

//       remark:此处列表中的数据序号问题，需要找方案解决下，推荐使用xml配置的方案进行导出，灵活性和扩展性高
    }


    private Date parseStringToDate(String dateString,String format){
        Instant instant = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(format)).atStartOfDay().toInstant(ZoneOffset.UTC);
        return Date.from(instant);
    }
}
