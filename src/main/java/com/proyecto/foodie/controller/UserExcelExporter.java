package com.proyecto.foodie.controller;
import java.io.IOException;
import java.util.List;
 

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.proyecto.foodie.model.Usuarios;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
 
public class UserExcelExporter {
	
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Usuarios> listUsers;
     
    public UserExcelExporter(List<Usuarios> listUsers) {
        this.listUsers = listUsers;
        workbook = new XSSFWorkbook();
    }
 
 
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Users");
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(row, 0, "User ID", style);      
        createCell(row, 1, "dni Usuario", style);       
        createCell(row, 2, "nombre Usuario", style);    
        createCell(row, 3, "apellidos Usuario", style);
        createCell(row, 4, "telefono Usuario", style);
        createCell(row, 5, "correo Electronico", style);
        createCell(row, 6, "tipo Usuario", style);
         
    }
     
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
     
    private void writeDataLines() {
        int rowCount = 1;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
                 
        for (Usuarios user : listUsers) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, user.getIdUsuario(), style);
            createCell(row, columnCount++, user.getDniUsuario(), style);
            createCell(row, columnCount++, user.getNombreUsuario(), style);
            createCell(row, columnCount++, user.getApellidosUsuario().toString(), style);
            createCell(row, columnCount++, user.getTelefonoUsuario(), style);
            createCell(row, columnCount++, user.getCorreoElectronico(), style);
            createCell(row, columnCount++, user.getTipoUsuario(), style);
             
        }
    }
     
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }
}