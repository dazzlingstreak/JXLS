package com.dazzlingstreak.util;

import com.dazzlingstreak.domain.Employee;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.*;

/**
 * Created by Administrator on 2017/6/6.
 */
public class JxlsExportUtilTest {

    @Test
    public void test() throws IOException {
        OutputStream outputStream = new FileOutputStream("D:\\export.xlsx");
        Map<String,Object> map = new HashMap<>();

        List<Employee> employees = new ArrayList();

        Employee employee1 = new Employee();
        employee1.setName("明教");
        employee1.setMarriage(true);
        employee1.setBirthday(Date.from(LocalDate.parse("2001-02-15").atStartOfDay().toInstant(ZoneOffset.UTC)));
        employee1.setPhone("18667048855");
        employees.add(employee1);

        Employee employee2 = new Employee();
        employee2.setName("藏剑");
        employee2.setMarriage(false);
        employee2.setBirthday(Date.from(LocalDate.parse("2000-01-14").atStartOfDay().toInstant(ZoneOffset.UTC)));
        employee2.setPhone("18667048855");
        employees.add(employee2);

        Employee employee3 = new Employee();
        employee3.setName("纯阳");
        employee3.setMarriage(false);
        employee3.setBirthday(Date.from(LocalDate.parse("1998-02-24").atStartOfDay().toInstant(ZoneOffset.UTC)));
        employee3.setPhone("18667048855");
        employees.add(employee3);

        map.put("employees",employees);
        JxlsExportUtil.exportExcelFile(map,"test-export-template.xlsx",outputStream);
    }

}
