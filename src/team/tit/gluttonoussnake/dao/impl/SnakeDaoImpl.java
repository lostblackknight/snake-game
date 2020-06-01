/*
 *
 */
package team.tit.gluttonoussnake.dao.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import team.tit.gluttonoussnake.animation.player.Snake;
import team.tit.gluttonoussnake.dao.SnakeDao;
import team.tit.gluttonoussnake.xls.XLS;

/**
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月24日
 */
public class SnakeDaoImpl implements SnakeDao {

	private String file = XLS.class.getClassLoader().getResource("data/savedata.xls").getFile();
	@Override
	public Snake findBySid(int sid) {
		// TODO 通过sid查询Snake表返回一个snake对象
		
		Snake snake = new Snake(sid);
		try
		{
			FileInputStream fis = null;		
			fis = new FileInputStream(file);
			Workbook workbook = new HSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet("Snake");
						
            for (int i =1; i <=sheet.getLastRowNum(); i++) {
            	Row row = sheet.getRow(i);                    
                    Cell cell = row.getCell(0);

                    double values = cell.getNumericCellValue();
                    int ssid = new Double(values).intValue();
                    
                    if(ssid==sid) {                    	
                    	Cell cell1 = row.getCell(1);
                    	double valuesx = cell1.getNumericCellValue();
                    	 int x=new Double(valuesx).intValue();
                    	 Cell cell2 = row.getCell(2);
                    	 double valuesy = cell2.getNumericCellValue();
                    	 int y=new Double(valuesy ).intValue();
                     	 
                         snake.setX(x);
			             snake.setY(y);    
			   
            	 }	
	            }
					
			workbook.close();
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
								
		return snake;
	}

}
