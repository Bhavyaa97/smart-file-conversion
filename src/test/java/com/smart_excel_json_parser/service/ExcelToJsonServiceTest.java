package com.smart_excel_json_parser.service;

import com.smart_excel_json_parser.service.ExcelToJsonService;
import com.smart_excel_json_parser.model.ExcelJsonResponse;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ExcelToJsonServiceTest {

    private ExcelToJsonService excelToJsonService;

    @BeforeEach
    void setUp() {
        excelToJsonService = new ExcelToJsonService();
    }

    @Test
    void testConvertExcelToJson() throws IOException {
        // Load an example Excel file
        File file = ResourceUtils.getFile("classpath:test-excel.xlsx");
        FileInputStream inputStream = new FileInputStream(file);

        ExcelJsonResponse response = excelToJsonService.convertExcelToJson(inputStream);

        // Check that the response is not null and contains the expected data
        assertNotNull(response);
        assertTrue(response.getSheets().size() > 0);
        assertEquals("Sheet1", response.getSheets().get(0).getSheetName());
        assertNotNull(response.getSheets().get(0).getRows());
    }
}
