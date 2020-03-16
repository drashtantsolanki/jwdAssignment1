<%-- 
    Document   : userIndex
    Created on : Mar 10, 2020, 5:41:24 PM
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
        <a href="/ComputerShop/logout.jsp">Logout</a><br><br>
        <sql:setDataSource var="db" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/computershop" user="root" password="root" />
        <%
            String msg=null;
            HttpSession ses=request.getSession();
            out.println("Hello "+ses.getAttribute("username"));
            msg=(String)request.getAttribute("msg");
            if(msg!="" && msg!=null)
            {
                out.println(msg);
            }
        %>
        
        
        <table align="center" border="1">
            <tr>
                <td><c:out value="${userid}"/><a href="/ComputerShop/cart.jsp">CART</a></td>
            </tr>
                <tr>
                    <th>Id</th>
                    <th>Item</th>
                    <th>Price</th>
                    <th>Category</th>
                    <th>Update</th>
                </tr>
                
                <sql:setDataSource var="db" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/computershop" user="root" password="root" />
                <sql:query dataSource="${db}" var="rs1">
                    SELECT i.itemId,i.itemName,i.itemPrice,c.categoryName FROM  item i,category c where i.categoryId=c.categoryId
                </sql:query>
                <c:forEach var="i" items="${rs1.rows}">
                    <tr>
                        <td> <c:out value="${i.itemId}" /> </td>
                        <td> <c:out value="${i.itemName}" /> </td>
                        <td> <c:out value="${i.itemPrice}" /> </td>
                        <td> <c:out value="${i.categoryName}" /> </td>
                        <td> <a href="/ComputerShop/addToCart.jsp?iid=<c:out value='${i.itemId}' />"> Add to Cart</a> </td>
                    </tr>
                </c:forEach>
            </table>
    </body>
</html>
