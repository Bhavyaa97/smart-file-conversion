package com.smart_excel_json_parser.controller;

import com.smart_excel_json_parser.service.ExcelToJsonService;
import com.smart_excel_json_parser.service.JsonToExcelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ExcelJsonControllerTest {

    @Mock
    private ExcelToJsonService excelToJsonService;

    @Mock
    private JsonToExcelService jsonToExcelService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ExcelJsonController())
                .build();
    }

    @Test
    void testUploadExcel_validFile() throws Exception {
        // Mock a valid file and response from ExcelToJsonService
        MultipartFile file = mock(MultipartFile.class);
        when(file.isEmpty()).thenReturn(false);

        // Use MockMvc to simulate file upload
        mockMvc.perform(multipart("/upload-excel").file("file", "Excel file content".getBytes()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("jsonData"));
    }

    @Test
    void testUploadExcel_invalidFile() throws Exception {
        // Simulate an invalid file
        mockMvc.perform(multipart("/upload-excel").file("file", "invalid content".getBytes()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("jsonData", "Error processing file!"));
    }

    @Test
    void testJsonToExcel() throws Exception {
        // Mock the response from JsonToExcelService
        String jsonInput = "{\"sheets\": [{\"sheetName\": \"Sheet1\", \"rows\": [{\"rowData\": {\"Name\": \"Alice\", \"Age\": \"30\", \"Gender\": \"Female\"}}]}]}";
        when(jsonToExcelService.convertJsonToExcel(anyString())).thenReturn("Excel content".getBytes());

        mockMvc.perform(post("/json-to-excel")
                        .param("jsonInput", jsonInput))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", "attachment; filename=converted.xlsx"));
    }
}
