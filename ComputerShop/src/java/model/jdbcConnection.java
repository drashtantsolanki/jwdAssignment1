/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Random;

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
    
    public String getRandomNumberString() {
    // It will generate 6 digit random Number.
    // from 0 to 999999
    Random rnd = new Random();
    int number = rnd.nextInt(999999);

    // this will convert any number sequence into 6 character.
    return String.format("g-%06d", number);
}
}
