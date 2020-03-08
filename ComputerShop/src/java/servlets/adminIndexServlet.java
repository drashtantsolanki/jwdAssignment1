/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
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
public class adminIndexServlet extends HttpServlet {

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
            out.println("<title>Servlet adminIndexServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<a href='/ComputerShop/sessionDestroyServlet'>Logout</a>");
            HttpSession session=request.getSession();
            out.println("<h2>"+session.getAttribute("username")+"</h2>");
            
            out.println("<table border=\"1\" align=\"center\" cellpadding=\"10\" style=\"margin-top:150px;font-size: 20px \">");
            out.println("<tr>");
            out.println("<th align='left' colspan='3'><a href='/ComputerShop/categoryFormServlet'>Add Category</a></th>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<th>Category ID</th>");
            out.println("<th>Category NAME</th>");
            out.println("<th>Update</th>");
            out.println("</tr>");
            jdbcConnection obj=new jdbcConnection();
            try {
                Statement st=obj.con.createStatement();
                ResultSet rs=st.executeQuery("select * from category");
                while (rs.next()) {                    
                    out.println("<tr>");        
                    out.println("<td>"+rs.getInt(1)+"</td>");
                    out.println("<td>"+rs.getString(2)+"</td>");
                    out.println("<td> <a href='/ComputerShop/categoryFormServlet?cid="+rs.getInt(1)+"'>update</a> </td>");
                    out.println("</tr>");
                }
            } catch (Exception e) {
                out.println(e);
            }
            
            
            out.println("<tr>");
            out.println("</tr>");
            out.println("</table>");

            
            
            
            out.println("<table border=\"1\" align=\"center\" cellpadding=\"10\" style=\"margin-top:150px;font-size: 20px \">");
            out.println("<tr>");
            out.println("<th align='left' colspan='5'><a href='/ComputerShop/itemFormServlet'>Add Item In Category</a></th>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<th>Item ID</th>");
            out.println("<th>Item NAME</th>");
            out.println("<th>Item Price</th>");
            out.println("<th>Category</th>");
            out.println("<th>Update</th>");
            out.println("</tr>");
            
            try {
                Statement st=obj.con.createStatement();
                ResultSet rs=st.executeQuery("select i.itemId,i.itemName,i.itemPrice,c.categoryName from item i,category c where i.categoryId=c.categoryId order by itemId");
                while (rs.next()) {                    
                    out.println("<tr>");        
                    out.println("<td>"+rs.getInt(1)+"</td>");
                    out.println("<td>"+rs.getString(2)+"</td>");
                    out.println("<td>"+rs.getInt(3)+"</td>");
                    out.println("<td>"+rs.getString(4)+"</td>");
                    out.println("<td><a href='/ComputerShop/categoryFormServlet?cid="+rs.getInt(1)+"'>update</a>  </td>");
                    out.println("</tr>");
                }
            } catch (Exception e) {
                out.println(e);
            }
            
            
            out.println("<tr>");
            out.println("</tr>");
            out.println("</table>");
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
