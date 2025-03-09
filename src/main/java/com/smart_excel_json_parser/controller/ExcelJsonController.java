package com.smart_excel_json_parser.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart_excel_json_parser.model.ExcelJsonResponse;
import com.smart_excel_json_parser.service.ExcelToJsonService;
import com.smart_excel_json_parser.service.JsonToExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.io.InputStream;
import java.io.IOException;

@Controller
public class ExcelJsonController {

    @Autowired
    private ExcelToJsonService excelToJsonService;

    @Autowired
    private JsonToExcelService jsonToExcelService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/upload-excel")
    public String uploadExcel(@RequestParam("file") MultipartFile file, Model model) {
        try {
            if (file.isEmpty()) {
                model.addAttribute("jsonData", "File is empty! Please upload a valid Excel file.");
                return "index";
            }

            // Check file type
            String contentType = file.getContentType();
            if (!"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(contentType) &&
                    !"application/vnd.ms-excel".equals(contentType)) {
                model.addAttribute("jsonData", "Invalid file type! Please upload a valid Excel file (.xlsx or .xls).");
                return "index";
            }

            InputStream inputStream = file.getInputStream();
            ExcelJsonResponse response = excelToJsonService.convertExcelToJson(inputStream);

            // Convert response to JSON string
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonData = objectMapper.writeValueAsString(response);

            model.addAttribute("jsonData", jsonData);
        } catch (Exception e) {
            model.addAttribute("jsonData", "Error processing file!");
        }
        return "index";
    }

    @PostMapping("/json-to-excel")
    public ResponseEntity<byte[]> jsonToExcel(@RequestParam("jsonInput") String jsonInput) {
        try {
            byte[] excelFile = jsonToExcelService.convertJsonToExcel(jsonInput);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=converted.xlsx");
            return new ResponseEntity<>(excelFile, headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
