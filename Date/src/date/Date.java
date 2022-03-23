/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package date;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;  
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.*;
import java.text.*;


/**
 *
 * @author Akemi
 */
public class Date {

    private static String last_game;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Jó működés, az adatbázisba beszúrásra kerül az aznapi dátum csak az a baj hogy minden dátumot lecserél
        java.sql.Connection con = null; 
        ResultSet rs = null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/webshopdatabase","root", ""); 
            PreparedStatement stmt = con.prepareStatement("UPDATE user SET last_game=? ");
            LocalDateTime localDateTime = LocalDateTime.now();
            java.sql.Date date = java.sql.Date.valueOf(localDateTime.toLocalDate());
            java.sql.Time time = java.sql.Time.valueOf(localDateTime.toLocalTime());
            stmt.setDate(1, date);
            //date.toString = (eper);
            stmt.executeUpdate();
             if(date == date){
                JOptionPane.showMessageDialog(null, "fasza!");
             }else{
                 
             }
            con.close();
            System.out.println(date);
            }catch (SQLException e) {
                e.printStackTrace();
        }
       /* ResultSet rs = null;
        java.sql.Connection con = null;
        Statement stmt = null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/webshopdatabase","root", ""); 
            
            //LocalDateTime localDateTime = LocalDateTime.now();
            //java.sql.Date date = java.sql.Date.valueOf(localDateTime.toLocalDate());
            //java.sql.Time time = java.sql.Time.valueOf(localDateTime.toLocalTime());
            stmt = con.createStatement();
                    rs = stmt.executeQuery("select last_game from user");
                    while (rs.next()) {
                        
                        java.sql.Timestamp ts = rs.getTimestamp("last_game");
                        java.sql.Date  date      = new java.sql.Date(ts.getTime());  
                        //java.util.Date dd = new java.util.Date(date.getTime());*/
                       //LocalDateTime localDateTime = LocalDateTime.now();
                        //java.sql.Date date = java.sql.Date.valueOf(localDateTime.toLocalDate());
                        //java.sql.Time time = java.sql.Time.valueOf(localDateTime.toLocalTime());
                        //SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
                        //System.out.println(sdf);
            
            
                    }/*
                        
                        
                        con.close();
        }catch (SQLException e) {
                e.printStackTrace();
        }
         //localdate megadása 
    //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    //LocalDateTime now = LocalDateTime.now();
    
    //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
    //String date = "16/08/2016";
        

//Get LocalDate from SQL date
        


    
        //Insert into date to database
       
        //PreparedStatement pst = null;
        
       
       //stmt.setString(2, name=? );
       
       //Próba 2 
       /*try{
       con = DriverManager.getConnection("jdbc:mysql://localhost/webshopdatabase","root", ""); 
       String sql = "insert into user(id, last_game) values(?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			//pstmt.setInt(1, 6);
			java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
			pstmt.setDate(2, sqlDate);
			//pstmt.executeUpdate();
       		pstmt.close();
    		con.close();*/
        //PRÓba1
        
        /*java.util.Date date=new java.util.Date();
        java.sql.Date sqlDate=new java.sql.Date(date.getTime());
        java.sql.Timestamp sqlTime=new java.sql.Timestamp(date.getTime());
        con.prepareStatement("UPDATE user SET last_game=sqlDate WHERE last_game=?");
        pst.setDate(1,sqlDate);
        pst.setTimestamp(2,sqlTime);
        pst.executeUpdate();
        pst.close();
        con.close();*/
            //PreparedStatement stmt = con.prepareStatement("UPDATE user SET last_game=? WHERE name=?");
            //con = DriverManager.getConnection("jdbc:mysql://localhost/webshopdatabase","root", ""); 
            //LocalDate localDate = LocalDate.now();
            //java.sql.Date sqlDateTime = java.sql.Date.valueOf( localDate );
            //LocalDateTime localDateTime = LocalDateTime.now();
            //java.sql.Date date = java.sql.Date.valueOf(localDateTime.toLocalDate());
            //java.sql.Time time = java.sql.Time.valueOf(localDateTime.toLocalTime());
            //String query = "INSERT INTO last_game VALUES (?)";
            //PreparedStatement pstmt = con.prepareStatement(query);
            //pstmt.setDate(1,date);
            //PreparedStatement stm = con.prepareStatement("INSERT INTO  user (last_game) VALUES(TO_DATE( '17/12/2015', 'DD/MM/YYYY')); ");
            //System.out.println( sqlDateTime );
            //stmt.setString(1,"2022.04.20");
            //stmt.setString(2, "Valaki");
            
            /*stmt.executeUpdate();
            //String name =(rs.getString(""));
            String str="last_game";
            boolean last_game = false;
            if(last_game == last_game){
                 JOptionPane.showMessageDialog(null, "Van adat és nem jó");
          
            }else{
                JOptionPane.showMessageDialog(null, "Fasza minden");
        }     */
        
      
        /*java.sql.Connection con = null;
        PreparedStatement pst = null;
       
          try{
        con = DriverManager.getConnection("jdbc:mysql://localhost/webshopdatabase","root", ""); 
        Statement stmt = con.createStatement ( ) ;
       
           String sql = "INSERT INTO last_game ( date_ ) " + "VALUES (?) ;";
            try ( PreparedStatement preparedStatement = con.prepareStatement ( sql ) ; ) {
                LocalDate today = LocalDate.now ( ZoneId.of ( "Hungarian" ) );
                preparedStatement.setObject ( 1, today.minusDays ( 1 ) );  // Yesterday.
                preparedStatement.executeUpdate ( );
                 stmt.execute ( sql );
            }
        }catch (SQLException e) {
         e.printStackTrace();
        }*/
        
    
}
