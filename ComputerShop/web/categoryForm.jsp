<%-- 
    Document   : categoryForm
    Created on : Mar 10, 2020, 6:06:54 PM
    Author     : hi
--%>

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
        
        <form action="insertCategory.jsp" method="post">
            <c:choose>
                <c:when test="${param.cid>0}">
                    <sql:setDataSource var="db" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/computershop" user="root" password="root" />
                    <sql:query dataSource="${db}" var="res">
                        SELECT * FROM Category where categoryId=?
                        <sql:param value="${param.cid}" />
                    </sql:query>
                        <c:forEach var="i" items="${res.rows}">
                            
                            <input type="text" name="txtcategoryId" value="<c:out value='${i.categoryId}' />" readonly/><br><br>
                            Name:<input type="text" name="txtcategoryName" value="<c:out value='${i.categoryName}' />" /><br><br>
                        </c:forEach>
                    
                    <input type="submit" value="add"/>
                </c:when>
                <c:otherwise>
                    <input type="text" name="txtcategoryId" value="0" readonly/><br><br>
                    Name:<input type="text" name="txtcategoryName" /><br><br>
                    <input type="submit" value="add"/>
                </c:otherwise>
            </c:choose>
        </form>
    </body>
</html>
