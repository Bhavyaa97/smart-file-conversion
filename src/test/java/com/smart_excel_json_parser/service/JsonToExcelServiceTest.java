package com.smart_excel_json_parser.service;

import com.smart_excel_json_parser.service.JsonToExcelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonToExcelServiceTest {

    private JsonToExcelService jsonToExcelService;

    @BeforeEach
    void setUp() {
        jsonToExcelService = new JsonToExcelService();
    }

    @Test
    void testConvertJsonToExcel() throws IOException {
        String jsonInput = "{ \"sheets\": [{ \"sheetName\": \"Sheet1\", \"rows\": [{ \"rowData\": {\"Name\": \"John\", \"Age\": \"30\"}}]}]}";
        byte[] result = jsonToExcelService.convertJsonToExcel(jsonInput);

        // Check that the result is not null and has data (ensure it's a valid Excel file)
        assertNotNull(result);
        assertTrue(result.length > 0);
    }
}
