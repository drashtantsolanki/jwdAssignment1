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
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hi
 */
public class insertingServlet extends HttpServlet {

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
            out.println("<title>Servlet insertingServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            String dname=request.getParameter("txtdeptname");
            String did=request.getParameter("txtdeptid");
            String eid=request.getParameter("txteid");
            String name=request.getParameter("txtname");
            int salary=Integer.parseInt(request.getParameter("txtsalary"));
            int deptid=Integer.parseInt(request.getParameter("txtdept"));
            if(did!=null && did!="")
            {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost/employeedb","root","root");
                    PreparedStatement ps=con.prepareStatement("update tbldepartment set departmentname=? where deptid=?");
                    ps.setString(1, dname);
                    ps.setInt(2, Integer.parseInt(did));
                    ps.execute();
                    con.close();
                    RequestDispatcher rd=request.getRequestDispatcher("indexServlet");
                    rd.include(request, response);

                } catch (Exception e) {
                    out.println(e);
                }
            }
            
            if(eid!=null && eid!="")
            {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost/employeedb","root","root");
                    PreparedStatement ps=con.prepareStatement("update tblemployee set name=?,salary=?,deptid=? where id=?");
                    ps.setString(1, name);
                    ps.setInt(2, salary);
                    ps.setInt(3, deptid);
                    ps.setInt(4, Integer.parseInt(eid));
                    ps.execute();
                    con.close();
                    RequestDispatcher rd=request.getRequestDispatcher("indexServlet");
                    rd.include(request, response);
                    
                } catch (Exception e) {
                    out.println(e);
                }
            }
            
            
            if(dname!="" && dname!=null)
            {
                out.println(dname);
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost/employeedb","root","root");
                    PreparedStatement ps=con.prepareStatement("insert into tbldepartment(DepartmentName) values(?)");
                    ps.setString(1, dname);
                    ps.execute();
                    con.close();
                    RequestDispatcher rd=request.getRequestDispatcher("indexServlet");
                    rd.include(request, response);

                    } catch (Exception e) {
                        out.println(e);
                    }
            }
            else
            {
            String ename=request.getParameter("txtname");
            int esalary=Integer.parseInt(request.getParameter("txtsalary"));
            int edept=Integer.parseInt(request.getParameter("txtdept"));    
                try 
                {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost/employeedb","root","root");
                    PreparedStatement ps=con.prepareStatement("insert into tblemployee(name,salary,deptid) values(?,?,?)");
                    ps.setString(1, ename);
                    ps.setInt(2, esalary);
                    ps.setInt(3, edept);
                    ps.execute();
                    con.close();
                    RequestDispatcher rd=request.getRequestDispatcher("indexServlet");
                    rd.include(request, response);
                } catch (Exception e) {
                    out.println(e);
                }
            }
            
            
            out.println("<h1>Servlet insertingServlet at " + request.getContextPath() + "</h1>");
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
