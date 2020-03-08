/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author hi
 */
public class jdbcConnection {
     public Connection con;
    
    public jdbcConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/computershop","root","root");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
