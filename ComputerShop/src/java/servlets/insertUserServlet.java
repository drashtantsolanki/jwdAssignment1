/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.jdbcConnection;

/**
 *
 * @author hi
 */
public class insertUserServlet extends HttpServlet {

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
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet insertUserServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            String userName=request.getParameter("txtname");
            String tempDate=request.getParameter("txtbdate");
//            DateFormat df=new SimpleDateFormat("dd/MM/yyyy");
//            String bdate=df.format(tempDate);
            String userEmail=request.getParameter("txtemail");
            String userpass=request.getParameter("txtpassword");
            String usercpass=request.getParameter("txtconfirmpassword");
            
            jdbcConnection obj=new jdbcConnection();
            if(userpass.equals(usercpass))
            {
                try 
                {
                    PreparedStatement ps=obj.con.prepareStatement("insert into users(Name,Birthdate,Email,Password) values(?,?,?,?)");
                    ps.setString(1, userName);
                    ps.setString(2, tempDate);
                    ps.setString(3, userEmail);
                    ps.setString(4, userpass);
                    int i= ps.executeUpdate();
                    if(i>0)
                    {
                        request.setAttribute("success", "Registration Successfull");
                        RequestDispatcher rd=request.getRequestDispatcher("index.html");
                        rd.include(request, response);
                    }
                    ps.close();
                }
                catch (Exception e) 
                {
                    out.println(e);
                }
            }
            else
            {
                request.setAttribute("pass", "Password And Confirm Password are not same!!");
                request.setAttribute("name", userName);
                request.setAttribute("bdate", tempDate);
                request.setAttribute("email", userEmail);
                RequestDispatcher rd=request.getRequestDispatcher("RegisterServlet");
                rd.include(request, response);
            }
            
            out.println("<h1>Servlet insertUserServlet at " + request.getContextPath() + "</h1>");
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(insertUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(insertUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
