package com.smart_excel_json_parser.service;

import com.smart_excel_json_parser.model.ExcelJsonResponse;
import com.smart_excel_json_parser.model.ExcelRow;
import com.smart_excel_json_parser.model.ExcelSheet;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;

@Service
public class ExcelToJsonService {

    public ExcelJsonResponse convertExcelToJson(InputStream excelFile) {
        try {
            Workbook workbook = new XSSFWorkbook(excelFile);
            List<ExcelSheet> excelSheets = new ArrayList<>();

            for (Sheet sheet : workbook) {
                List<ExcelRow> rows = new ArrayList<>();
                Iterator<Row> rowIterator = sheet.iterator();

                List<String> headers = new ArrayList<>();
                if (rowIterator.hasNext()) {
                    Row headerRow = rowIterator.next();
                    for (Cell cell : headerRow) {
                        headers.add(cell.getStringCellValue().trim());
                    }
                }

                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    Map<String, String> rowData = new LinkedHashMap<>();

                    for (int i = 0; i < headers.size(); i++) {
                        Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        rowData.put(headers.get(i), getCellValue(cell));
                    }

                    rows.add(new ExcelRow(rowData));
                }

                excelSheets.add(new ExcelSheet(sheet.getSheetName(), rows));
            }

            workbook.close();
            return new ExcelJsonResponse(excelSheets);

        } catch (Exception e) {
            throw new RuntimeException("Error reading Excel file: " + e.getMessage(), e);
        }
    }

    private String getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "";
        }
    }
}