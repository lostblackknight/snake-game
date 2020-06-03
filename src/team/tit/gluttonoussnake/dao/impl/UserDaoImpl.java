package team.tit.gluttonoussnake.dao.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import team.tit.gluttonoussnake.dao.UserDao;
import team.tit.gluttonoussnake.domain.User;
import team.tit.gluttonoussnake.util.XLSUtils;

/**
 * 操纵数据源中的User
 * 
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月22日
 */
public class UserDaoImpl implements UserDao {

	private String file = this.getClass().getClassLoader().getResource("data/savedata.xls").getFile();

	private boolean usernameFlag = false;

	private boolean passwordFlag = false;

	private boolean mobilephoneFlag = false;

	private Row rightRow = null;// 拿到要找的那一行

	/**
	 * 根据用户名查询用户信息
	 * 
	 * @param username 用户名
	 * @return 返回数据源中的user对象
	 */
	@Override
	public User findByUsername(String username) {
		// TO DO 根据用户名查询数据源中的user，返回user对象
		User user = null;
		FileInputStream fis = null;
		Workbook workbook = null;
		try {
			fis = XLSUtils.getFileInputStream(file);
			workbook = new HSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet("User");
			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
				Row row = sheet.getRow(rowNum);// 获取行
				if (row != null) {
					Cell usernameCell = row.getCell(1);// 获取用户名
					if (usernameCell != null) {
						String value = XLSUtils.celltoString(usernameCell);
						if (value.equals(username)) {
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
				int uidValue = XLSUtils.celltoInt(uid);

				Cell usernameCell = rightRow.getCell(1);
				String usernameValue = XLSUtils.celltoString(usernameCell);

				Cell password = rightRow.getCell(2);
				String passwordValue = XLSUtils.celltoString(password);

				Cell mobilephone = rightRow.getCell(3);
				String mobilephoneValue = XLSUtils.celltoString(mobilephone);

				user = new User();
				user.setUid(uidValue);
				user.setUsername(usernameValue);
				user.setPassword(passwordValue);
				user.setMobliePhone(mobilephoneValue);
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			XLSUtils.close(fis, null, workbook);
		}
		return user;
	}

	/**
	 * 保存用户信息
	 * 
	 * @param user user对象
	 */
	@Override
	public void save(User user) {
		// TODO 将user对象保存到数据源中
		FileInputStream fis = null;
		Workbook workbook = null;
		FileOutputStream fos = null;
		try {
			fis = XLSUtils.getFileInputStream(file);
			workbook = new HSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet("User");
			
			Row newRow = sheet.createRow(sheet.getLastRowNum() + 1);
			
			Cell uid = newRow.createCell(0);
			uid.setCellValue(sheet.getLastRowNum());

			Cell username = newRow.createCell(1);
			username.setCellValue(user.getUsername());

			Cell password = newRow.createCell(2);
			password.setCellValue(user.getPassword());

			Cell mobliephone = newRow.createCell(3);
			mobliephone.setCellValue(user.getMobliePhone());
			
			fos = XLSUtils.getFileOutputStream(file);
			workbook.write(fos);
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			XLSUtils.close(fis, fos, workbook);
		}
	}

	/**
	 * 根据用户名和密码查询用户信息
	 * 
	 * @param username 用户名
	 * @param password 密码
	 * @return 返回一个user对象
	 */
	@Override
	public User findByUsernameAndPassword(String username, String password) {
		// TODO 根据用户名和密码查询数据源中的user，返回user对象
		User user = null;
		FileInputStream fis = null;
		Workbook workbook = null;
		try {
			fis = XLSUtils.getFileInputStream(file);
			workbook = new HSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet("User");

			System.out.println("-------用户加载start-------");
			XLSUtils.printSheetData(sheet);// 打印User表
			System.out.println("-------用户加载end-------");
			System.out.println();

			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
				Row row = sheet.getRow(rowNum);// 获取行
				if (row != null) {
					Cell usernameCell = row.getCell(1);// 获取用户名
					if (username != null) {
						String value = XLSUtils.celltoString(usernameCell);
						if (value.equals(username)) {
							usernameFlag = true;
						} else {
							usernameFlag = false;
						}
					}
					Cell passwordCell = row.getCell(2);// 获取密码
					if (password != null) {
						String value = XLSUtils.celltoString(passwordCell);
						if (value.equals(password)) {
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
				String uidString = XLSUtils.celltoString(uid);
				int uidValue = Integer.parseInt(uidString);

				Cell usernameCell = rightRow.getCell(1);
				String usernameValue = XLSUtils.celltoString(usernameCell);

				Cell passwordCell = rightRow.getCell(2);
				String passwordValue = XLSUtils.celltoString(passwordCell);

				Cell mobilephoneCell = rightRow.getCell(3);
				String mobilephoneValue = XLSUtils.celltoString(mobilephoneCell);

				user = new User();
				user.setUid(uidValue);
				user.setUsername(usernameValue);
				user.setPassword(passwordValue);
				user.setMobliePhone(mobilephoneValue);
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			XLSUtils.close(fis, null, workbook);
		}

		return user;
	}

	/**
	 * 根据手机号查询用户信息
	 * 
	 * @param mobliePhone 手机号
	 * @return 返回一个user对象
	 */
	@Override
	public User findByMobilePhone(String mobliePhone) {
		// TODO 根据手机号查询数据源中的user，返回user对象
		User user = null;
		FileInputStream fis = null;
		Workbook workbook = null;
		try {
			fis = XLSUtils.getFileInputStream(file);
			workbook = new HSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet("User");
			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
				Row row = sheet.getRow(rowNum);// 获取行
				if (row != null) {
					Cell mobilephoneCell = row.getCell(3);
					if (mobilephoneCell != null) {
						String value = XLSUtils.celltoString(mobilephoneCell);
						if (value.equals(mobliePhone)) {
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
				Cell uidCell = rightRow.getCell(0);
				int uidValue = XLSUtils.celltoInt(uidCell);

				Cell usernameCell = rightRow.getCell(1);
				String usernameValue = XLSUtils.celltoString(usernameCell);

				Cell passwordCell = rightRow.getCell(2);
				String passwordValue = XLSUtils.celltoString(passwordCell);

				Cell mobilePhoneCell = rightRow.getCell(3);
				String mobilephoneValue = XLSUtils.celltoString(mobilePhoneCell);

				user = new User();
				user.setUid(uidValue);
				user.setUsername(usernameValue);
				user.setPassword(passwordValue);
				user.setMobliePhone(mobilephoneValue);
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			XLSUtils.close(fis, null, workbook);
		}

		return user;
	}
}
