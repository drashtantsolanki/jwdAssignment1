/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hi
 */
public class insertEmployee extends HttpServlet {

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
            out.println("<title>Servlet insertEmployee</title>");            
            out.println("</head>");
            out.println("<body>");
            
            String eid=request.getParameter("eid");
            if(eid!=null && eid!="")
            {
                try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost/employeedb","root","root");
                Statement st = con.createStatement();
                Statement st1 = con.createStatement();
                ResultSet rs=st1.executeQuery("select DISTINCT d.DeptId,d.DepartmentName from tbldepartment d");
                ResultSet rs1=st.executeQuery("select * from tblemployee where id="+eid);
                String ename="";
                int esalary=0;
                
                int did=0;
                while (rs1.next()) {                        
                    ename=rs1.getString(2);
                    esalary=rs1.getInt(3);
                    did=rs1.getInt(4);
                }
                out.println("<form action='insertingServlet' method='post'>");
                out.println("id:<input type='text' name='txteid' value='"+eid+"' readonly><br>");
                out.println("name:<input type='text' name='txtname' value='"+ename+"' required><br>");
                out.println("salary:<input type='number' name='txtsalary' value='"+esalary+"' required><br>");
                out.println("<select name='txtdept' required>");
                out.println("<option value='-1'>--Select--</option>");
                while(rs.next())
                {
                    out.println("<option value='"+rs.getInt(1)+"'>"+rs.getString(2)+"</option>");   
                }
                out.println("</select><br>");
                out.println("<input type='submit' value='Add'>");
                out.println("</form>");

                } catch (Exception e) {
                    
                    out.println(e);
                }
            }
            else
            {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost/employeedb","root","root");
                Statement st = con.createStatement();
                ResultSet rs=st.executeQuery("select DISTINCT d.DeptId,d.DepartmentName from tbldepartment d");
                out.println("<form action='insertingServlet' method='post'>");
                out.println("name:<input type='text' name='txtname' required><br>");
                out.println("salary:<input type='number' name='txtsalary' required><br>");
                out.println("<select name='txtdept' required>");
                out.println("<option value='-1'>--Select--</option>");
                while(rs.next())
                {
                    out.println("<option value='"+rs.getInt(1)+"'>"+rs.getString(2)+"</option>");
                }
                out.println("</select><br>");
                out.println("<input type='submit' value='Add'>");
                out.println("</form>");
            } catch (Exception e) {
                out.println("error in insertEmployee"+e);
            }
            
            }
            
            
            out.println("<h1>Servlet insertEmployee at " + request.getContextPath() + "</h1>");
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
