<%-- 
    Document   : changePassword
    Created on : Mar 11, 2020, 8:44:23 PM
    Author     : hi
--%>

<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page import="model.jdbcConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            HttpSession otpSession=request.getSession();
            String email=(String)otpSession.getAttribute("tempemail");
            String pass=(String)request.getParameter("txtpass");
            String cpass=(String)request.getParameter("txtcpass");
//            out.println(email+pass+cpass);
            if(pass.equals(cpass))
            {
                jdbcConnection obj=new jdbcConnection();
                PreparedStatement ps=obj.con.prepareStatement("update users set password=? where email=?");
                ps.setString(1, pass);
                ps.setString(2, email);
                int i=ps.executeUpdate();
                if(i>0)
                {
                    response.sendRedirect("index.jsp");
                }
                else
                {
                    out.println("error");
                }
                
                
            }

        %>
        
    </body>
</html>
