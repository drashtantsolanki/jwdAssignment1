<%-- 
    Document   : itemForm
    Created on : Mar 10, 2020, 6:45:02 PM
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
    <sql:setDataSource var="db" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/computershop" user="root" password="root" />
    <body>
        <form method="post" action="insertItem.jsp">
        <c:choose>
            <c:when test="${param.iid>0}">
                <sql:query dataSource="${db}" var="rs">
                    select * from item where itemId=?
                    <sql:param value="${param.iid}"/>
                </sql:query>
            <c:forEach var="i" items="${rs.rows}">
                <c:out value='${i.itemPrice}' />
            <table align="center" border="1">
                <tr>
                    <th colspan="2">
                        <input type="text" name="txtid" value="<c:out value='${i.itemId}' />" readonly>
                    </th>
                </tr>
                <tr>
                    <th>
                        Name:<input type="text" name="txtname" value="<c:out value='${i.itemName}' />">
                    </th>
                </tr>
                <tr>
                    <th>
                        Price:<input type="text" name="txtpice" value="<c:out value='${i.itemPrice}' />">
                    </th>
                </tr>

                <tr>                    
                    <sql:query dataSource="${db}" var="res">
                        select * from category
                    </sql:query>
                    <th>
                        Category:
                        <select name="txtcategory">
                            <option value="-1">select Category</option>
                            <c:forEach var="j" items="${res.rows}">
                                <c:choose>
                                <c:when test="${i.categoryId==j.categoryId}">
                                    <option value="<c:out value='${j.categoryId}' />" selected> <c:out value="${j.categoryName}" /> </option>
                                </c:when>
                                <c:otherwise>
                                    <option value="<c:out value='${j.categoryId}' />"> <c:out value="${j.categoryName}" /> </option>
                                </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </th>
                </tr>
                <tr>
                    <th colspan="2">
                        <input type="submit" value="Add">
                    </th>
                </tr>
            </table>
            </c:forEach>  
                    
                    
                    
                    
                    
                    
            </c:when>
        <c:otherwise>
            <table align="center" border="1">
                <tr>
                    <th colspan="2">
                        <input type="text" name="txtid" value="0" readonly>
                    </th>
                </tr>
                <tr>
                    <th>
                        Name:<input type="text" name="txtname">
                    </th>
                </tr>
                <tr>
                    <th>
                        Price:<input type="text" name="txtpice">
                    </th>
                </tr>

                <tr>                    
                    <sql:query dataSource="${db}" var="res">
                        select * from category
                    </sql:query>
                    <th>
                        Category:
                        <select name="txtcategory">
                            <option value="-1">select Category</option>
                            <c:forEach var="i" items="${res.rows}">
                                <option value="<c:out value='${i.categoryId}' />"> <c:out value="${i.categoryName}" /> </option>
                            </c:forEach>
                        </select>
                    </th>
                </tr>
                <tr>
                    <th colspan="2">
                        <input type="submit" value="Add">
                    </th>
                </tr>
            </table>
        </c:otherwise>
        </c:choose>
        </form>
    </body>
</html>
