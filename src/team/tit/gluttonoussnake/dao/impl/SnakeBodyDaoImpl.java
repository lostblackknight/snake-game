/*
 *
 */
package team.tit.gluttonoussnake.dao.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import team.tit.gluttonoussnake.animation.player.SnakeNode;
import team.tit.gluttonoussnake.dao.SnakeBodyDao;
import team.tit.gluttonoussnake.util.XLSUtils;

/**
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月24日
 */
public class SnakeBodyDaoImpl implements SnakeBodyDao {
	private String file = this.getClass().getClassLoader().getResource("data/savedata.xls").getFile();

	@Override
	public LinkedList<SnakeNode> findBodyBySid(int sid) {
		// TODO 根据sid查询snakebody表返回一个linklist
		LinkedList<SnakeNode> body = new LinkedList<SnakeNode>();
		FileInputStream fis = null;
		Workbook workbook = null;
		try {
			fis = XLSUtils.getFileInputStream(file);
			workbook = new HSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet("SnakeBody");

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				Cell cellsid = row.getCell(0);
				int ssid = XLSUtils.celltoInt(cellsid);
				if (ssid == sid) {
					Cell cellx = row.getCell(1);
					Cell celly = row.getCell(2);
					int x = XLSUtils.celltoInt(cellx);
					int y = XLSUtils.celltoInt(celly);
					body.add(new SnakeNode(x, y));
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		XLSUtils.close(fis, null, workbook);
		return body;
	}

}
