<%-- 
    Document   : userForm
    Created on : Mar 11, 2020, 8:09:59 AM
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
        <form method="post" action="insertUser.jsp">
        <table align="center" border="1">
            <tr>
                <td>Name</td>
                <td><input type="text" name="txtname" required/> </td>
            </tr>
            <tr>
                <td>BirthDdate</td>
                <td> <input type="date" name="txtbdate" required/> </td>
            </tr>
            <tr>
                <td>Email</td>
                <td> <input type="text" name="txtemail" required/> </td>
            </tr>
            <tr>
                <td>password</td>
                <td> <input type="password" name="txtpass" required/> </td>
            </tr>
            <tr>
                <td>confirm password</td>
                <td> <input type="password" name="txtcpass" required/> </td>
            </tr>
            <tr align="center">
                
                <td colspan="2"> <input type="submit" value="Register" /> </td>
            </tr>
        </table>
        </form>
    </body>
</html>
