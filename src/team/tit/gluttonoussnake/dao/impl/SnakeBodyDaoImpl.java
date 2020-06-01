/*
 *
 */
package team.tit.gluttonoussnake.dao.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import team.tit.gluttonoussnake.animation.player.SnakeNode;
import team.tit.gluttonoussnake.dao.SnakeBodyDao;
import team.tit.gluttonoussnake.xls.XLS;

/**
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月24日
 */
public class SnakeBodyDaoImpl implements SnakeBodyDao {
	private String file = XLS.class.getClassLoader().getResource("data/savedata.xls").getFile();

	@Override
	public LinkedList<SnakeNode> findBodyBySid(int sid) {
		// TODO 根据sid查询snakebody表返回一个linklist
		
		LinkedList<SnakeNode> body=new LinkedList<SnakeNode>();
		
		try
		{
			FileInputStream fis = null;		
			fis = new FileInputStream(file);
			Workbook workbook = new HSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet("SnakeBody");
						
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
                    	
                    body.add(i-1,new SnakeNode(x,y));                   	                
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
		

		
		return body;
	}


}
