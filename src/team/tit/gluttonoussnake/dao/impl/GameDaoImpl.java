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

import team.tit.gluttonoussnake.dao.GameDao;
import team.tit.gluttonoussnake.domain.Game;
import team.tit.gluttonoussnake.xls.XLS;

/**
 * 操纵数据源中的Game
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月24日
 */
public class GameDaoImpl implements GameDao {
	private String file = XLS.class.getClassLoader().getResource("data/savedata.xls").getFile();

	@Override
	public Game findByUidAndType(int uid, int type) {
		// TODO 查询Game表，返回一个game对象		
		Game game = new Game(uid,type);
try
		{
			
	          FileInputStream fis = null;		
	            fis = new FileInputStream(file);
	        Workbook workbook = new HSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet("Game");

            for (int i =1; i <= sheet.getLastRowNum(); i++) {
            	Row row = sheet.getRow(i);  
            	           	
                Cell celluid = row.getCell(0);
                Cell celltype = row.getCell(1);   
                
            
                double valuesuid = celluid.getNumericCellValue();   
                int uuid = new Double(valuesuid).intValue();              
                double valuestype = celltype.getNumericCellValue();
                int ttype = new Double(valuestype).intValue();
             
                if(uuid==uid&&ttype==type) {
                	 Cell cellsid = row.getCell(2);
                	 Cell cellfid = row.getCell(3);
                	 Cell cellwid = row.getCell(4);
                	 double valuessid = cellsid.getNumericCellValue();
                     int ssid = new Double(valuessid).intValue();
                     double valuesfid = cellfid.getNumericCellValue();
                     int ffid =new Double(valuesfid).intValue();
                     double valueswid = cellwid.getNumericCellValue();
                     int wwid = new Double(valueswid).intValue();
                	 
                	game.setSid(ssid);
                	game.setFid(ffid);
                	game.setWid(wwid);
                	
    
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
						
		
		
		
		
		
		return game;
	}

}
