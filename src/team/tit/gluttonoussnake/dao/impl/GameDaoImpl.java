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

import team.tit.gluttonoussnake.dao.GameDao;
import team.tit.gluttonoussnake.domain.Game;
import team.tit.gluttonoussnake.util.XLSUtils;

/**
 * 操纵数据源中的Game
 * 
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月24日
 */
public class GameDaoImpl implements GameDao {
	private String file = this.getClass().getClassLoader().getResource("data/savedata.xls").getFile();

	@Override
	public Game findByUidAndType(int uid, int type) {
		// TODO 查询Game表，返回一个game对象
		Game game = new Game(uid, type);
		FileInputStream fis = null;
		Workbook workbook = null;
		try {
			fis = XLSUtils.getFileInputStream(file);
			workbook = new HSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet("Game");

			System.out.println("以下是打印game表的数据");
			XLSUtils.printSheetData(sheet);

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				if (row != null) {
					Cell celluid = row.getCell(0);
					Cell celltype = row.getCell(1);
					int uuid = XLSUtils.celltoInt(celluid);
					int ttype = XLSUtils.celltoInt(celltype);
					if (uuid == uid && ttype == type) {
						Cell cellsid = row.getCell(2);
						Cell cellfid = row.getCell(3);
						Cell cellwid = row.getCell(4);
						int ssid = XLSUtils.celltoInt(cellsid);
						int ffid = XLSUtils.celltoInt(cellfid);
						int wwid = XLSUtils.celltoInt(cellwid);
						game.setSid(ssid);
						game.setFid(ffid);
						game.setWid(wwid);
					}else {
						game.setSid(sheet.getLastRowNum()+1);
						game.setFid(sheet.getLastRowNum()+1);
						if(type==0) {
							game.setWid(0);
						}
						if(type==1) {
							game.setWid(0);
						}
						
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		XLSUtils.close(fis, null, workbook);
		return game;
	}

	@Override
	public void delOldData(Game g) {
		int sid = g.getSid();
		int fid = g.getFid();
		FileInputStream fis = null;
		Workbook workbook = null;
		FileOutputStream fos = null;
		try {
			fis = XLSUtils.getFileInputStream(file);
			workbook = new HSSFWorkbook(fis);
			Sheet food = workbook.getSheet("Food");
			Sheet snake = workbook.getSheet("Snake");
			Sheet body = workbook.getSheet("SnakeBody");
			
			System.out.println("以下是旧的数据");
			XLSUtils.printSheetData(food);
			XLSUtils.printSheetData(snake);
			XLSUtils.printSheetData(body);
			for (int i = 1; i <= food.getLastRowNum(); i++) {
				Row row = food.getRow(i);
				Cell cellfid = row.getCell(0);
				int cellfidInt = XLSUtils.celltoInt(cellfid);
				if (cellfidInt == fid) {
					food.removeRow(row);
				}
			}
			for (int i = 1; i <= snake.getLastRowNum(); i++) {
				Row row = snake.getRow(i);
				Cell cellsid = row.getCell(0);
				int cellsidInt = XLSUtils.celltoInt(cellsid);
				if (cellsidInt == sid) {
					snake.removeRow(row);
				}
			}
			for (int i = 1; i <= body.getLastRowNum(); i++) {
				Row row = body.getRow(i);
				Cell cellsid = row.getCell(0);
				int cellsidInt = XLSUtils.celltoInt(cellsid);
				if (cellsidInt == sid) {
					body.removeRow(row);
				}
			}
			
			fos = XLSUtils.getFileOutputStream(file);
			workbook.write(fos);
			
			System.out.println("以下是删除后的数据");
			XLSUtils.printSheetData(food);
			System.out.println("------------");
			XLSUtils.printSheetData(snake);
			System.out.println("------------");
			XLSUtils.printSheetData(body);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			XLSUtils.close(fis, fos, workbook);
		}
	}
	@Override
	public void saveGameData(Game game)
	{
		//删除Game旧数据
		delGameOldData(game);
		//写入新数据
		FileInputStream fis = null;
		Workbook workbook = null;
		FileOutputStream fos = null;
		try {
			
			fis = XLSUtils.getFileInputStream(file);
			workbook = new HSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet("Game");
			
			fos = XLSUtils.getFileOutputStream(file);
			Row newRow = sheet.createRow(sheet.getLastRowNum() + 1);
						
			Cell uid = newRow.createCell(0);
			uid.setCellValue(game.getUid());
			
			Cell type = newRow.createCell(1);
			type.setCellValue(game.getType());
			
			Cell sid = newRow.createCell(2);
			sid.setCellValue(game.getSid());
			
			Cell fid = newRow.createCell(3);
			fid.setCellValue(game.getFid());
			
			Cell wid = newRow.createCell(4);
			wid.setCellValue(game.getWid());
	
			
			workbook.write(fos);
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		XLSUtils.close(fis, fos, workbook);
		
	}
	
public void delGameOldData(Game game) {
	FileInputStream fis = null;
	Workbook workbook = null;
	FileOutputStream fos = null;
	try {
		fis = XLSUtils.getFileInputStream(file);
		workbook = new HSSFWorkbook(fis);
		Sheet game1 = workbook.getSheet("Game");

		for (int i = 1; i <= game1.getLastRowNum(); i++) {
			Row row = game1.getRow(i);
			if(row!=null) {
			Cell celluid = row.getCell(0);
			int celluidInt = XLSUtils.celltoInt(celluid);
			
			Cell celltype = row.getCell(1);
			int celltypeInt = XLSUtils.celltoInt(celltype);
			
			if (celluidInt == game.getUid()&&celltypeInt==game.getType()) {
				game1.removeRow(row);
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
