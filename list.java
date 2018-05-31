/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gudiya
 */
public class list extends HttpServlet {

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
             out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">");
            out.println("<title>Servlet list</title>");            
            out.println("</head>");
            out.println("<body>");
            try{
                Connection cn = null;
                Statement st = null;
                Class.forName("com.mysql.jdbc.Driver");
                cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hm", "root", "student");
                st = cn.createStatement();
                ResultSet rs;
                rs = st.executeQuery("select * from login");
                out.println("<table border='1' align='center' width='50%' class='table table-striped table-hover'><tr><th colspan='5'><a href='insert.html'>Insert new user</a></th></tr><tr><th>USERNAME</th><th>EMAIL ID</th><th>AGE</th><th colspan='2'>OPERATION</th></tr>");
                while(rs.next())
                {
                    out.println("<tr><td>"+rs.getString("uname")+"</td><td>"+rs.getString("mailid")+"</td><td>"+rs.getString("age")+"</td><td><a href='update1?uname="+rs.getString("uname")+"'>Update</a></td><td><a href='delete?uname="+rs.getString("uname")+"'>Delete</a></td></tr>");
                }
            }
            catch(ClassNotFoundException e)
            {
                out.println("Class not found exception");
            }
            catch(SQLException e)
            {
                out.println("SQL exception");
            }
            catch(Exception e)
            {
                out.println("General exception");
            }
            /* TODO output your page here. You may use following sample code. */
           
            //out.println("<h1>Servlet list at " + request.getContextPath() + "</h1>");
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
