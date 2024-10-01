/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import configuration.ConnectionBD;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Carlos Eugenio
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login_servlet"})
public class LoginServlet extends HttpServlet {
    
    Connection conn; 
    PreparedStatement ps; 
    Statement statement; 
    ResultSet rs; 

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(false); 
        
        if(session != null){
            session.invalidate(); 
        }
        Cookie [] cookies = request.getCookies(); 
        if(cookies != null){
            for (Cookie cookie : cookies){
                if("matricula".equals(cookie.getName())){
                    cookie.setMaxAge(0); 
                    cookie.setPath("/"); 
                    response.addCookie(cookie); 
                    break; 
                }
            }
        }
        response.sendRedirect("/cookies/JSP/login.jsp");
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
        String matricula = request.getParameter("matricula");
        String password = request.getParameter("password");
        
        try{
            String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt()); 
            ConnectionBD conexion = new ConnectionBD(); 
            conn = conexion.getConnectionBD(); 
            
            String sql = "SELECT password FROM autenticacion WHERE matricula = ?";
            ps = conn.prepareStatement(sql); 
            ps.setString(1, matricula);
            
            rs = ps.executeQuery(); 
            
            if(rs.next()){
                String hashPasword = rs.getString("password"); 
                if(BCrypt.checkpw(password, hashPassword)){
                    Cookie cookie = new Cookie("matricula", matricula); 
                    
                    if(request.getParameter("recordar")!= null && request.getParameter("recordar").equals("on")){
                        cookie.setMaxAge(60 * 60 *24);
                    } else {
                        cookie.setMaxAge(60 * 5); 
                    }
                    cookie.setPath("/");
                    
                    response.addCookie(cookie); 
                    request.getRequestDispatcher("/JSP/usuario.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/JSP/error.jsp").forward(request, response);
                }
            }else {
                    request.getRequestDispatcher("/JSP/error.jsp").forward(request, response);
                }
            
            rs.close(); 
            ps.close(); 
            conn.close(); 
            
        }catch (Exception e){
            System.out.println("Error" + e);
        }
        
        
        
        
        
        
        
        
        
        
        
        
        if("014429037".equals(matricula) && "12345".equals(password)){
            request.getRequestDispatcher("/JSP/usuario.jsp").forward(request, response);
            
            try {
                if (request.getParameter("recordar") != null && request.getParameter("recordar").equals("on")){
                    Cookie cookie = new Cookie("matricula", matricula);
                    cookie.setMaxAge(60 * 5);
                    response.addCookie(cookie); 
                }else{
                    Cookie cookie = new Cookie("matricula", matricula);
                    cookie.setMaxAge(60 * 1);
                    response.addCookie(cookie); 
                }
            }catch (Exception e) {
                System.out.println("Error" + e);
            }
        }else {
            request.getRequestDispatcher("/JSP/error.jsp").forward(request, response);
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
