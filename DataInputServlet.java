package lab5Algorithms.servlets;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lab5Algorithms.model.Node;
import javax.persistence.*;
import lab5Algorithms.model.Data;
//import static lab5Algorithms.model.HistoryData_.id;

/**
 * class that stores processes on html page- getting size of the graph, creating
 * adjacency list and passing the data
 *
 * @author Emilia Gosiewska version 5.0
 */
@WebServlet(name = "DataInput", urlPatterns = {"/DataInput"})

public class DataInputServlet extends HttpServlet {

    /**
     * An empty constructor
     */
    DataInputServlet() {

    }

    /**
     *
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

            out.println("<body>");

            out.println("<p>Give the size of the graph: </p>");
            int size = Integer.parseInt(request.getParameter("sizeGraph"));
            if (size < 0) {
                response.sendError(response.SC_NOT_ACCEPTABLE, "Size cannot be less than 0!");
            }
            out.println(size);

            List<List<Node>> adjList = (List<List<Node>>) request.getSession().getAttribute("adjList");

            int x;
            int y;
            int weight;
            if (adjList == null) {
                adjList = new ArrayList<>();
            }
            for (int i = 0; i < size; i++) {
                adjList.add(new ArrayList<>());
            }

            String add = (String) request.getSession().getAttribute("Add");

            for (int i = 0; i < size; i++) {
                out.println("<p>Give the first node: </p>");
                x = Integer.parseInt(request.getParameter("graphParameterX"));
                out.println(x);
                if (x < 0) {
                    response.sendError(response.SC_NOT_ACCEPTABLE, "Vertex cannot be less than 0!");
                    return;
                }
                out.println("<p>Give the second node: </p>");
                y = Integer.parseInt(request.getParameter("graphParameterY"));
                out.println(y);
                if (y < 0) {
                    response.sendError(response.SC_NOT_ACCEPTABLE, "Vertex cannot be less than 0!");
                    return;
                }
                out.println("<p>Give the weight: </p>");
                weight = Integer.parseInt(request.getParameter("graphParameterW"));
                out.println(weight);

                HttpSession session = request.getSession();

                Node node = new Node(y, weight);
                adjList.get(x).add(node);

                session.setAttribute("adjList", adjList);

                Data data = new Data();
                EntityManager em = Persistence.createEntityManagerFactory("DijkstraBellman").createEntityManager();

                try {
                    List<Data> datas;
                    em.getTransaction().begin();
                    data.setSize(size);
                    data.setX(x);
                    data.setY(y);
                    data.setWeight(weight);
                    em.persist(data);
                    em.getTransaction().commit();
                    out.println("<table border=\"1\" cellspacing=\"2\"cellpadding=\"2\">");
                    out.println("<thead><tr>");
                    out.println("<td> Id </td>");
                    out.println("<td> Size </td>");
                    out.println("<td> x </td>");
                    out.println("<td> y </td>");
                    out.println("<td> weight </td>");
                    out.println("</thead></tr>");

                    PrintWriter writer2 = response.getWriter();
                    datas = em.createQuery("SELECT s FROM Data s", Data.class).getResultList();

                    for (Data o : datas) {
                        writer2.println("<p></p>");
                        writer2.println("</td><td>" + o.getId() + "</td><td>" + o.getSize() + "</td><td>" + o.getX() + "</td><td>" + o.getY() + "</td><td>" + o.getWeight() + "<br" + "</td><tr>");
                        writer2.println("<p></p>");

                    }
                } catch (PersistenceException e) {
                    em.getTransaction().rollback();
                } finally {
                    em.close();
                }

                getServletContext().getRequestDispatcher("BellmanFordServlet").forward(request, response);
                getServletContext().getRequestDispatcher("DijkstraServlet").forward(request, response);
                getServletContext().getRequestDispatcher("AllServlet").forward(request, response);
                getServletContext().getRequestDispatcher("HistoryServlet").forward(request, response);

                out.println("Rate the program from 1-5");

                String dijkstra = request.getParameter("Dijkstra");
                String bellman = request.getParameter("BellmanFord");
                String all = request.getParameter("All");
                String enumm = request.getParameter("Enum");
                String showHistory = request.getParameter("History");

                if (showHistory != null) {
                    RequestDispatcher rd = request.getRequestDispatcher("HistoryServlet");
                    rd.forward(request, response);
                }

                if (enumm != null) {
                    RequestDispatcher rd = request.getRequestDispatcher("EnumServlet");
                    rd.forward(request, response);
                }
                if (dijkstra != null) {
                    RequestDispatcher rd = request.getRequestDispatcher("DijkstraServlet");
                    rd.forward(request, response);

                }
                if (bellman != null) {
                    RequestDispatcher rd = request.getRequestDispatcher("BellmanFordServlet");
                    rd.forward(request, response);

                }
                if (all != null) {
                    RequestDispatcher rd = request.getRequestDispatcher("AllServlet");
                    rd.forward(request, response);

                }

                request.setAttribute("adjList", adjList);

                out.println("</body>");
                out.println("</html>");

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
