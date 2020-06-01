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
import team.tit.gluttonoussnake.xls.XLS;

/**
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月24日
 */
public class FoodDaoImpl implements FoodDao {
	private String file = XLS.class.getClassLoader().getResource("data/savedata.xls").getFile();
	@Override
	public Food findByFid(int fid) {

		// TODO 通过fid查找食物对象
		Food food=new Food(fid);
		try
		{
			FileInputStream fis = null;		
			fis = new FileInputStream(file);
			Workbook workbook = new HSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet("Food");
						
            for (int i =1; i <=sheet.getLastRowNum(); i++) {

            	Row row = sheet.getRow(i);               	
                    Cell cell = row.getCell(0);
                
                    double values = cell.getNumericCellValue();
                    int ffid = new Double(values).intValue();
                    
                    if(ffid==fid) {                    	
                    	Cell cell1 = row.getCell(1);
                    	double valuesx = cell1.getNumericCellValue();
                    	 int x=new Double(valuesx).intValue();
                    	 Cell cell2 = row.getCell(2);
                    	 double valuesy = cell2.getNumericCellValue();
                    	 int y=new Double(valuesy ).intValue();
                         food.setX(x);
			             food.setY(y);        		 
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
						

		
		return food;
	}

}
