package testData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import base.TestBase;

public class TestData extends TestBase {
	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;

	private static DataFormatter formatter = new DataFormatter();

	public static void OpenExcelFile(String Path, String SheetName) throws Exception {

		try {

			// Open the Excel file
			logger.info("Open the Excel file");
			FileInputStream ExcelFile = new FileInputStream(Path);

			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);

			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			// ExcelWSheet = ExcelWBook.getSheetAt(0);

		} catch (Exception e) {
			throw (e);
		}
	}

	// This method is to read the test data from the Excel cell, in this we are
	// passing parameters as Row num and Col num
	public static String getCellData(int RowNum, int ColNum) throws Exception {

		try {

			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			String CellData = null;
			if (Cell.getCellType().equals(CellType.STRING)) {
				CellData = Cell.getStringCellValue();
			} else if (Cell.getCellType().equals(CellType.NUMERIC)) {

				if (HSSFDateUtil.isCellDateFormatted(Cell)) {
					CellData = String.valueOf(Cell.getDateCellValue());
				} else {
					CellData = NumberToTextConverter.toText(Cell.getNumericCellValue());
					// CellData= Integer.toString((int)Cell.getNumericCellValue());
				}
			}

			return CellData;

		} catch (Exception e) {

			return "";

		}
	}

	// This method is to read the test data from exact Excel ROW to end of file.
	public static ArrayList<String> getALLExcelData(int RowNum) throws Exception {

		logger.info("Get All data from Excel File");

		// To get the number of rows present in sheet
		int totalNoOfRows = ExcelWSheet.getLastRowNum();

		ArrayList<String> row = new ArrayList<String>();

		for (int i = RowNum; i <= totalNoOfRows; i++) {
			// get the total cell count in row[i]
			int cellcount = ExcelWSheet.getRow(i).getLastCellNum();
			for (int j = 0; j < cellcount; j++) {
				// get cell value at the given position [i][j]
				String value = formatter.formatCellValue(ExcelWSheet.getRow(i).getCell(j));
				row.add(value);
			}
		}
		return row;
	}

	// This method is to read the test data from exact Excel ROW.
	public static ArrayList<String> getRowData(int RowNum) throws Exception {

		logger.info("Get data from row in Excel File");
		Row = ExcelWSheet.getRow(RowNum);
		ArrayList<String> row = new ArrayList<String>();

		for (Cell cell : Row) {

			row.add(formatter.formatCellValue(cell));
		}
		return row;
	}

	// DataProvider TCS
	public static Object[][] fetchData(String TestCaseName, String sheetName) throws Exception

	{
		List <Integer> iTestCaseRows = new ArrayList<Integer>();
		List<Object[]> testObjList = new ArrayList<>();

		TestData.OpenExcelFile(System.getProperty("user.dir") + "\\Source\\TestData\\TestData.xlsx", sheetName);
		iTestCaseRows = TestData.getRowContains(TestCaseName, 0);
		for (Integer iTestCaseRow : iTestCaseRows) 
		{

			Object[][] testObjArray = TestData.getTableArray(
					System.getProperty("user.dir") + "\\Source\\TestData\\TestData.xlsx", sheetName, iTestCaseRow);
			for (Object[] row : testObjArray) {
				testObjList.add( row);

			}


		}
		Object[][] testObjArray2 = new Object[testObjList.size()][];
		for (int i=0;i< testObjList.size();i++) 
		{
			testObjArray2[i]= testObjList.get(i);

		}
		
		return (testObjArray2);

	}

	public static Object[][] getTableArray(String FilePath, String SheetName, int iTestCaseRow) throws Exception

	{

		String[][] tabArray = null;

		try {

			FileInputStream ExcelFile = new FileInputStream(FilePath);

			// Access the required test data sheet

			ExcelWBook = new XSSFWorkbook(ExcelFile);

			ExcelWSheet = ExcelWBook.getSheet(SheetName);

			int startCol = 1;

			int ci = 0, cj = 0;

			int totalRows = 1;

			int totalCols = ExcelWSheet.getRow(iTestCaseRow).getLastCellNum() - 1;

			tabArray = new String[totalRows][totalCols];

			for (int j = startCol; j <= totalCols; j++, cj++)

			{

				tabArray[ci][cj] = getCellData(iTestCaseRow, j);

			}

		}

		catch (FileNotFoundException e)

		{

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		}

		catch (IOException e)

		{

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		}

		return (tabArray);

	}

	public static List<Integer> getRowContains(String sTestCaseName, int colNum) throws Exception {

		int i;
		List<Integer> list = new ArrayList<Integer>();
		try {

			int rowCount = TestData.getRowUsed();

			for (i = 0; i <= rowCount; i++) {

				String name = TestData.getCellData(i, colNum);
				if (name.equalsIgnoreCase(sTestCaseName)) {
					list.add(i);
				}

			}

			return list;

		} catch (Exception e) {

			throw (e);

		}

	}

	public static int getRowUsed() throws Exception {

		try {

			int RowCount = ExcelWSheet.getLastRowNum();

			return RowCount;

		} catch (Exception e) {

			System.out.println(e.getMessage());

			throw (e);

		}

	}
}
