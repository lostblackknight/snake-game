/*
 *
 */
package team.tit.gluttonoussnake.xls;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import team.tit.gluttonoussnake.domain.User;

/**
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月30日
 */
@SuppressWarnings("deprecation")
public class XLS {

	private String file = XLS.class.getClassLoader().getResource("data/savedata.xls").getFile();

	private int rowCount = 1;

	private boolean usernameFlag = false;

	private boolean passwordFlag = false;

	private boolean mobilephoneFlag = false;

	private User userXLS = null;

	private Row rightRow = null;// 拿到要找的那一行

	/**
	 * 通过用户名和密码查找User对象
	 * 
	 * @param name
	 * @param word
	 * @return
	 */
	@SuppressWarnings({ "resource" })
	public User findByUsernameAndPassword(String name, String word) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Sheet sheet = null;
		try {
			Workbook workbook = new HSSFWorkbook(fis);
			sheet = workbook.getSheet("User");
		} catch (IOException e) {
			e.printStackTrace();
		}

		printAllData(sheet);// 打印User表

		for (int rowNum = 1; rowNum <= rowCount; rowNum++) {
			Row row = sheet.getRow(rowNum);// 获取行
			if (row != null) {
				Cell username = row.getCell(1);// 获取用户名
				if (username != null) {
					String value = cellToString(username);
					if (value.equals(name)) {
						usernameFlag = true;
					} else {
						usernameFlag = false;
					}
				}
				Cell password = row.getCell(2);// 获取密码
				if (password != null) {
					String value = cellToString(password);
					if (value.equals(word)) {
						passwordFlag = true;
					} else {
						passwordFlag = false;
					}
				}
				if (usernameFlag && passwordFlag) {
					rightRow = row;
				}
			}
		}

		if (rightRow != null) {
			Cell uid = rightRow.getCell(0);
			String uidString = cellToString(uid);
			int uidValue = Integer.parseInt(uidString);
			

			Cell username = rightRow.getCell(1);
			String usernameValue = cellToString(username);

			Cell password = rightRow.getCell(2);
			String passwordValue = cellToString(password);

			Cell mobilephone = rightRow.getCell(3);
			String mobilephoneValue = cellToString(mobilephone);

			userXLS = new User();
			userXLS.setUid(uidValue);
			userXLS.setUsername(usernameValue);
			userXLS.setPassword(passwordValue);
			userXLS.setMobliePhone(mobilephoneValue);
		}

		try {
			if (fis != null) {
				fis.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userXLS;
	}

	@SuppressWarnings("resource")
	public User findByUsername(String name) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Sheet sheet = null;
		try {
			Workbook workbook = new HSSFWorkbook(fis);
			sheet = workbook.getSheet("User");
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int rowNum = 1; rowNum <= sheet.getLastRowNum() + 1; rowNum++) {
			Row row = sheet.getRow(rowNum);// 获取行
			if (row != null) {
				Cell username = row.getCell(1);// 获取用户名
				if (username != null) {
					String value = cellToString(username);
					if (value.equals(name)) {
						usernameFlag = true;
					}
				}
				if (usernameFlag) {
					rightRow = row;
				}
			}
		}

		if (rightRow != null) {
			Cell uid = rightRow.getCell(0);
			int uidValue = cellToInt(uid);

			Cell username = rightRow.getCell(1);
			String usernameValue = cellToString(username);

			Cell password = rightRow.getCell(2);
			String passwordValue = cellToString(password);

			Cell mobilephone = rightRow.getCell(3);
			String mobilephoneValue = cellToString(mobilephone);

			userXLS = new User();
			userXLS.setUid(uidValue);
			userXLS.setUsername(usernameValue);
			userXLS.setPassword(passwordValue);
			userXLS.setMobliePhone(mobilephoneValue);
		}

		try {
			if (fis != null) {
				fis.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userXLS;
	}

	@SuppressWarnings("resource")
	public void saveUser(User user) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			Workbook workbook = new HSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet("User");

			FileOutputStream fos = new FileOutputStream(file);
			Row newRow = sheet.createRow(sheet.getLastRowNum() + 1);

			Cell uid = newRow.createCell(0);
			uid.setCellValue(sheet.getLastRowNum());

			Cell username = newRow.createCell(1);
			username.setCellValue(user.getUsername());

			Cell password = newRow.createCell(2);
			password.setCellValue(user.getPassword());

			Cell mobliephone = newRow.createCell(3);
			mobliephone.setCellValue(user.getMobliePhone());

			fos.flush();
			workbook.write(fos);
			fos.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public User findByMobilePhone(String phone) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Sheet sheet = null;
		try {
			Workbook workbook = new HSSFWorkbook(fis);
			sheet = workbook.getSheet("User");
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int rowNum = 1; rowNum <= sheet.getLastRowNum() + 1; rowNum++) {
			Row row = sheet.getRow(rowNum);// 获取行
			if (row != null) {
				Cell mobilephone = row.getCell(3);
				if (mobilephone != null) {
					String value = cellToString(mobilephone);
					if (value.equals(phone)) {
						mobilephoneFlag = true;
					} else {
						mobilephoneFlag = false;
					}
					if (mobilephoneFlag) {
						rightRow = row;
					}
				}
			}
		}

		if (rightRow != null) {
			Cell uid = rightRow.getCell(0);
			int uidValue = cellToInt(uid);

			Cell username = rightRow.getCell(1);
			String usernameValue = cellToString(username);

			Cell password = rightRow.getCell(2);
			String passwordValue = cellToString(password);

			Cell mobilephone = rightRow.getCell(3);
			String mobilephoneValue = cellToString(mobilephone);

			userXLS = new User();
			userXLS.setUid(uidValue);
			userXLS.setUsername(usernameValue);
			userXLS.setPassword(passwordValue);
			userXLS.setMobliePhone(mobilephoneValue);
		}

		try {
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userXLS;
	}

	/**
	 * 打印表的信息
	 * 
	 * @param sheet 表
	 */
	@SuppressWarnings({ "static-access", "incomplete-switch" })
	public void printAllData(Sheet sheet) {
		rowCount = sheet.getLastRowNum() + 1;// 获取总行数
		Row head = sheet.getRow(0);// 获取第一行
		for (int i = 0; i < rowCount; i++) {
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
	 * 将不同类型的单元格元素转换为字符串
	 * 
	 * @param cell 单元格
	 * @return 返回一个字符串
	 */
	@SuppressWarnings({ "incomplete-switch", "static-access" })
	public String cellToString(Cell cell) {
		CellType cellType = cell.getCellTypeEnum();
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
	 * 将单元格元素转换为整型
	 * 
	 * @param cell 单元格
	 * @return 是number类型返回大于等于0的数字，不是返回-1
	 */
	public int cellToInt(Cell cell) {
		if (cell.getCellTypeEnum() == CellType.NUMERIC) {
			return (int) cell.getNumericCellValue();
		}
		return -1;
	}

}
