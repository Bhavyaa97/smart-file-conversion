package com.smart_excel_json_parser.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExcelSheet {
    private String sheetName;
    private List<ExcelRow> rows;

}
