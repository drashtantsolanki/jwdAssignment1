<%-- 
    Document   : addToCart
    Created on : Mar 10, 2020, 9:05:56 PM
    Author     : hi
--%>

<%@page import="java.sql.PreparedStatement"%>
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
            int iid=Integer.parseInt(request.getParameter("iid"));
        %>
        <%--     <sql:setDataSource var="db" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/computershop" user="root" password="root" />
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
            </c:forEach> --%>
            <%
                jdbcConnection obj=new jdbcConnection();
                Statement st=obj.con.createStatement();
                ResultSet res=st.executeQuery("select * from cart where itemid="+iid+" and userid="+uid );
                int i=0;
                while(res.next())
                {
                    i++;
                }
                if(i==0)
                {
                    PreparedStatement ps=obj.con.prepareStatement("insert into cart(itemid,quantity,userid) values(?,?,?)");
                    ps.setInt(1, iid);
                    ps.setInt(2, 1);
                    ps.setInt(3, uid);
                    int j=ps.executeUpdate();
                    if(j>0)
                    {
                        request.setAttribute("msg", "item added");
                        response.sendRedirect("userIndex.jsp");
                    }
                }
                else
                {
                    request.setAttribute("msg", "Item Already Exists");
                    response.sendRedirect("userIndex.jsp");
                }
                
            %>
    </body>
</html>
