/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import model.jdbcConnection;

/**
 * Web application lifecycle listener.
 *
 * @author hi
 */
public class HttpAttributeListener implements  HttpSessionAttributeListener {

    jdbcConnection obj=new jdbcConnection();
    
    
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        String userid=null;
        Format f=new SimpleDateFormat("yyyy-MM-dd");
        String sessionId=event.getSession().getId();
        
        Date d=new Date(event.getSession().getCreationTime());
        String fct=f.format(d);
        
        Date d1=new Date(event.getSession().getLastAccessedTime());
        String flat=f.format(d1);
        
        userid=(String) event.getSession().getAttribute("userid");
        try {
            Statement st=obj.con.createStatement();
            ResultSet rs=st.executeQuery("select * from log where userId="+userid);
            if(rs.next())
            {
                int visits=rs.getInt(6);
                PreparedStatement ps=obj.con.prepareStatement("update log set sessionId=?,lastAccessedTime=?,visit=? where userId=?");
                ps.setString(1, sessionId);
                ps.setString(2, flat);
                ps.setInt(3, visits+1);
                ps.setString(4, userid);
                ps.executeUpdate();
            }
            else
            {
                PreparedStatement ps1=obj.con.prepareStatement("insert into log(sessionId,creationTime,lastAccessedTime,userId,visit) values(?,?,?,?,?)");
                ps1.setString(1, sessionId);
                ps1.setString(2, fct);
                ps1.setString(3, flat);
                ps1.setString(4, userid);
                ps1.setInt(5, 1);
                ps1.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
    }
}
