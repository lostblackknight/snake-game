/*
 *
 */
package team.tit.gluttonoussnake.dao.impl;

import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import team.tit.gluttonoussnake.animation.npc.Wall;
import team.tit.gluttonoussnake.dao.WallDao;

/**
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月24日
 */
public class WallDaoImpl implements WallDao {
	private String file = this.getClass().getClassLoader().getResource("data/savedata.xls").getFile();

	@Override
	public ArrayList<Wall> findAll() {

		ArrayList<Point> points1 = findByWid(1);
		ArrayList<Point> points2 = findByWid(2);
		ArrayList<Point> points3 = findByWid(3);

		Wall wall1 = new Wall(1, points1);
		Wall wall2 = new Wall(2, points2);
		Wall wall3 = new Wall(3, points3);

		ArrayList<Wall> list = new ArrayList<Wall>();
		list.add(null);
		list.add(wall1);
		list.add(wall2);
		list.add(wall3);
		return list;
	}

	@Override
	public ArrayList<Point> findByWid(int wid) {
		// TODO 通过mid查询地图表
		ArrayList<Point> points = new ArrayList<Point>();
		try {
			FileInputStream fis = null;
			fis = new FileInputStream(file);
			Workbook workbook = new HSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet("Wall");

			for (int i = 1; i <= sheet.getLastRowNum() + 1; i++) {
				Row row = sheet.getRow(i);
				if (row != null) {
					Cell cell = row.getCell(0);
					
					double values = cell.getNumericCellValue();
					int wwid = new Double(values).intValue();
					
					if (wwid == wid) {
						Cell cell1 = row.getCell(1);
						double valuesx = cell1.getNumericCellValue();
						int x = new Double(valuesx).intValue();
						Cell cell2 = row.getCell(2);
						double valuesy = cell2.getNumericCellValue();
						int y = new Double(valuesy).intValue();
						points.add(new Point(x, y));
					}
				}
			}

			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return points;
	}

}
