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
public class userIndexServlet extends HttpServlet {

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
            out.println("<title>Servlet userIndexServlet</title>");
//            out.println("<script>");
//            out.println("function fn1() { \n"
//                    + "alert(\"hi\");\n"
//                    + "var inputVal = document.getElementById(\"txtqty\").value;"
//                    + "localStorage.setItem(\"storageName\",inputVal);\n"
//                    + "alert(localStorage.getItem(\"storageName\"));\n"
//                    + "window.location.href = \"http://localhost:8080/ComputerShop/addToCartServlet\""
//                    + "} ");
//            out.println("</script>");
            out.println("</head>");
            out.println("<body>");
            HttpSession session = request.getSession();
            out.println("<h2>welcome " + session.getAttribute("username") + " Your Id is " + session.getAttribute("userid") + "</h2>");
            out.println("<a href='/ComputerShop/sessionDestroyServlet'>Logout</a>");
            jdbcConnection obj = new jdbcConnection();
            out.println("<form id=\"frm\" name=\"frm\" method=\"post\">");
            out.println("<table border=\"1\" align=\"center\" cellpadding=\"10\" style=\"margin-top:150px;font-size: 20px \">");

            out.println("<tr>");
            out.println("<th>Item ID</th>");
            out.println("<th>Item NAME</th>");
            out.println("<th>Item Price</th>");
            out.println("<th>Category</th>");
            out.println("<th>Add To Cart</th>");
            out.println("</tr>");
            out.println("<form method='post' action='addToCartServlet'>");
            try {
                Statement st = obj.con.createStatement();
                ResultSet rs = st.executeQuery("select i.itemId,i.itemName,i.itemPrice,c.categoryName from item i,category c where i.categoryId=c.categoryId order by c.categoryId");
                int i=0;
                while (rs.next()) 
                {
                    i++;
                    out.println("<tr>");
                    out.println("<td>" + rs.getInt(1) + "</td>");
                    out.println("<td>" + rs.getString(2) + "</td>");
                    out.println("<td>" + rs.getInt(3) + "</td>");
                    out.println("<td>" + rs.getString(4) + "</td>");
                    out.println("<td> <a href='/ComputerShop/addToCartServlet?iid=" + rs.getInt(1) + "'> Add to cart </a> &nbsp;&nbsp;</td>");
                    out.println("</tr>");
                }
            } catch (Exception e) {
                out.println(e);
            }
            out.println("</form>");
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
