package com.bcht.axletempmonitor.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 导出excel
 * createdBy lazi 2018/08/23
 */
public class ExcelUtil {
    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    static int sheetsize = 5000;

    /**
     *
     * @param title 表名
     * @param headers 表头
     * @param dataset 数据
     * @return
     */
    public static InputStream exportExcel(String title, String[] headers, List<Object[]> dataset,String excelExtName) throws Exception {
        logger.info("导出excel ...exportExcel()...");
        InputStream is=null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        // 声明一个工作薄
        Workbook workbook=null;
        if("xls".equals(excelExtName)){
            workbook = new HSSFWorkbook();
        }else if("xlsx".equals(excelExtName)){
            workbook=new XSSFWorkbook();
        }else {
            throw new Exception("不是excel文件");
        }

        // 生成一个表格
        Sheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(15);
        // 生成一个样式
        CellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        Font font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        CellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        Font font2 = workbook.createFont();
        font2.setBoldweight(Font.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
       style2.setFont(font2);
        // 声明一个画图的顶级管理器
        Drawing patriarch = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置,详见文档
        //HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
          //      0, 0, 0, (short) 4, 2, (short) 6, 5));
        // 设置注释内容
        //comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
       // comment.setAuthor("lazi");
        // 产生表格标题行
        Row row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            //HSSFCell cell = row.createCell(i, i);
           Cell cell = row.createCell(i);
            cell.setCellStyle(style);
            RichTextString text=null;
            if("xls".equals(excelExtName)){
                text = new HSSFRichTextString(headers[i]);
            }else if("xlsx".equals(excelExtName)){
                text = new XSSFRichTextString(headers[i]);
            }
            cell.setCellValue(text);
        }
        // 遍历集合数据，产生数据行
        int index = 0;
        for(int i=1;i<dataset.size()+1;i++){
            row = sheet.createRow(i);
            for(int j=0;j<dataset.get(i-1).length;j++){
                Cell cell = row.createCell(j);
                cell.setCellStyle(style2);
                Object value=dataset.get(i-1)[j];
                if(value==null || value.equals("null(null)")||value.toString().contains("null")){
                    value="";
                }
                String textValue = null;
                if (value instanceof Boolean) {
                    boolean bValue = (Boolean) value;
                    if(bValue==true){
                        textValue="true";
                    }else{
                        textValue="false";
                    }
                }else if (value instanceof Date) {
                    Date date = (Date) value;
                    textValue=DateUtils.formatString(date,"yyyy-MM-dd HH:mm:ss");
                } else if (value instanceof byte[]) {
                    // 有图片时，设置行高为60px;
                    row.setHeightInPoints(60);
                    // 设置图片所在列宽度为80px,注意这里单位的一个换算
                    sheet.setColumnWidth(i, (short) (35.7 * 80));
                    // sheet.autoSizeColumn(i);
                    byte[] bsValue = (byte[]) value;
                    HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
                            1023, 255, (short) 7, index, (short) 7, index);
                    anchor.setAnchorType(2);
                    patriarch.createPicture(anchor, workbook.addPicture(
                            bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
                } else {
                    // 其它数据类型都当作字符串简单处理
                    textValue = value.toString();
                }
                // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                if (textValue != null) {
                    Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                    Matcher matcher = p.matcher(textValue);
                    if (matcher.matches()) {
                        // 是数字当作double处理
                        cell.setCellValue(Double.parseDouble(textValue));
                    } else {
                        /*HSSFRichTextString richString = new HSSFRichTextString(
                                textValue);*/
                        Font font3 = workbook.createFont();
                        font3.setColor(HSSFColor.BLUE.index);
                        //richString.applyFont(font3);
                        cell.setCellValue(textValue);
                    }
                }
            }
        }
        try {
            workbook.write(os);
            os.flush();
            is = new ByteArrayInputStream(os.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
     return is;
    }

    public static void ExportExcel(HttpServletResponse response, String[] headArray,
                                   List<Object[]> contentList, String fileName) throws Exception {
        logger.info("导出Excel... ExportExcel()....");
        // 读到流中
        InputStream inStream = exportExcel(fileName, headArray, contentList,"xlsx");
        // 设置输出的格式
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        //response.setContentType("application/octet-stream");
        //response.setContentType("bin");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xlsx").getBytes())) ;
        ServletOutputStream out = response.getOutputStream();
        bis = new BufferedInputStream(inStream);
        bos = new BufferedOutputStream(out);
        // 循环取出流中的数据
        byte[] b = new byte[2048];
        int len;
        try {
            while ((len = bis.read(b)) > 0){
                bos.write(b, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bis!=null){
                bis.close();
            }
            if(bos!=null){
                bos.close();
            }
            if(inStream!=null){
                inStream.close();
            }
        }
    }

    public static String[][] importExcel(MultipartFile file,String flag) throws Exception {
        Workbook workbook=null;
        String originalFilename = file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        List<String[]> result = new ArrayList<String[]>();
        if(originalFilename.endsWith("xls")){
            workbook=new HSSFWorkbook(inputStream);
        }else if(originalFilename.endsWith("xlsx")){
            workbook=new XSSFWorkbook(inputStream);
        }else {
            throw new Exception("不是excel文件");
        }
        Sheet sheet = workbook.getSheet("Sheet1");
        Row title = sheet.getRow(0);
        int titleColumn = title.getLastCellNum();

        if("01".equals(flag)){
            if(titleColumn!=6){
                String[][] errorStr={{"模版格式不正确"}};
                return errorStr;
            }
            for (int j = 0; j< titleColumn; j++){
                Cell cell=title.getCell(j);
                if(j==0 && !"故障代码".equals(getCellValue(cell))){
                    String[][] errorStr={{"标题行第一列为'故障代码'"}};
                    return errorStr;
                }
                if(j==1 && !"故障名称".equals(getCellValue(cell))){
                    String[][] errorStr={{"标题行第二列为'故障名称'"}};
                    return errorStr;
                }
                if(j==2 && !"故障等级".equals(getCellValue(cell))){
                    String[][] errorStr={{"标题行第三列为'故障等级'"}};
                    return errorStr;
                }
                if(j==3 && !"故障原因".equals(getCellValue(cell))){
                    String[][] errorStr={{"标题行第四列为'故障原因'"}};
                    return errorStr;
                }
                if(j==4 && !"车型".equals(getCellValue(cell))){
                    String[][] errorStr={{"标题行第五列为'车型'"}};
                    return errorStr;
                }
                if(j==5 && !"故障系统".equals(getCellValue(cell))){
                    String[][] errorStr={{"标题行第六列为'故障系统'"}};
                    return errorStr;
                }
            }
        }

        int rownums = sheet.getLastRowNum();//总共多少行
        if(rownums==0){
            String[][] errorStr={{"表格内容为空，请填写数据"}};
            return errorStr;
        }
        int columns=0;
        Cell cell=null;
        for(int i=1;i<rownums+1;i++){
            Row row = sheet.getRow(i);
            if("01".equals(flag)){
                columns=6; //故障代码导入 有6列
            }
           // columns=row.getLastCellNum()+1;//总共多少列
            String[] values=new String[columns];//每一行 每一列值
            Arrays.fill(values,"");//数组vaules初始化 ""
            boolean hasValue=false;
            for(int j=0;j<columns;j++){
                cell=row.getCell(j);
                String value=getCellValue(cell);
                /*if(j==0 && value.trim().equals("")){
                    break;
                }*/
                values[j]=rightTrim(value);
                hasValue=true;
            }
            if (hasValue) {
                result.add(values);
            }
        }
        inputStream.close();
        String[][] returnArray = new String[result.size()][columns];
        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = (String[]) result.get(i);
        }
        return returnArray;
    }

    //取单元格值
    public static String getCellValue(Cell cell){
        String value="";
        if (null != cell) {
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_STRING:
                    value = cell.getStringCellValue();
                    break;
                case HSSFCell.CELL_TYPE_NUMERIC:
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        if (null != date) {
                            value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                        } else {
                            value = "";
                        }
                    } else {
                        DecimalFormat df = new DecimalFormat("0.#######");
                        //value = df.format(cell.getNumericCellValue());
                        value = String.valueOf(df.format(cell.getNumericCellValue()));
                    }
                    break;
                case HSSFCell.CELL_TYPE_FORMULA:
                    // 公式生成的数据
                    if (!"".equals(cell.getStringCellValue())) {
                        value = cell.getStringCellValue();
                    } else {
                        value = cell.getNumericCellValue() + "";
                    }
                    break;
                case HSSFCell.CELL_TYPE_BLANK:
                    break;
                case HSSFCell.CELL_TYPE_ERROR:
                    value = "";
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN:
                    value = (cell.getBooleanCellValue() == true ? "Y" : "N");
                    break;
                default:
                    value = "";
            }
        }
        return value;
    }

    /**
     * 去除字符串右侧空格
     * @param str 字符串
     * @return String
     */
    public static String rightTrim(String str) {
        if (str == null) {
            return "";
        }
        int length = str.length();
        for (int i = length - 1; i >= 0; i--) {
            if (0x20 != str.charAt(i)) {
                break;
            }
            length--;
        }
        return str.substring(0, length);
    }



}
