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
		try {

			FileInputStream fis = null;
			fis = XLSUtils.getFileInputStreamInput(file);
			Workbook workbook = new HSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet("Game");

			XLSUtils.printSheetData(sheet);

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);

				if (row != null) {

					Cell celluid = row.getCell(0);
					Cell celltype = row.getCell(1);

			
					

					int uuid =XLSUtils.celltoInt(celluid);
					int ttype =XLSUtils.celltoInt(celltype);

					
					System.out.println(uid);
					System.out.println(uuid);
					System.out.println(type);
					System.out.println(ttype);

					if (uuid == uid && ttype == type) {
						Cell cellsid = row.getCell(2);
						Cell cellfid = row.getCell(3);
						Cell cellwid = row.getCell(4);
					
						int ssid =XLSUtils.celltoInt(cellsid);
						int ffid =XLSUtils.celltoInt(cellfid);
						int wwid =XLSUtils.celltoInt(cellwid);

						game.setSid(ssid);
						game.setFid(ffid);
						game.setWid(wwid);
						
						System.out.println(ssid);
						System.out.println(ffid);
						System.out.println(ffid);

					}
				}
			}

			XLSUtils.close(fis, null, workbook);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return game;
	}

	@Override
	public void delOldData(Game g) {

		int sid = g.getSid();
		int fid = g.getFid();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Workbook workbook = null;
		try {
			workbook = new HSSFWorkbook(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sheet food = workbook.getSheet("Food");
		Sheet snake = workbook.getSheet("Snake");
		Sheet body = workbook.getSheet("SnakeBody");

		for (int i = 1; i <= food.getLastRowNum(); i++) {
			Row row = food.getRow(i);
			Cell cellfid = row.getCell(0);
			int cellfidInt = (int) cellfid.getNumericCellValue();

			if (cellfidInt == fid) {
				food.shiftRows(i, food.getLastRowNum(), -1);
			}
		}

		for (int i = 1; i <= snake.getLastRowNum(); i++) {
			Row row = snake.getRow(i);
			Cell cellsid = row.getCell(0);
			int cellsidInt = (int) cellsid.getNumericCellValue();

			if (cellsidInt == sid) {
				snake.shiftRows(i, snake.getLastRowNum(), -1);
			}
		}

		for (int i = 1; i <= body.getLastRowNum(); i++) {
			Row row = body.getRow(i);
			Cell cellsid = row.getCell(0);
			int cellsidInt = (int) cellsid.getNumericCellValue();

			if (cellsidInt == sid) {
				body.shiftRows(i, body.getLastRowNum(), -1);
			}
		}

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			workbook.write(fos);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
