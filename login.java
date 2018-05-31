/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
/**
 *
 * @author DELL
 */
public class login extends HttpServlet {

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
            try{
                out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>login</title>");            
            out.println("</head>");
            out.println("<body>");
            String username,password;
            Connection cn = null;
            Statement st = null;
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hm", "root", "student");
            st = cn.createStatement();
            username=request.getParameter("u");
            password=request.getParameter("p");
            ResultSet rs;
            rs = st.executeQuery("select * from login");
            Cookie ck= new Cookie("uname",username);
            response.addCookie(ck);
            while(rs.next())
            {
                if(username.equals(rs.getString("uname"))&&password.equals(rs.getString("pwd")))
                {
                    RequestDispatcher rd=request.getRequestDispatcher("main");
                    rd.forward(request,response);
                }
                else if(username.equals(rs.getString("uname")) && !password.equals(rs.getString("pwd")))
                {
                    out.println("<b> You have entered incorrect password </b>");
                    RequestDispatcher rd=request.getRequestDispatcher("index.html");
                    rd.include(request,response);
            }
            }
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
            
          //  out.println("<h1>Servlet login at " + request.getContextPath() + "</h1>");
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
