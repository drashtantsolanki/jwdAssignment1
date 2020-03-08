/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hi
 */
public class RegisterServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            String passErr,tname,tbdate,temail="";
            passErr=(String) request.getAttribute("pass");
            tname=(String) request.getAttribute("name");
            tbdate=(String) request.getAttribute("bdate");
            temail=(String) request.getAttribute("email");
            
            if(passErr!=null)
            {
                out.println("<font color='red'>"+passErr+"</font>");
            }
            else
            {
                out.println("");
            }
            
            out.println("<form method=\"post\" action=\"insertUserServlet\">");
            out.println("<table border='1' align=\"center\" cellpadding=\"10\" style=\"margin-top:150px;font-size: 30px \">");
            out.println("<tr>");
            out.println("<th>Name</th>");
            if(tname==null)
            {
                out.println("<td><input style=\"font-size: 30px\" type='text' name='txtname' placeholder='Enter name' required></td>");
            }
            else
            {
                out.println("<td><input style=\"font-size: 30px\" type='text' name='txtname' value='"+tname+"'  placeholder='Enter name' required></td>");
            }
            out.println("</tr>");
            out.println("<tr>");
            out.println("<th>Birthdate</th>");
            
            if(tbdate==null)
            {
                out.println("<td><input style=\"font-size: 30px\" type='date' name='txtbdate'  placeholder='Enter Birthdate' required></td>");
            }
            else
            {
                out.println("<td><input style=\"font-size: 30px\" type='date' name='txtbdate' value='"+tbdate+"'  placeholder='Enter Birthdate' required></td>");
            }
            
            out.println("</tr>");
            out.println("<tr>");
            out.println("<th>Email</th>");
            if(temail==null)
            {
                out.println("<td><input style=\"font-size: 30px\" type='text' name='txtemail' placeholder='Enter Email' required></td>");
            }
            else
            {
                out.println("<td><input style=\"font-size: 30px\" type='text' name='txtemail' value='"+temail+"' placeholder='Enter Email' required></td>");
            }
            
            out.println("</tr>");
            out.println("<tr>");
            out.println("<th>Password</th>");
            out.println("<td><input style=\"font-size: 30px\" type='password' name='txtpassword' placeholder='Enter password' required></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<th>Confirm Password</th>");
            out.println("<td><input style=\"font-size: 30px\" type='password' name='txtconfirmpassword' placeholder='confirm password' required></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td colspan=\"2\" align='center'><input style=\"font-size: 30px\" type='submit' value='REGISTER'></td>");
            out.println("</tr>");
            
            out.println("</table>");
            out.println("</form>");
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
