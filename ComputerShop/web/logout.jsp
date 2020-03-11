<%-- 
    Document   : logout
    Created on : Mar 10, 2020, 8:56:32 PM
    Author     : hi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            HttpSession ses=request.getSession();
            ses.invalidate();
            response.sendRedirect("index.jsp");
        %>
    </body>
</html>
