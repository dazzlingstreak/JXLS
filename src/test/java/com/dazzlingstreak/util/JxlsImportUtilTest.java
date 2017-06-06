package com.dazzlingstreak.util;

import com.dazzlingstreak.util.domain.TestImportEntity;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/6/6.
 */
public class JxlsImportUtilTest {

    @Test
    public void testImport() throws  Exception{
        InputStream dataStream = JxlsImportUtilTest.class.getClassLoader().getResourceAsStream("dataImport.xlsx");
        TestImportEntity testImport = JxlsImportUtil.importFromExcelFile(dataStream,"test-import-config.xml", "testImport", TestImportEntity.class);
        System.out.println(testImport);
    }

}
