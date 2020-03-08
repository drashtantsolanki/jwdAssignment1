/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.chillyfacts.com.utils.verifyRecaptcha;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.jdbcConnection;
//import model.verifyRecaptcha;


/**
 *
 * @author hi
 */
public class checkUserServlet extends HttpServlet {

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
            out.println("<title>Servlet checkUserServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            String email=request.getParameter("txtemail");
            String pass=request.getParameter("txtpass");
            
            HttpSession session=request.getSession(true);
            
            
            String gRecaptchaResponse=request.getParameter("g-recaptcha-response");
            System.out.println("grecaptcha="+gRecaptchaResponse);
            boolean verify=verifyRecaptcha.verify(gRecaptchaResponse);
            System.out.println("verify = "+verify);
//            if(!verify)
//            {
//                request.setAttribute("captcha","You missed captcha");
//                RequestDispatcher rd=request.getRequestDispatcher("index.html");
//                rd.include(request, response);                                        
//            }
            if(email.equals("admin") && pass.equals("admin"))
            {
                out.println("in admin");
                session.setAttribute("username", "Admin");
                session.setAttribute("userid", "1");
                
                response.sendRedirect("adminIndexServlet");
            }
            else
            {
                String sql="select * from users where Email='"+email+"' and Password='"+pass+"'";
                out.println(sql);
                jdbcConnection obj=new jdbcConnection();
                try {
                    Statement st=obj.con.createStatement();
                    ResultSet rs=st.executeQuery(sql);
                    if(rs.next())
                    {   
                        String name=rs.getString(2);
                        session.setAttribute("username",name);
                        session.setAttribute("userid", rs.getInt(1));
                        response.sendRedirect("userIndexServlet");
                    }
                    else
                    {
                        request.setAttribute("err","email or password incorrect");
                        RequestDispatcher rd=request.getRequestDispatcher("index.html");
                        rd.include(request, response);                        
                    }
                } catch (Exception e) {
                    out.println(e);
                }
            }
            out.println("<h1>Servlet checkUserServlet at " + request.getContextPath() + "</h1>");
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
