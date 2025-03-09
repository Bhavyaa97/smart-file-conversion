package com.smart_excel_json_parser.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

@Service
public class JsonToExcelService {

    public byte[] convertJsonToExcel(String jsonInput) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonInput);

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            for (JsonNode sheetNode : rootNode.get("sheets")) {
                String sheetName = sheetNode.get("sheetName").asText();
                Sheet sheet = workbook.createSheet(sheetName);
                int rowIndex = 0;

                // Get headers from the first row's "rowData" dynamically
                JsonNode firstRowData = sheetNode.get("rows").get(0).get("rowData");
                Iterator<Map.Entry<String, JsonNode>> fields = firstRowData.fields();

                // Create header row dynamically from the keys of the first rowData
                Row headerRow = sheet.createRow(rowIndex++);
                int cellIndex = 0;
                while (fields.hasNext()) {
                    Map.Entry<String, JsonNode> field = fields.next();
                    headerRow.createCell(cellIndex++).setCellValue(field.getKey());
                }

                // Iterate through the rows and add the corresponding data to the sheet
                for (JsonNode rowNode : sheetNode.get("rows")) {
                    Row row = sheet.createRow(rowIndex++);
                    cellIndex = 0;

                    // Access the rowData field
                    JsonNode rowDataNode = rowNode.get("rowData");

                    // Check if rowData exists and is an object
                    if (rowDataNode != null && rowDataNode.isObject()) {
                        // Write data to Excel
                        Iterator<Map.Entry<String, JsonNode>> rowFields = rowDataNode.fields();
                        while (rowFields.hasNext()) {
                            Map.Entry<String, JsonNode> field = rowFields.next();
                            row.createCell(cellIndex++).setCellValue(field.getValue().asText());
                        }
                    }
                }
            }
            workbook.write(outputStream);
            return outputStream.toByteArray();
        }
    }
}
