/*
 *
 */
package team.tit.gluttonoussnake.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年6月3日
 */
@SuppressWarnings("deprecation")
public class XLSUtils {

	private XLSUtils() {
	}

	/**
	 * 获取输入流
	 * 
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 */
	public static FileInputStream getFileInputStreamInput(String file) throws FileNotFoundException {
		FileInputStream fis = new FileInputStream(file);
		return fis;
	}

	/**
	 * 获取输出流
	 * 
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 */
	public static FileOutputStream getFileOutputStream(String file) throws FileNotFoundException {
		FileOutputStream fos = new FileOutputStream(file);
		return fos;
	}

	/**
	 * 关闭流
	 * 
	 * @param fis
	 * @param fos
	 * @param workbook
	 */
	public static void close(FileInputStream fis, FileOutputStream fos, Workbook workbook) {
		if (fos != null) {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (workbook != null) {
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (fis != null) {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 打印表中所有数据
	 * 
	 * @param sheet
	 */
	@SuppressWarnings({ "incomplete-switch", "static-access" })
	public static void printSheetData(Sheet sheet) {
		Row head = sheet.getRow(0);// 获取第一行
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			if (row != null) {
				// 读取列
				int cellCount = head.getPhysicalNumberOfCells();// 获取列数
				for (int j = 0; j < cellCount; j++) {
					System.out.print("[" + (i) + "-" + (j) + "]");
					Cell cell = row.getCell(j);
					// 匹配列的类型
					if (cell != null) {
						CellType cellType = cell.getCellType();
						String value = "";
						switch (cellType) {
						case STRING:
							value = cell.getStringCellValue();
							System.out.print(value);
							break;
						case BOOLEAN:
							boolean valueBoolean = cell.getBooleanCellValue();
							value = String.valueOf(valueBoolean);
							System.out.print(value);
							break;
						case BLANK:
							break;
						case NUMERIC:
							if (HSSFDateUtil.isCellDateFormatted(cell)) {
								Date date = cell.getDateCellValue();
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								value = sdf.format(date);
								System.out.print(value);
							} else {
								cell.setCellType(cellType.STRING);
								value = cell.toString();
								System.out.print(value);
							}
							break;
						case ERROR:
							System.out.print("类型错误");
							break;
						}
					}
				}
				System.out.println();
			}
		}
	}

	/**
	 * 将cell中的内容转换为字符串
	 * @param cell
	 * @return
	 */
	@SuppressWarnings({ "incomplete-switch", "static-access" })
	public static String celltoString(Cell cell) {
		CellType cellType = cell.getCellType();
		String value = "";
		switch (cellType) {
		case STRING:
			value = cell.getStringCellValue();
			break;
		case BOOLEAN:
			boolean valueBoolean = cell.getBooleanCellValue();
			value = String.valueOf(valueBoolean);
			break;
		case NUMERIC:
			cell.setCellType(cellType.STRING);
			value = cell.toString();
			break;
		case BLANK:
			break;
		case ERROR:
			break;
		}
		return value;
	}
	
	/**
	 * 将cell中的内容转换为整型,cell中存的必须是数字
	 * @param cell
	 * @return
	 */
	public static int celltoInt(Cell cell) {
		return Integer.parseInt(celltoString(cell));
	}
}
