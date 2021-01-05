/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5Algorithms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lab5Algorithms.model.Node;
import lab5Algorithms.model.Algorithm;
import static lab5Algorithms.model.Algorithm.Algo.AVERAGE;
import static lab5Algorithms.model.Algorithm.Algo.BAD;
import static lab5Algorithms.model.Algorithm.Algo.GOOD;
import static lab5Algorithms.model.Algorithm.Algo.VERY_BAD;
import static lab5Algorithms.model.Algorithm.Algo.VERY_GOOD;

/**
 * Class that checks the user's choice and print the information about his satisfaction of the website
 * @author Emilia Gosiewska
 * version 5.0
 */
@WebServlet(name = "EnumServlet", urlPatterns = {"/EnumServlet"})
public class EnumServlet extends HttpServlet {
    /**
    * An empty contructor
    */
    EnumServlet()
    {
        
    }
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
            out.println("<title>Servlet EnumServlet</title>");
            out.println("</head>");
            out.println("<body>");

            HttpSession session = request.getSession();
            int decisionInt = Integer.parseInt(request.getParameter("happy"));

            if (decisionInt == VERY_BAD.getValue()) {

                out.println("I'm sorry, I did my best!");

            }
            if (decisionInt == BAD.getValue()) {

                out.println("I'm sorry, I did my best!");

            }
            if (decisionInt == AVERAGE.getValue()) {

                out.println("It could be worse!");

            }
            if (decisionInt == GOOD.getValue()) {

                out.println("Thank you!");

            }
            if (decisionInt == VERY_GOOD.getValue()) {

                out.println("Thank you for you support sweetie!");

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
