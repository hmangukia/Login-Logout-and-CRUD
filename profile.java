/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
/**
 *
 * @author DELL
 */
public class profile extends HttpServlet {

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
            try{
                
            Connection cn = null;
            Statement st = null;
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hm", "root", "student");
            Cookie[] ck=request.getCookies();
            String s1=ck[0].getName();
            String s2=ck[0].getValue();
            st = cn.createStatement();
            ResultSet rs;
            rs = st.executeQuery("select * from login");    
       //     String u=request.getParameter("user");
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">");
            out.println("<title>Profile</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>Welcome user...</h3>");
             out.println("<table class='table table-primaray' border='1' align='center' width='50%' class='table table-striped table-hover'><tr><th>USERNAME</th><th>EMAIL ID</th><th>AGE</th></tr>");
             while(rs.next())
                {   if(rs.getString("uname").equals(s2))
                    out.println("<tr><td>"+rs.getString("uname")+"</td><td>"+rs.getString("mailid")+"</td><td>"+rs.getString("age")+"</td></tr>");
                }
           // out.println("<h1>Servlet profile at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            }catch(ClassNotFoundException e)
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
        Cookie ck[]=request.getCookies();
        out.println(ck[0]);
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
