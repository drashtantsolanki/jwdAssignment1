<%-- 
    Document   : index
    Created on : Mar 10, 2020, 6:09:22 PM
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
        <%
            String err=null;
            String otperr=null;
            err=(String)request.getAttribute("err");
            otperr=(String)request.getAttribute("otperr");
            if(err!="" && err!=null)
            {
                out.println(err);
            }
            
            if(otperr!="" && otperr!=null)
            {
               out.println(otperr);
            }
            
        %>
        
       <form method="post" action="checkUser.jsp">
        <table border="1" align="center">
            <tr>
                <td>Email</td>
                <td><input type="text" name="txtemail"/> </td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="txtpass"/> </td>
            </tr>
            <tr>
                <td><input type="submit" value="LOGIN"/> </td>
                <td> <a href="/ComputerShop/userForm.jsp">Not Register yet..?</a> </td>
            </tr>
            <tr>
                <td> <a href="/ComputerShop/forgotPassword.jsp"> Forgot Password?? </a> </td>
            </tr>
            
        </table>
        </form>
    </body>
</html>
