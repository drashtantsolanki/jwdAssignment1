<%-- 
    Document   : cart
    Created on : Mar 14, 2020, 4:54:45 PM
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
            int uid=Integer.parseInt((String)ses.getAttribute("userid"));
        %>
        <%--  <sql:setDataSource var="db" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/computershop" user="root" password="root" />
        <c:out value="${uid}"/>
        <sql:query dataSource="${db}" var="res">
            SELECT item.ItemName,cart.Quantity from item,cart WHERE item.ItemId=cart.ItemId and userid=?
            <sql:param value="${uid}" />
        </sql:query>
            <table>
                <tr>
                    <td>
                        Item name
                    </td>
                    <td>Quantity</td>
                </tr>
            <c:forEach var="i" items="${res.rows}">
                <tr>
                    <td> <c:out value="${i.ItemName}"/></td>
                    <td> <c:out value="${i.Quantity}"/></td>
                </tr>
            </c:forEach>
            </table> --%>
        
        <%
            jdbcConnection obj=new jdbcConnection();
            Statement st=obj.con.createStatement();
            ResultSet rs=st.executeQuery("SELECT item.ItemName,cart.Quantity from item,cart WHERE item.ItemId=cart.ItemId and userid="+uid);
        %>
        <table>
                <tr>
                    <td>
                        Item name
                    </td>
                    <td>Quantity</td>
                </tr>
                <% while(rs.next()){ %>
                <tr>
                    <td> <% out.println(rs.getString(1)); %> </td>
                    <td> <% out.println(rs.getInt(2)); %> </td>
                </tr>
                    
                <% } %>
        </table>
    </body>
</html>
