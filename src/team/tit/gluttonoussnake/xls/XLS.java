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

	@SuppressWarnings({ "incomplete-switch", "static-access" })
	private String cellToString(Cell cell) {
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

	private int cellToInt(Cell cell) {
		if (cell.getCellTypeEnum() == CellType.NUMERIC) {
			return (int) cell.getNumericCellValue();
		}
		return -1;
	}

	@SuppressWarnings({ "resource", "static-access" })
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

		rowCount = sheet.getLastRowNum() + 1;// 总行数

		System.out.println(rowCount);

		Row head = sheet.getRow(0);
		for (int i = 0; i < rowCount; i++) {
			Row row = sheet.getRow(i);
			if (row != null) {
				// 读取列
				int cellCount = head.getPhysicalNumberOfCells();
				for (int j = 0; j < cellCount; j++) {
					System.out.print("[" + (i) + "-" + (j) + "]");

					Cell cell = row.getCell(j);

					// 匹配列的类型
					if (cell != null) {
						CellType cellType = cell.getCellType();
						String value = "";

						switch (cellType) {
						case STRING:
							String valueString = cell.getStringCellValue();
							value = valueString;
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
								// 是日期
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
						default:
							break;
						}
					}
				}
				System.out.println();
			}
		}

		for (int rowNum = 1; rowNum <= rowCount; rowNum++) {
			Row row = sheet.getRow(rowNum);// 获取行
			if (row != null) {
				Cell username = row.getCell(1);// 获取用户名
				if (username != null) {
					String value = cellToString(username);
					if (value.equals(name)) {
						usernameFlag = true;
					}
				}
				Cell password = row.getCell(2);// 获取密码
				if (password != null) {
					String value = cellToString(password);
					if (value.equals(word)) {
						passwordFlag = true;
					}
				}
				if (usernameFlag && passwordFlag) {
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
			fis.close();
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

		rowCount = sheet.getLastRowNum() + 1;// 总行数

		for (int rowNum = 1; rowNum <= rowCount; rowNum++) {
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
			fis.close();
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
			System.out.println(sheet.getLastRowNum());
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

		rowCount = sheet.getLastRowNum() + 1;// 总行数
		
		for (int rowNum = 1; rowNum <= rowCount; rowNum++) {
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
}
