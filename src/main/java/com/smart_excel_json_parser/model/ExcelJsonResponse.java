package com.smart_excel_json_parser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelJsonResponse {
    private List<ExcelSheet> sheets;
}
