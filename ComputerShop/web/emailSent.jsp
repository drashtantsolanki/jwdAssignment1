<%-- 
    Document   : emailSent
    Created on : Mar 11, 2020, 6:02:05 PM
    Author     : hi
--%>

<%@page import="model.SendMail"%>
<%@page import="model.jdbcConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <%
            jdbcConnection obj=new jdbcConnection();
            String otp=obj.getRandomNumberString();
            out.println(otp);
            String temail=request.getParameter("txtemail");
            HttpSession otpSession=request.getSession();
            otpSession.setAttribute("gotp", otp);
            otpSession.setAttribute("tempemail", temail);
            String to=request.getParameter("txtemail");
            String sub="Forgot Password";
            SendMail mail=new SendMail(to, sub, otp);
            out.println("mail sent");
        %>
        <form method="post" action="checkOTP.jsp">
            <input type="text" name="txtopt1" value="<%= otp%>" readonly>
            <input type="text" name="txtotp"  placeholder="Enter OTP" style="font-size: 30px;width: 250px;"/>
            <br><br>
            <input type="submit" value="OK" style="font-size: 30px;width: 250px;">
       </form>
    </body>
</html>
