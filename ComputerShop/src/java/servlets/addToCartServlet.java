/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.jdbcConnection;

/**
 *
 * @author hi
 */
public class addToCartServlet extends HttpServlet {

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
            out.println("<title>Servlet addToCartServlet</title>");
            out.println("</head>");
            out.println("<body>");
//            String qty=request.getParameter("txtqty");
//            out.println("quntity is "+qty);
//            out.println("<input type='number' name='txtq' id='txtq' />");
//            out.println("<h1>Servlet addToCartServlet at " + request.getContextPath() + "</h1>");
            HttpSession session=request.getSession();
            int uid=(int) session.getAttribute("userid");
            int iid=Integer.parseInt(request.getParameter("iid"));
            jdbcConnection obj=new jdbcConnection();
            String sql="{call sp_insertCart(?,?,?)}";
            try {
                CallableStatement cs=obj.con.prepareCall(sql);
                cs.setInt(1, iid);
                cs.setInt(2, 1);
                cs.setInt(3, uid);
                int i=cs.executeUpdate();
                if(i>0)
                {
                    response.sendRedirect("cartServlet");
                }
                
            } catch (Exception e) {
                out.println(e);
            }
            
            out.println("</body>");
            out.println("</html>");
//            out.println("<script>");
//            //out.println("window.onload = alert(localStorage.getItem(\"storageName\"));");
//            out.println("document.getElementById('txtq').value=localStorage.getItem('storageName');");
//            out.println("localStorage.removeItem(\"storageName\"); ");
//            out.println("</script>");
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
