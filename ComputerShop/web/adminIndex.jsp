<%-- 
    Document   : adminIndex
    Created on : Mar 10, 2020, 5:56:17 PM
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
        <sql:setDataSource var="db" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/computershop" user="root" password="root" />
        <%
            HttpSession ses=request.getSession();
            out.println("Hello "+ses.getAttribute("username"));
        %>
        
        <a href="/ComputerShop/logout.jsp">Logout</a>
        <table align="center" border="1">
            <tr>
                <td colspan="2">
                    <a href="/ComputerShop/categoryForm.jsp">Add Category</a>
                </td>
            </tr>
            <tr>
                <th>Category ID</th>
                <th>Category Name</th>
            </tr>
            
            <sql:query dataSource="${db}" var="rs">
                SELECT * FROM Category
            </sql:query>
            
                <c:forEach var="i" items="${rs.rows}">
                    <tr>
                        <td> <c:out value="${i.categoryId}" /> </td>
                        <td> <c:out value="${i.categoryName}" /> </td>
                        <td> <a href="/ComputerShop/categoryForm.jsp?cid=<c:out value='${i.categoryId}'/>">update</a> </td>
                    </tr>
                </c:forEach>
        </table>
            <br><br>
            <table align="center" border="1">
                <tr>
                    <td colspan="5">
                        <a href="/ComputerShop/itemForm.jsp">Add Item</a>
                    </td>
                </tr>
                <tr>
                    <th>Id</th>
                    <th>Item</th>
                    <th>Price</th>
                    <th>Category</th>
                    <th>Update</th>
                </tr>
                
                
                <sql:query dataSource="${db}" var="rs1">
                    SELECT i.itemId,i.itemName,i.itemPrice,c.categoryName FROM  item i,category c where i.categoryId=c.categoryId
                </sql:query>
                <c:forEach var="i" items="${rs1.rows}">
                    <tr>
                        <td> <c:out value="${i.itemId}" /> </td>
                        <td> <c:out value="${i.itemName}" /> </td>
                        <td> <c:out value="${i.itemPrice}" /> </td>
                        <td> <c:out value="${i.categoryName}" /> </td>
                        <td> <a href="/ComputerShop/itemForm.jsp?iid=<c:out value='${i.itemId}' />">Update</a> </td>
                    </tr>
                </c:forEach>
            </table>
                <br><br>
                
            <table align="center" border="1">
                <tr>
                    <th>id</th>
                    <th>session id</th>
                    <th>creation time</th>
                    <th> last accessed time</th>
                    <th> username </th>
                    <th> visits </th>
                </tr>
                <sql:query dataSource="${db}" var="res">
                    SELECT log.id,log.sessionId,log.creationTime,log.lastAccessedTime,users.Name,log.visit from log,users WHERE log.userId=users.UserId
                </sql:query>
                <c:forEach var="i" items="${res.rows}">
                    <tr>
                        <td> <c:out value="${i.id}" /> </td>
                        <td> <c:out value="${i.sessionId}" /> </td>
                        <td> <c:out value="${i.creationTime}" /> </td>
                        <td> <c:out value="${i.lastAccessedTime}" /> </td>
                        <td> <c:out value="${i.Name}" /> </td>
                        <td> <c:out value="${i.visit}" /> </td>
                    </tr>
                </c:forEach>
            </table>
    </body>
</html>
