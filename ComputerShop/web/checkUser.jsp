<%-- 
    Document   : checkUser
    Created on : Mar 10, 2020, 5:54:26 PM
    Author     : hi
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="model.jdbcConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            HttpSession ses=request.getSession();
            String username,userid="";
            String user=request.getParameter("txtemail");
            String pass=request.getParameter("txtpass");
            if(user.equals("admin") && pass.equals("admin"))
            {
                ses.setAttribute("username","Admin");
                ses.setAttribute("userid", "1");
                response.sendRedirect("adminIndex.jsp");
            }
            else
            {
                jdbcConnection obj=new jdbcConnection();
                Statement st=obj.con.createStatement();
                ResultSet rs=st.executeQuery("SELECT * FROM USERS WHERE Email='"+user+"' AND Password='"+pass+"'");
                if(rs.next())
                {
                    String uid=rs.getString(1);
                    ses.setAttribute("username", rs.getString(2));
                    ses.setAttribute("userid",uid );
                    response.sendRedirect("userIndex.jsp");
                }
                else
                {
                    request.setAttribute("err","email or password incorrect");
                    RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
                    rd.include(request, response);                        
                }
                
            }
        %>
       <%-- <sql:setDataSource var="db" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/computershop" user="root" password="root"/>
        <sql:query dataSource="${db}" var="rs">
            SELECT * FROM USERS WHERE Email=? AND Password=?
            <sql:param value="${param.txtemail}" />
            <sql:param value="${param.txtpass}" />
        </sql:query>
             <c:forEach var="i" items="${rs.rows}">
                <c:out value="${i.Name}"/>
                <c:out value="${i.Email}"/>
            </c:forEach> --%>
       
       
            
        
        
    </body>
</html>