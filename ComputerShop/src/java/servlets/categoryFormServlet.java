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
import model.jdbcConnection;

/**
 *
 * @author hi
 */
public class categoryFormServlet extends HttpServlet {

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
            out.println("<title>Servlet categoryFormServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            String err=null;
            String tempCname="";
            String cid="";
            int tempCid=0;
            cid=request.getParameter("cid");
            jdbcConnection obj=new jdbcConnection();
            try {
                Statement st=obj.con.createStatement();
                ResultSet rs=st.executeQuery("select * from category where categoryId="+cid);
                if(rs.next())
                {
                    tempCname=rs.getString(2);
                    tempCid=rs.getInt(1);
                }
            } catch (Exception e) {
                out.println(e);
            }
            err=(String) request.getAttribute("err");
            if(err!="" && err!=null)
            {
                out.println("<font color='red'>"+ err +"</font>");
            }
            out.println("<form method='post' action='insertCategory'>");
            
            if(tempCid>0)
            {
                out.println("<input type='text' name='txtcid' value='"+tempCid+"' readonly><br><br>");
            }
            else
            {
                out.println("<input type='text' name='txtcid' readonly><br><br>");
            }
            if(tempCname=="" && tempCname==null)
            {
                out.println("Category Name:<input type='text' name='txtcname'><br><br>");
            }
            else
            {
                out.println("Category Name:<input type='text' name='txtcname' value='"+tempCname+"'><br><br>");
            }
            
            out.println("<input type='submit' value='ADD'>");
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
