/*
 *
 */
package team.tit.gluttonoussnake.dao.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

}
