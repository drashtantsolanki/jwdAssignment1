<%-- 
    Document   : insertItem
    Created on : Mar 10, 2020, 6:55:57 PM
    Author     : hi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <sql:setDataSource var="db" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/computershop" user="root" password="root" />
        <c:choose>
        <c:when test="${param.txtid==0}">            
            <sql:update var="count" dataSource="${db}">
                insert into item(itemname,itemprice,categoryid) values(?,?,?)
                <sql:param value="${param.txtname}"/>
                <sql:param value="${param.txtpice}"/>
                <sql:param value="${param.txtcategory}"/>
            </sql:update>
                <c:choose>
                    <c:when test="${count>0}">
                        <% response.sendRedirect("adminIndex.jsp"); %>
                    </c:when>
                    <c:otherwise>
                        <% out.println("Error in insert"); %>
                    </c:otherwise>
                </c:choose>
        </c:when>
        <c:otherwise>
            <sql:update var="count" dataSource="${db}">
                update item set itemName=?,itemPrice=?,categoryId=? where itemId=?
                <sql:param value="${param.txtname}"/>
                <sql:param value="${param.txtpice}"/>
                <sql:param value="${param.txtcategory}"/>
                <sql:param value="${param.txtid}"/>
            </sql:update>
                <c:choose>
                    <c:when test="${count>0}">
                        <% response.sendRedirect("adminIndex.jsp"); %>
                    </c:when>
                    <c:otherwise>
                        <% out.println("Error in update"); %>
                    </c:otherwise>
                </c:choose>
        </c:otherwise>
        </c:choose>
    </body>
</html>
