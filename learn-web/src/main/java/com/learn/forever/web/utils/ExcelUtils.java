package com.learn.forever.web.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @className: ExcelUtils
 * @author: Jaxling
 * @date: 2019/6/12 21:55
 * @version: 1.0
 * @E-mail: lixu@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 */
public class ExcelUtils {
    /**
     * 根据文件后缀获取excel读取对象
     *
     * @param multipartFile
     * @return Workbook
     * @throws IOException
     */
    public static Workbook getWorkbook(MultipartFile multipartFile) throws IOException {

        String ext = getFileSuffix(multipartFile.getOriginalFilename());
        Workbook workbook = null;
        //2003
        if ("xls".equalsIgnoreCase(ext)) {
            workbook = new HSSFWorkbook(multipartFile.getInputStream());
            //2007
        } else if ("xlsx".equalsIgnoreCase(ext)) {
            workbook = new XSSFWorkbook(multipartFile.getInputStream());
        }
        return workbook;
    }

    /**
     * 获取值
     */
    public static String getValue(Cell cell) {
        if (cell == null) {
            return null;
        }

        String value;
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    //日期型
                    value = DateFormatUtils.format(cell.getDateCellValue(), "yyyy-MM-dd hh:mm:ss");
                } else {
                    HSSFDataFormatter dataFormatter = new HSSFDataFormatter();
                    value = dataFormatter.formatCellValue(cell);
                }
                break;
            //文本类型
            case Cell.CELL_TYPE_STRING:
                value = cell.getStringCellValue();
                break;
            //布尔型
            case Cell.CELL_TYPE_BOOLEAN:
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            //空白
            case Cell.CELL_TYPE_BLANK:
                value = cell.getStringCellValue();
                break;
            //错误
            case Cell.CELL_TYPE_ERROR:
                value = "错误";
                break;
            //公式
            case Cell.CELL_TYPE_FORMULA:
                try {
                    value = String.valueOf(cell.getStringCellValue());
                } catch (IllegalStateException e) {
                    value = String.valueOf(cell.getNumericCellValue());
                }
                break;
            default:
                value = cell.getRichStringCellValue() == null ? null : cell.getRichStringCellValue().toString();
        }
        return null != value ? value.trim() : null;
    }

    /**
     * 获取文件名后缀播不包括.
     *
     * @param fileName
     * @return xls
     */
    private static String getFileSuffix(String fileName) {
        int dot = fileName.lastIndexOf(".");
        if ((dot > -1) && (dot < (fileName.length() - 1))) {
            return fileName.substring(dot + 1).toLowerCase();
        }
        return fileName;
    }

    /**
     * 给单元格设置批注
     *
     * @param workbook
     * @param cell
     * @param commentStr
     */
    public static void setCellComment(Workbook workbook,Cell cell,String commentStr) {

        Drawing patriarch = workbook.getSheetAt(0).createDrawingPatriarch();

        Comment comment = cell.getCellComment();
        if (comment == null) {
            if (cell instanceof HSSFCell) {
                comment = patriarch.createCellComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 1, 2, (short) 4, 4));
            } else {
                comment = patriarch.createCellComment(new XSSFClientAnchor(0, 0, 0, 0, (short) 1, 2, (short) 4, 4));
            }
        }

        RichTextString richTextString = comment.getString();
        String oldComment = (richTextString == null || StringUtils.isEmpty(richTextString.getString())) ? commentStr : richTextString.getString().concat("\n").concat(commentStr);


        //2003的批注
        if (richTextString instanceof HSSFRichTextString) {
            comment.setString(new HSSFRichTextString(oldComment));
        } else {
//            CreationHelper helper = workbook.getCreationHelper();
//            comment.setString( helper.createRichTextString(oldComment));

            comment.setString(new XSSFRichTextString(oldComment));
        }
        cell.setCellComment(comment);
    }


    /**
     * 标识单元格错误
     *
     * @param workbook
     * @param cell
     * @param commentStr
     */
    public static void setError(Workbook workbook,Cell cell,String commentStr) {
//        System.out.println(cell.getCellStyle().getFillBackgroundColor());
        //如果未设红背景
        if (IndexedColors.RED.getIndex() != cell.getCellStyle().getFillBackgroundColor()) {
            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.RED.getIndex());
//            style.setFillPattern(CellStyle.SOLID_FOREGROUND);
            cell.setCellStyle(style);
        }

        Drawing patriarch = workbook.getSheetAt(0).createDrawingPatriarch();

        Comment comment = cell.getCellComment();
        if (comment == null) {
            if (cell instanceof HSSFCell) {
                comment = patriarch.createCellComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 1, 2, (short) 4, 4));
            } else {
                comment = patriarch.createCellComment(new XSSFClientAnchor(0, 0, 0, 0, (short) 1, 2, (short) 4, 4));
            }
        }

        RichTextString richTextString = comment.getString();
        String oldComment = (richTextString == null || StringUtils.isEmpty(richTextString.getString())) ? commentStr : richTextString.getString().concat("\n").concat(commentStr);
        //2003的批注
        if (richTextString instanceof HSSFRichTextString) {
            comment.setString(new HSSFRichTextString(oldComment));
        } else {
            comment.setString(new XSSFRichTextString(oldComment));
        }
        cell.setCellComment(comment);
    }

    /**
     * 设置单元格数据有限性
     *
     * @param sheet
     * @param textlist
     * @param firsRow
     * @param lastRow
     * @param firstCol
     * @param lastCol
     */
    public static void setCellValidation(Sheet sheet,String[] textlist,int firsRow,int lastRow,int firstCol,int lastCol) {
        DataValidationHelper helper = sheet.getDataValidationHelper();
        // 加载下拉列表内容
        DataValidationConstraint constraint = helper.createExplicitListConstraint(textlist);
        constraint.setExplicitListValues(textlist);
        // 设置数据有效性加载在哪个单元格上。
        // 四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firsRow, lastRow, firstCol, lastCol);
        // 数据有效性对象
        DataValidation dataValidation = helper.createValidation(constraint, regions);
        sheet.addValidationData(dataValidation);

//        //加载下拉列表内容
//        DVConstraint constraint = DVConstraint.createExplicitListConstraint(textlist);
//        //设置数据有效性加载在哪个单元格上。
//        //四个参数分别是：起始行、终止行、起始列、终止列
//        CellRangeAddressList regions = new CellRangeAddressList(firsRow, lastRow, firstCol, lastCol);
//        //数据有效性对象
//        HSSFDataValidation dataValidationList = new HSSFDataValidation(regions, constraint);
//        sheet.addValidationData(dataValidationList);
    }

}
