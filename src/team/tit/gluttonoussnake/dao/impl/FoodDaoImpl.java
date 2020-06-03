/*
 *
 */
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

import team.tit.gluttonoussnake.animation.npc.Food;
import team.tit.gluttonoussnake.dao.FoodDao;
import team.tit.gluttonoussnake.util.XLSUtils;

/**
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月24日
 */
public class FoodDaoImpl implements FoodDao {
	private String file = this.getClass().getClassLoader().getResource("data/savedata.xls").getFile();

	@Override
	public Food findByFid(int fid) {
		// TODO 通过fid查找食物对象
		Food food = new Food(fid);
		FileInputStream fis = null;
		Workbook workbook = null;
		try {
			fis = XLSUtils.getFileInputStream(file);
			workbook = new HSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet("Food");

			System.out.println("以下是打印food表的数据");
			XLSUtils.printSheetData(sheet);
			
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				if (row != null) {
					Cell cellfid = row.getCell(0);
					int ffid = XLSUtils.celltoInt(cellfid);
					if (ffid == fid) {
						Cell cellx = row.getCell(1);
						int x = XLSUtils.celltoInt(cellx);
						Cell celly = row.getCell(2);
						int y = XLSUtils.celltoInt(celly);
						food.setX(x);
						food.setY(y);
					}
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		XLSUtils.close(fis, null, workbook);
		return food;
	}
	@Override
	public void saveFoodData(Food food)
	{
		//删除旧数据
		delFoodData(food);
		//写入新数据
		FileInputStream fis = null;
		Workbook workbook = null;
		FileOutputStream fos = null;
		try {
			
			fis = XLSUtils.getFileInputStream(file);
			workbook = new HSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet("Food");
			
			fos = XLSUtils.getFileOutputStream(file);
			Row newRow = sheet.createRow(sheet.getLastRowNum() + 1);
						
			Cell uid = newRow.createCell(0);
			uid.setCellValue(food.getId());
			
			Cell x = newRow.createCell(1);
			x.setCellValue(food.getY());
			
			Cell y = newRow.createCell(2);
			y.setCellValue(food.getY());
			
		
			workbook.write(fos);
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		XLSUtils.close(fis, fos, workbook);
		
		
		
		
	}
	public void delFoodData(Food food) {

		FileInputStream fis = null;
		Workbook workbook = null;
		FileOutputStream fos = null;
		try {
			fis = XLSUtils.getFileInputStream(file);
			workbook = new HSSFWorkbook(fis);
			Sheet food1 = workbook.getSheet("Food");

			for (int i = 1; i <= food1.getLastRowNum(); i++) {
				
				Row row = food1.getRow(i);
				if(row!=null) {
				Cell cellfid = row.getCell(0);
				int cellfidInt = XLSUtils.celltoInt(cellfid);
				if (cellfidInt == food.getId()) {
					food1.removeRow(row);
				}
			}
			}
			
			fos = XLSUtils.getFileOutputStream(file);
			fos.flush();
			workbook.write(fos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		XLSUtils.close(fis, fos, workbook);
				
	}

}
