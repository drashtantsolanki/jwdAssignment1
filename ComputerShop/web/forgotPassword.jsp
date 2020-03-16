<%-- 
    Document   : forgotPassword
    Created on : Mar 11, 2020, 5:56:00 PM
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
        <form method="post" action="emailSent.jsp">
            <input type="text" name="txtemail" placeholder="Enter Your Register Email" style="font-size: 30px;width: 500px;"> &nbsp;
            <input type="submit" value="Send OTP" style="font-size: 30px;width: 250px;"/>
        </form>
    </body>
</html>
