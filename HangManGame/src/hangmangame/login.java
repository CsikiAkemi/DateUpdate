/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hangmangame;

import java.io.*;  
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
/**
 *
 * @author sisin
 */
public class login extends javax.swing.JFrame {

    /**
     * Creates new form login
     */
    public login() {
        initComponents();
        setSize(580,500);
        setLocation(200,200);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtjelszo = new javax.swing.JPasswordField();
        txtemail = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Email cím");

        jLabel2.setText("Jelszó");

        txtemail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtemailActionPerformed(evt);
            }
        });

        jButton1.setText("Bejelentkezes");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(239, 239, 239)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtjelszo, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txtemail))
                .addGap(36, 36, 36))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtjelszo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addComponent(jButton1)
                .addContainerGap(191, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
          java.sql.Connection con = null;
        PreparedStatement pst = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //Statement stmt=null;
        
       try{
           
            //Kapcsolat az adatbázishoz(webshopdatabase)
            con = DriverManager.getConnection("jdbc:mysql://localhost/webshopdatabase","root", ""); 
            String query = " SELECT * FROM user WHERE mail=? and jelszo=?"; 
            pst = con.prepareStatement(query);
            pst.setString(1,txtemail.getText());
            pst.setString(2,txtjelszo.getText());
            rs = pst.executeQuery();
            //Sikeres kapcsolat
            if(rs.next()){              
                String mail =(rs.getString("mail"));
                String jelszo =(rs.getString("jelszo"));
                
            //Jelszo és email cím validáció Sikeres
            
                if(jelszo.equals(jelszo) && mail.equals(mail)){
                    
                    try{
                    //A USER EMAIL MEZŐHÖZ KAPCSOLÓDÓ UTOLSÓ JÁTÉK DÁTUMA
                    stmt = con.prepareStatement (" SELECT last_game FROM user WHERE mail=? ");
                    stmt.setString(1, rs.getString("mail"));
                    ResultSet result=stmt.executeQuery();
                    Date myEndDate = null;
                    
                    
                    while(result.next())
                        myEndDate = result.getDate("last_game"); //KIOLVASOM ÉS VÁLTOZÓBA MENTEM A LAST_GAME DÁTUMÁT
                      //  System.out.println(result.getDate("last_game"));
                    
                    
                    //DÁTUMVIZSGÁLAT
                    //    https://www.baeldung.com/java-period-duration?fbclid=IwAR2axXo_ObrJXan1fEPB8UXMsQOLAvXcsIwUSHlKmLxT9Kjuy5dXHYZ5xUA#period-class
                    LocalDate startDate = LocalDate.now(); //AKTUÁLIS IDŐ
                    LocalDate endDate = LocalDate.parse(String.valueOf(myEndDate)); // -> LAST_GAME
                    Period period = Period.between(startDate, endDate); //A KETTŐ KÖZTI KÜLÖNBSÉG
                    System.out.println(period.getDays());
                    
                    
                    
                    if(period.getDays() > +5){
                        System.out.println("5 naptól 5-el több. Játszhatsz!");  //DEBUG
                    
                        //Hozzáad egy mai dátumot a LAST_GAME oszlophoz ahol az EMAIL = belépő EMAIL-el
                        stmt = con.prepareStatement (" UPDATE user SET last_game=? WHERE mail=? ");
                        
                        LocalDateTime localDateTime = LocalDateTime.now();
                        java.sql.Date last_game = java.sql.Date.valueOf(localDateTime.toLocalDate());
                        java.sql.Time time = java.sql.Time.valueOf(localDateTime.toLocalTime());
                        
                        stmt.setDate(1, last_game);
                        stmt.setString(2, rs.getString("mail"));
                        stmt.executeUpdate();
                    
                        new login().setVisible(false);
                        TheGame g=new TheGame();
                        g.setVisible(true);
                        JOptionPane.showMessageDialog(null, "Sikeres bejelentkezés");
                        
                        con.close();    
                     
                        
                    }else if(period.getDays() < -5){
                        System.out.println("5 naptól 5-el kevesebb. Játszhatsz!");
                        
                        //Hozzáad egy mai dátumot a LAST_GAME oszlophoz ahol az EMAIL = belépő EMAIL-el
                        stmt = con.prepareStatement (" UPDATE user SET last_game=? WHERE mail=? ");
                        
                        LocalDateTime localDateTime = LocalDateTime.now();
                        java.sql.Date last_game = java.sql.Date.valueOf(localDateTime.toLocalDate());
                        java.sql.Time time = java.sql.Time.valueOf(localDateTime.toLocalTime());
                        
                        stmt.setDate(1, last_game);
                        stmt.setString(2, rs.getString("mail"));
                        stmt.executeUpdate();
                    
                        new login().setVisible(false);
                        TheGame g=new TheGame();
                        g.setVisible(true);
                        JOptionPane.showMessageDialog(null, "Sikeres bejelentkezés");
                        
                        con.close(); 
                        
                    
                    }else{
                        System.out.println("5 naptól 5-el se több se kevesebb. Nem játszhatsz!");
                        //new login().setVisible(false);
                        JOptionPane.showMessageDialog(null, "Kevesebb mint öt napja játszottál!");   
                        TheGame g=new TheGame(); //Azért raktam bele mert nem engedett be és így tudom csinálni a pótszámlálást
                        g.setVisible(true);
                    }
                    
                    
                    }catch (SQLException e) {
                    e.printStackTrace();
                    
                        }
                        
                }
                
            //Sikertelen bejelentkezés
            
             }else{
            JOptionPane.showMessageDialog(null, "Sikertelen bejelentkezés, Próbáld újra!");
            new login().setVisible(true);
            txtemail.setText(" ");
            txtjelszo.setText(" ");
            }
            con.close();    
       }catch (SQLException e) {
         e.printStackTrace();
      }














        // TODO add your handling code here:
        //Kapcsolódás az adatbázishoz
        /*String felhasznalo,jelszo;
        felhasznalo=txtfelhasznalo.getText(); //emailes bejelentkezés szükséges 
        jelszo=txtjelszo.getText();
        //Egy vizsgálat hogy a megadott emailcim szerepel-e már aza datbázisban és ha igen akkor mikor játszott utoljára(mezőben lévő dátum vizsgálata )
        if(felhasznalo.equals("1") && jelszo.equals("1")){
            JOptionPane.showMessageDialog(null, "Sikeres bejelentkezés");
            
            TheGame g=new TheGame();
            g.setVisible(true);
            dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "Sikertelen bejelentkezés, Próbáld újra!");
            dispose();
            new login().setVisible(true);
        }
        //próba2
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","");
            String sql = "Select * from phpmyadmin wehre mail=? and jelszo=? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,txtemail.getText());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Sikeres bejelentkezés");   
        }
        //próba3
        //Scanner in =new Scanner(System.in);
        java.sql.Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        //Statement stmt=null;
        
       try{
            //Kapcsolat az adatbázishoz(webshopdatabase)
            con = DriverManager.getConnection("jdbc:mysql://localhost/webshopdatabase","root", ""); 
            String query = " SELECT * FROM user WHERE mail=? and jelszo=?"; 
            pst = con.prepareStatement(query);
            pst.setString(1,txtemail.getText());
            pst.setString(2,txtjelszo.getText());
            rs = pst.executeQuery();
            String mail =(rs.getString("mail"));
            String jelszo =(rs.getString("jelszo"));
            //Sikeres kapcsolat
         
                
                if(jelszo.equals(jelszo) && mail.equals(mail)){
                    PreparedStatement stmt = con.prepareStatement("UPDATE user SET last_game=? ");
                        LocalDateTime localDateTime = LocalDateTime.now();
                        java.sql.Date date = java.sql.Date.valueOf(localDateTime.toLocalDate());
                        java.sql.Time time = java.sql.Time.valueOf(localDateTime.toLocalTime());
                        stmt.setDate(1, date);
                        stmt.executeUpdate();
                        con.close();
                        JOptionPane.showMessageDialog(null, "Sikeres bejelentkezés és a dátum rögzült az adatbázisban");
                    Statement stmt = null;
                    stmt = con.createStatement();
                    String querry=("SELECT * FROM last_game WHERE mail = txtmail");
                    java.sql.Timestamp ts = rs.getTimestamp("last_game");
                    java.sql.Date  date      = new java.sql.Date(ts.getTime());  
                    java.util.Date dd = new java.util.Date(date.getTime());
                    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
                    String strDate = sdf.format(date); 
                    JOptionPane.showMessageDialog(null, "Mond hogy cső apu");
                    
                    //Ki kell formatálni date ből int típusú változó
                    
                    if( date1 == null ){
                       
                        pst = con.prepareStatement("UPDATE user SET last_game=last_game WHERE mail=mail ");
                        LocalDateTime localDateTime = LocalDateTime.now();
                        java.sql.Date last_game = java.sql.Date.valueOf(localDateTime.toLocalDate());
                        java.sql.Time time = java.sql.Time.valueOf(localDateTime.toLocalTime());
                        pst.setDate(1, last_game);
                        pst.executeUpdate();
                        DateFormat DateFormat= new SimpleDateFormat ("last_game");
                        
                        last_game.getDay();
                        last_game.getMonth();
                        last_game.getYear();
                        
                        con.close();//Lehet nem ide kéne
                    
                   
                    }else{
                   JOptionPane.showMessageDialog(null, "Mond hogy cső apu");
                    }
                    
                        
                       
                }else{
               JOptionPane.showMessageDialog(null, "Sikertelen bejelentkezés, Próbáld újra!");
               new login().setVisible(true);
               txtemail.setText(" ");
               txtjelszo.setText(" ");
               }
                
            con.close();  
             
       }catch (SQLException e) {
         e.printStackTrace();
        }
       //Itt vannak jó kódok
       //new login().setVisible(false);
                        //TheGame g=new TheGame();
                        //g.setVisible(true);
                        //JOptionPane.showMessageDialog(null, "Sikeres bejelentkezés");
                        //con.close();
                        //PreparedStatement stmt = con.prepareStatement("SELECT * FROM user WHERE last_game=?  ");
                        //String last_game = ("lg");
                        //System.out.printlm.("lg");
                        
                        //BadDate b=new BadDate();
                        //b.setVisible(true);
      try{
           
           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/webshopdatabase","root","");
           String s = " SELECT * FROM user";
           
           PreparedStatement ps=con.prepareStatement("s");
           ps.setString(1,txtemail.getText());
           ps.setString(2,txtjelszo.getText());
           ResultSet rs=ps.executeQuery();
           System.out.println(rs);
            
           
            
       }
       catch (SQLException e) {
         e.printStackTrace();
      }*/
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtemailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtemailActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtemail;
    private javax.swing.JPasswordField txtjelszo;
    // End of variables declaration//GEN-END:variables

    /*private static class last_game {

        public last_game() {
        LocalDate today = LocalDate.now();
 
        String formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MMM-yy")); //Ez is kiírja a mai dátumot
 
        
        }

    }*/
}
