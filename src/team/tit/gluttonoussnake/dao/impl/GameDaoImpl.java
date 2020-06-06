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
import team.tit.gluttonoussnake.animation.npc.Wall;
import team.tit.gluttonoussnake.animation.player.Snake;
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
			System.out.println("以下是获取数据时打印game表的数据");
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
		if (g != null) {
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
				for (int i = 1; i <= food.getLastRowNum(); i++) {
					Row row = food.getRow(i);
					if (row != null) {
						Cell cellfid = row.getCell(0);
						int cellfidInt = XLSUtils.celltoInt(cellfid);
						if (cellfidInt == fid) {
							food.removeRow(row);
						}
					}
				}
				for (int i = 1; i <= snake.getLastRowNum(); i++) {
					Row row = snake.getRow(i);
					if (row != null) {
						Cell cellsid = row.getCell(0);
						int cellsidInt = XLSUtils.celltoInt(cellsid);
						if (cellsidInt == sid) {
							snake.removeRow(row);
						}
					}
				}
				for (int i = 1; i <= body.getLastRowNum(); i++) {
					Row row = body.getRow(i);
					if (row != null) {
						Cell cellsid = row.getCell(0);
						int cellsidInt = XLSUtils.celltoInt(cellsid);
						if (cellsidInt == sid) {
							body.removeRow(row);
						}
					}
				}
				fos = XLSUtils.getFileOutputStream(file);
				workbook.write(fos);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				XLSUtils.close(fis, fos, workbook);
			}
		}
	}

	@Override
	public void saveGameData(Game game) {
		// 删除Game旧数据
		delGameOldData(game);
		// 写入新数据
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
			System.out.println("以下是保存数据时打印game表的数据");
			XLSUtils.printSheetData(sheet);
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
				if (row != null) {
					Cell celluid = row.getCell(0);
					int celluidInt = XLSUtils.celltoInt(celluid);
					Cell celltype = row.getCell(1);
					int celltypeInt = XLSUtils.celltoInt(celltype);
					if (celluidInt == game.getUid() && celltypeInt == game.getType()) {
						game1.removeRow(row);
					}
				}
			}
			fos = XLSUtils.getFileOutputStream(file);
			workbook.write(fos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		XLSUtils.close(fis, fos, workbook);
	}

	@Override
	public Game setInitialValue(Game game) {
		// TODO 查找game表
		Game g = findByUidAndType(game.getUid(), game.getType());
		if (game.getType() == 0) {
			if (g.getSid() > 0 && game.getType() == 0) {
				
			} else if (g.getSid() <= 0 && game.getType() == 0) {
				FileInputStream fis = null;
				Workbook workbook = null;
				FileOutputStream fos = null;
				try {
					fis = XLSUtils.getFileInputStream(file);
					workbook = new HSSFWorkbook(fis);
					Sheet sheet = workbook.getSheet("Game");
					g.setSid(sheet.getLastRowNum() + 1);
					g.setFid(sheet.getLastRowNum() + 1);
					g.setWid(0);
					Snake snake = new Snake();
					g.setSanke(snake);
					Food food = new Food();
					g.setFood(food);
					Wall wall = new Wall();
					g.setWall(wall);
					g.getWall().setId(0);
					g.getSanke().setId(sheet.getLastRowNum() + 1);
					g.getFood().setId(sheet.getLastRowNum() + 1);
//					g.getWall().setId(0);
					fos = XLSUtils.getFileOutputStream(file);
					workbook.write(fos);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					XLSUtils.close(fis, fos, workbook);
				}
			}
		} else {
			if (g.getSid() > 0 && game.getType() == 1) {
				
			} else if (g.getSid() <= 0 && game.getType() == 1) {
				FileInputStream fis = null;
				Workbook workbook = null;
				FileOutputStream fos = null;
				try {
					fis = XLSUtils.getFileInputStream(file);
					workbook = new HSSFWorkbook(fis);
					Sheet sheet = workbook.getSheet("Game");
					g.setSid(sheet.getLastRowNum() + 1);
					g.setFid(sheet.getLastRowNum() + 1);
					// 改地图
					g.setWid(1);
					Snake snake = new Snake();
					g.setSanke(snake);
					Food food = new Food();
					g.setFood(food);
					Wall wall = new Wall();
					g.setWall(wall);
					g.getWall().setId(1);
					g.getSanke().setId(sheet.getLastRowNum() + 1);
					g.getFood().setId(sheet.getLastRowNum() + 1);
					fos = XLSUtils.getFileOutputStream(file);
					workbook.write(fos);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					XLSUtils.close(fis, fos, workbook);
				}
			}
		}
		return g;
	}
}
