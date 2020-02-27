/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Employee;

/**
 *
 * @author hi
 */
public class indexServlet extends HttpServlet {

    Connection con;
    
    
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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet indexServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            
            
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost/employeedb","root","root");
                Statement st=con.createStatement();
                ResultSet rs1=st.executeQuery("select * from tbldepartment");
                out.println("<a href='/empDeptQue/insertDept'>AddDepartment</a><br><br>");
                    out.println("<table border='1'>");
                    out.println("<tr>");
                    out.println("<th>ID</th>");
                    out.println("<th>NAME</th>");
                    out.println("</tr>");
                    
                    while (rs1.next()) {                    
                    out.println("<tr>");
                    out.println("<td>"+rs1.getInt(1)+"</td>");
                    out.println("<td>"+rs1.getString(2)+"</td>");
                    out.println("<td><a href='/empDeptQue/insertDept?did="+rs1.getInt(1)+"'>Update</a></td>");
                    out.println("<td><a href='/empDeptQue/deleteDept?did="+rs1.getInt(1)+"'>Delete</a></td>");
                    out.println("</tr>");
                }
                    
                    out.println("</table>");
                    
                    
                    
                out.println("<br><br><a href='/empDeptQue/insertEmployee'>ADD Employee</a>");
                out.println("<br><br>");
                ResultSet rs2=st.executeQuery("select e.id,e.name,e.salary,d.DepartmentName from tblemployee e,tbldepartment d where e.deptid=d.deptid");
                out.println("<table border='1'>");
                out.println("<tr>");
                out.println("<th>ID</th>");
                out.println("<th>NAME</th>");
                out.println("<th>SALARY</th>");
                out.println("<th>DepartMent</th>");
                out.println("</tr>");
                while (rs2.next()) {                    
                    out.println("<tr>");
                    out.println("<td>"+rs2.getInt(1)+"</td>");
                    out.println("<td>"+rs2.getString(2)+"</td>");
                    out.println("<td>"+rs2.getInt(3)+"</td>");
                    out.println("<td>"+rs2.getString(4)+"</td>");
                    out.println("<td> <a href='/empDeptQue/insertEmployee?eid="+rs2.getInt(1)+"'>update</a> </td>");
                    out.println("<td> <a href='/empDeptQue/deleteEmployee?eid="+rs2.getInt(1)+"'>Delete</a> </td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                
                
            } catch (Exception e) {
                out.println(e);
            }
            
            ResultSet res = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost/employeedb","root","root");
                Statement st1=con.createStatement();
                res=st1.executeQuery("select * from tbldepartment");
            } catch (Exception e) {
                out.println(e);
            }
            
            out.println("<br><br><form method='post' action='search1Servlet'>");
            out.println("<br><br><table border='1'>");
            out.println("<tr>");
            out.println("<td>");
            out.println("<select name='txtdept'>");
            out.println("<option value='-1'>--select department--</option>");
            while (res.next()) {                
                out.println("<option value='"+res.getInt(1)+"'>"+res.getString(2)+"</option>");
            }
            out.println("</select>");
            out.println("</td>");
            out.println("<td><input type='submit' value='search'></td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("</form>");
            
            ArrayList<Employee> lst=new ArrayList<>();
            lst=(ArrayList<Employee>) request.getAttribute("lstEmp");
                for (Employee emp : lst) {
                    out.println("Employee Name="+emp.getName()+" Employee Salary="+emp.getSalary()+"<br>");
                }
            
            
                
            out.println("<hr>");
            out.println("<h1>Departmen wise Total Salary</h1>");
            String sp="{call sp_getTotalSalaryOfDepartment}";
                try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost/employeedb","root","root");
                CallableStatement cs=con.prepareCall(sp);
                ResultSet rs=cs.executeQuery();
                out.println("<h3>Department name    |   Salary<h3>");
                    while (rs.next()) {                        
                        out.println("<br>   "+rs.getString(1)+"    |   "+rs.getInt(2));
                    }
            } catch (Exception e) {
                out.println(e);
            }
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
        } catch (SQLException ex) {
            Logger.getLogger(indexServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(indexServlet.class.getName()).log(Level.SEVERE, null, ex);
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
