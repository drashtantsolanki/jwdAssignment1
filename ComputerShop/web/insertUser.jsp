<%-- 
    Document   : insertUser
    Created on : Mar 11, 2020, 8:16:51 AM
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
            <c:when test="${param.txtpass == param.txtcpass}">
                <sql:update dataSource="${db}" var="count">
                    INSERT INTO Users(Name,Birthdate,Email,Password) VALUES(?,?,?,?)
                    <sql:param value="${param.txtname}" />
                    <sql:param value="${param.txtbdate}" />
                    <sql:param value="${param.txtemail}" />
                    <sql:param value="${param.txtpass}" />
                </sql:update>
                    <c:if test="${count>0}">
                        <%
                        response.sendRedirect("index.jsp");
                        %> 
                    </c:if>
            </c:when>
            <c:otherwise>
                <%
                    request.setAttribute("err","password and confirm password are not same");
                    response.sendRedirect("userForm.jsp");
                %>
            </c:otherwise>
        </c:choose>
    </body>
</html>
