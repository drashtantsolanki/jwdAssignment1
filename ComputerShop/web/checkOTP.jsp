<%-- 
    Document   : checkOTP
    Created on : Mar 11, 2020, 8:06:20 PM
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
            String tempotp=null;
            HttpSession otpSession=request.getSession();
            String totp=(String)otpSession.getAttribute("gotp");
            
            //tempotp=request.getParameter("txtotp1");
            String cotp=request.getParameter("txtotp");
            out.println(totp+" "+cotp);
            if(totp.equals(cotp))
            {%>
            <form method="post" action="changePassword.jsp">
                New Password: <input type="password" name="txtpass" placeholder="password" style="font-size: 30px;width: 300px;" required/> <br>
                confirm password:<input type="password" name="txtcpass" placeholder="confirm password" style="font-size: 30px;width: 300px;" required/> <br>
            <input type="submit" value="save"/>
            </form>
            <% } else {%>
                <% 
                    out.println("in else");
                    request.setAttribute("otperr","otp is incorrect");
                    response.sendRedirect("index.jsp");
                %>
            <% } %>
    </body>
</html>
