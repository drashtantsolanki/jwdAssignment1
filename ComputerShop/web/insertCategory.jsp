<%-- 
    Document   : insertCategory
    Created on : Mar 10, 2020, 6:09:19 PM
    Author     : hi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <sql:setDataSource var="db" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/computershop" user="root" password="root" />
        <c:choose>
        <c:when test="${param.txtcategoryId == 0}">
            <sql:update dataSource="${db}" var="count">
                INSERT INTO category(categoryName) VALUES(?)
                <sql:param value="${param.txtcategoryName}" />
            </sql:update>
                <c:choose>
                    <c:when test="${count>0}">
                        <% response.sendRedirect("adminIndex.jsp"); %>
                    </c:when>
                    <c:otherwise>
                        <% out.println("error in insert"); %>
                    </c:otherwise>
                </c:choose>
        </c:when>
        <c:otherwise>
            <sql:update dataSource="${db}" var="count">
                UPDATE Category set categoryName=? WHERE CategoryId=?
                <sql:param value="${param.txtcategoryName}" />
                <sql:param value="${param.txtcategoryId}" />
            </sql:update>
                <c:choose>
                    <c:when test="${count>0}">
                        <% response.sendRedirect("adminIndex.jsp"); %>
                    </c:when>
                    <c:otherwise>
                        <% out.println("error in update"); %>
                    </c:otherwise>
                </c:choose>
        </c:otherwise>
                
                
        </c:choose>
    </body>
</html>
