package com.reusableMethods;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Methods {

	// Reads Excel file and returns sheet
	public Sheet Read_Excel_Sheet(String fileName,String sheetName) throws Exception {
		File file =    new File(System.getProperty("user.dir")+"\\src\\app\\property\\files\\"+fileName);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook workbook = null;
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if(fileExtensionName.equals(".xlsx")){
			workbook = new XSSFWorkbook(inputStream);
		}
		else if(fileExtensionName.equals(".xls")){
			workbook = new HSSFWorkbook(inputStream);
		}

		Sheet sheet = workbook.getSheet(sheetName);
		return sheet;
	}

	// Returns Row Count
	public static int RowCount(Sheet sheet) {
		int rowCount = sheet.getLastRowNum()+1;
		return rowCount;
	}

	// Returns Column Count
	public static int ColumnCount(Sheet sheet) {
		int colCount = sheet.getRow(0).getLastCellNum();
		return colCount;
	}


	//Returns Column Values
	public String[] Read_Column(Sheet sheet, String columnName) {
		int rowCount = RowCount(sheet);
		int colCount = ColumnCount(sheet);
		String[] colValues = new String[rowCount-1];
		Row row = sheet.getRow(0);
		for(int i=0; i<colCount;i++) {
			Cell cell = row.getCell(i);
			String cellValue = cell.getStringCellValue();
			if(columnName.equalsIgnoreCase(cellValue)) {
				int colIndex = cell.getColumnIndex();
				for(int j=0;j<sheet.getLastRowNum();j++) {
					Row rowIterate = sheet.getRow(j+1);
					Cell cellFinal = rowIterate.getCell(colIndex);
					if(cellFinal!=null){
						switch (cellFinal.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							colValues[j] = (long)cellFinal.getNumericCellValue() + "";
							break;
						case Cell.CELL_TYPE_STRING:
							colValues[j] = cellFinal.getStringCellValue();
							break;
						case Cell.CELL_TYPE_BLANK:
							break;
						default:
							break;
						}
					}
				}
			}
		}
		return colValues;
	}

	// Returns specified cell data
	public String Read_Cell_Data(Sheet sheet, String data, String readColumnName, String cellColumnName) throws Exception {
		int colCount = ColumnCount(sheet);
		String cellValue = null;
		String[] dataColumn = Read_Column(sheet, readColumnName);
		for(int i=0;i<dataColumn.length;i++) {
			if(data.equalsIgnoreCase(dataColumn[i])) {
				Row colRow = sheet.getRow(0);
				for(int j=0; j<colCount; j++) {
					String colValue = colRow.getCell(j).getStringCellValue();
					if(cellColumnName.equalsIgnoreCase(colValue)) {
						Row actRow = sheet.getRow(i+1);
						Cell cell = actRow.getCell(j);
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							cellValue = (long)cell.getNumericCellValue() + "";
							break;
						case Cell.CELL_TYPE_STRING:
							cellValue = cell.getStringCellValue();
							break;
						}
					}
				}
			}
		}
		return cellValue;
	}

}
