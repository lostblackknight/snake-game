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

import team.tit.gluttonoussnake.animation.player.Snake;
import team.tit.gluttonoussnake.dao.SnakeDao;
import team.tit.gluttonoussnake.util.XLSUtils;

/**
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月24日
 */
public class SnakeDaoImpl implements SnakeDao {

	private String file = this.getClass().getClassLoader().getResource("data/savedata.xls").getFile();

	@Override
	public Snake findBySid(int sid) {
		// TODO 通过sid查询Snake表返回一个snake对象
		Snake snake = new Snake(sid);
		FileInputStream fis = null;
		Workbook workbook = null;
		try {
			fis = XLSUtils.getFileInputStream(file);
			workbook = new HSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet("Snake");

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				if(row!=null) {
				Cell cellsid = row.getCell(0);
				int ssid = XLSUtils.celltoInt(cellsid);
				if (ssid == sid) {
					Cell cellx = row.getCell(1);
					Cell celly = row.getCell(2);
					int x = XLSUtils.celltoInt(cellx);
					int y = XLSUtils.celltoInt(celly);
					snake.setX(x);
					snake.setY(y);
				}}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		XLSUtils.close(fis, null, workbook);
		return snake;
	}

	@Override
	public void saveSnake(Snake snake)
	{

		//删除旧数据
		delSnakeData(snake);
		//写入新数据
		FileInputStream fis = null;
		Workbook workbook = null;
		FileOutputStream fos = null;
		try {
			
			fis = XLSUtils.getFileInputStream(file);
			workbook = new HSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet("Snake");
			
			fos = XLSUtils.getFileOutputStream(file);
			Row newRow = sheet.createRow(sheet.getLastRowNum() + 1);
						
			Cell uid = newRow.createCell(0);
			uid.setCellValue(snake.getId());
			
			Cell x = newRow.createCell(1);
			x.setCellValue(snake.getY());
			
			Cell y = newRow.createCell(2);
			y.setCellValue(snake.getY());
			
		
			workbook.write(fos);
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		XLSUtils.close(fis, fos, workbook);
		
		
		
			
		
	}
	
	public void delSnakeData(Snake snake) {


		FileInputStream fis = null;
		Workbook workbook = null;
		FileOutputStream fos = null;
		try {
			fis = XLSUtils.getFileInputStream(file);
			workbook = new HSSFWorkbook(fis);
			Sheet snake1 = workbook.getSheet("Snake");

			for (int i = 1; i <= snake1.getLastRowNum(); i++) {
				Row row = snake1.getRow(i);
				if(row!=null) {
				Cell cellsid = row.getCell(0);
				int cellsidInt = XLSUtils.celltoInt(cellsid);
				if (cellsidInt == snake.getId()) {
					snake1.removeRow(row);
				}}
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
