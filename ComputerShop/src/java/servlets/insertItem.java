/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.jdbcConnection;

/**
 *
 * @author hi
 */
public class insertItem extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) 
        {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet insertItem</title>");            
            out.println("</head>");
            out.println("<body>");
            
            String iid = "",iname,iprice,cid="";
            //iid=request.getParameter("txtitemId");
            iname=request.getParameter("txtitemname");
            iprice=request.getParameter("txtitemprice");
            cid=request.getParameter("txtcategory");
            
            if(!iid.equals(""))
            {
                System.out.println("in if");
                //update
            }
            else
            {
                jdbcConnection obj=new jdbcConnection();
                try {
                    PreparedStatement ps=obj.con.prepareStatement("insert into item(itemName,itemPrice,categoryId) value(?,?,?)");
                    ps.setString(1, iname);
                    ps.setString(2, iprice);
                    ps.setInt(3, Integer.parseInt(cid));
                    int i=ps.executeUpdate();
                    if(i>0)
                    {
                        response.sendRedirect("adminIndexServlet");
                    }
                } catch (Exception e) {
                    out.println(e);
                }
            }
            
            out.println("<h1>Servlet insertItem at " + request.getContextPath() + "</h1>");
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
