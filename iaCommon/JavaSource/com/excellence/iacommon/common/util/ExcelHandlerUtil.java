package com.excellence.iacommon.common.util;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
  
  
  
  /** 
  * @ClassName: excelHandlerUtil 
  * @Description: Excel导入导出工具类
  * @author wangjg
  * @date 2017-7-25 
  *  
  */
  public class ExcelHandlerUtil {
      
      /** 
      * @Title: createWorkbook 
      * @Description: 判断excel文件后缀名，生成不同的workbook 
      * @param  is
      * @param  excelFileName
      * @throws IOException
      * @return Workbook
      * @throws 
      */
      public static Workbook createWorkbook(InputStream is,String excelFileName) throws IOException{
          if (excelFileName.endsWith(".xls")) {
              return new HSSFWorkbook(is);
          }else if (excelFileName.endsWith(".xlsx")) {
              return new XSSFWorkbook(is);
          }
          return null;
      }
  
      /** 
      * @Title: getSheet 
      * @Description: 根据sheet索引号获取对应的sheet
      * @param  workbook
      * @param  sheetIndex
      * @return Sheet
      * @throws 
      */
      public static Sheet getSheet(Workbook workbook,int sheetIndex){
          return workbook.getSheetAt(0);        
      }
      
      /** 
      * @Title: importDataFromExcel 
      * @Description: 将sheet中的数据保存到list中，
      * 、调用此方法时，vo的属性个数必须和excel文件每行数据的列数相同且一一对应，vo的所有属性都为String
      * 、在action调用此方法时，需声明 
      *     private File excelFile;上传的文件
      *     private String excelFileName;原始文件的文件名
      * 、页面的file控件name需对应File的文件名
      * @param  vo javaBean
      * @param  is 输入流
      * @param  excelFileName
      * @return List<Object>
      * @throws 
      */
      @SuppressWarnings("deprecation")
	  public static List<Object> importDataFromExcel(Object vo,InputStream is,String excelFileName){
          List<Object> list = new ArrayList<Object>();
          try {
              //创建工作簿
              Workbook workbook = createWorkbook(is, excelFileName);
              //创建工作表sheet
              Sheet sheet = getSheet(workbook, 0);
              //获取sheet中数据的行数
              int rows = sheet.getPhysicalNumberOfRows();
              //获取表头单元格个数
              int cells = sheet.getRow(0).getPhysicalNumberOfCells();
              
              SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
              //利用反射，给JavaBean的属性进行赋值
             Field[] fields = vo.getClass().getDeclaredFields();
             for (int i = 1; i < rows; i++) {//第一行为标题栏，从第二行开始取数据
                 Row row = sheet.getRow(i);
                 if(row != null){
                	 int index = 0;
                     Object vo1 = vo.getClass().getConstructor(new Class[]{}).newInstance(new Object[]{});//重新创建一个vo对象
                     while (index < cells) {
                         Cell cell = row.getCell(index);
                         if (null == cell) {
                             cell = row.createCell(index);
                         }
                         String value = "";
                         int type = cell.getCellType();
                         //数字类型（0）,字符串（1）
                         switch (type) {
    						case 0:
    							if (HSSFDateUtil.isCellDateFormatted(cell)) {  //判断日期类型
    								value =	sdf.format(cell.getDateCellValue()); 
    				             }else{
    				            	 value = cell.getStringCellValue()==null?"":String.valueOf(cell.getStringCellValue());
    				             }
    							break;
    						case 1:
    							value = cell.getStringCellValue()==null?"":cell.getStringCellValue().trim();
    							break;
    						default:
    							break;
    					 }
                         Field field = fields[index];
                         String fieldName = field.getName();
                         String methodName = "set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
                         Method setMethod = vo1.getClass().getMethod(methodName, new Class[]{String.class});
                         setMethod.invoke(vo1, new Object[]{value});
                         index++;
                     }
                     if (isHasValues(vo1)) {//判断对象属性是否有值
                         list.add(vo1);
                     }
                 }
             }
         } catch (Exception e) {
         }finally{
             try {
                 is.close();//关闭流
             } catch (Exception e) {
             }
         }
         return list;
         
     }
     
     /** 
     * @Title: isHasValues 
     * @Description: 判断一个对象所有属性是否有值，如果一个属性有值(分空)，则返回true
     * @param  object
     * @return boolean
     * @throws 
     */
     public static boolean isHasValues(Object object){
         Field[] fields = object.getClass().getDeclaredFields();
         boolean flag = false;
         for (int i = 0; i < fields.length; i++) {
             String fieldName = fields[i].getName();
             String methodName = "get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
             Method getMethod;
             try {
                 getMethod = object.getClass().getMethod(methodName);
                 Object obj = getMethod.invoke(object);
                 if (obj != null && !"".equals(obj)) {
                     flag = true;
                     break;
                 }
             } catch (Exception e) {
             }
             
         }
         return flag;
         
     }
     
     public static HSSFWorkbook exportDataToExcel(List<Object> list,String[] headers,String title){
         HSSFWorkbook workbook = new HSSFWorkbook();
         //生成一个表格
         HSSFSheet sheet = workbook.createSheet(title);
         //设置表格默认列宽15个字节
         //sheet.setDefaultColumnWidth(15);
         sheet.setColumnWidth(0, 100 * 150);
         sheet.setColumnWidth(1, 100 * 100);
         sheet.setColumnWidth(2, 100 * 100);
         //生成一个样式
         HSSFCellStyle style = getCellStyle(workbook);
         //生成一个字体
         HSSFFont font = getFont(workbook);
         //把字体应用到当前样式
         style.setFont(font);
         
         //生成表格标题
         HSSFRow row = sheet.createRow(0);
         row.setHeight((short)300);
         HSSFCell cell = null;
         
         for (int i = 0; i < headers.length; i++) {        
             cell = row.createCell(i);
             cell.setCellStyle(style);
             HSSFRichTextString text = new HSSFRichTextString(headers[i]);
             cell.setCellValue(text);
         }
         
         //将数据放入sheet中
         for (int i = 0; i < list.size(); i++) {
             row = sheet.createRow(i+1);
             Object t = list.get(i);
             //利用反射，根据JavaBean属性的先后顺序，动态调用get方法得到属性的值
             Field[] fields = t.getClass().getDeclaredFields();
             try {
                 for (int j = 0; j < fields.length; j++) {
                     cell = row.createCell(j);
                     Field field = fields[j];
                     String fieldName = field.getName();
                     String methodName = "get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
                     Method getMethod = t.getClass().getMethod(methodName,new Class[]{});
                     Object value = getMethod.invoke(t, new Object[]{});
                     
                     if(null == value)
                         value ="";
                     cell.setCellValue(value.toString());
                     if(j > headers.length -1){
                    	 break;
                     }
                     
                 }
             } catch (Exception e) {
             }
         }
         
         return workbook;
         /*try {
             workbook.write(os);
         } catch (Exception e) {
         }finally{
             try {
                 os.flush();
                 os.close();
             } catch (IOException e) {
             }
         }*/
         
     }
     
     /** 
     * @Title: getCellStyle 
     * @Description: 获取单元格格式
     * @param  workbook
     * @return HSSFCellStyle
     * @throws 
     */
     @SuppressWarnings("deprecation")
	public static HSSFCellStyle getCellStyle(HSSFWorkbook workbook){
         HSSFCellStyle style = workbook.createCellStyle();
         style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
         style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
         style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
         style.setBorderTop(HSSFCellStyle.BORDER_THIN);
         style.setLeftBorderColor(HSSFCellStyle.BORDER_THIN);
         style.setRightBorderColor(HSSFCellStyle.BORDER_THIN);
         style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
         
         return style;
     }
 
     /** 
     * @Title: getFont 
     * @Description: 生成字体样式
     * @param  workbook
     * @return HSSFFont
     * @throws 
     */
     public static HSSFFont getFont(HSSFWorkbook workbook){
         HSSFFont font = workbook.createFont();
         font.setColor(HSSFColor.WHITE.index);
         font.setFontHeightInPoints((short)12);
         font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
         return font;
     }
     
     public static boolean isIE(HttpServletRequest request){
    	 return request.getHeader("USER-AGENT").toLowerCase().indexOf("msie")>0?true:false;     
     }
 }