<%-- 
    Document   : addToCart
    Created on : Mar 10, 2020, 9:05:56 PM
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
        <%
            HttpSession ses=request.getSession();
        %>
        <sql:setDataSource var="db" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/computershop" user="root" password="root" />
        <sql:query dataSource="${db}" var="res">
            SELECT * FROM cart WHERE userId=?
            <sql:param value="${userid}" />
        </sql:query>
            <c:forEach var="i" items="${res.rows}">
                <c:out value="${i.itemId}"/>
                <c:out value="${i.userId}"/>
            </c:forEach>
            
            <c:forEach var="i" items="${res.rows}">
                <c:choose>
                    <c:when test="${i.itemId==param.iid}">
                        <%
                        request.setAttribute("msg", "Item exists");
                        response.sendRedirect("userIndex.jsp");
                        %>
                    </c:when>
                    <c:otherwise>
                        <sql:update dataSource="${db}" var="count">
                            INSERT INTO cart(itemId,quantity,userId) values(?,?,?)
                            <sql:param value="${param.iid}" />
                            <sql:param value="${1}" />
                            <sql:param value="${userid}" />
                        </sql:update>
                            <c:if test="${count>0}">
                                <%
                                    request.setAttribute("msg", "Item added into cart");
                                    response.sendRedirect("userIndex.jsp");
                                %>
                            </c:if>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            
    </body>
</html>
