package lab5Algorithms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lab5Algorithms.model.Model;
import lab5Algorithms.model.Node;
import lab5Algorithms.model.NegativeNodeException;
import lab5Algorithms.model.Algorithm;
import lab5Algorithms.model.Data;
import lab5Algorithms.model.HistoryData;

/**
 * Class that calls both Dijkstra and Bellman-Ford algorithms and takes source
 * node from the user
 *
 * @author Emilia Gosiewska version 5.0
 */
@WebServlet(name = "All", urlPatterns = {"/All"})
public class AllServlet extends HttpServlet {

    /**
     * an empty contructor
     */
    AllServlet() {

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

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Dijkstra and Bellman-Ford algorithms</title>");
            out.println("</head>");
            out.println("<body>");
            int source = Integer.parseInt(request.getParameter("sourceNode"));
            if (source < 0) {
                response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "Source should be greater than 0!");
                return;
            } else {
                out.println("source equals:" + source + "</p>");
            }
            //Bellman-Ford algorihtm
            List<Integer> distance = new ArrayList<>();
            HttpSession session = request.getSession();
            List<List<Node>> adjList = (List<List<Node>>) session.getAttribute("adjList");

            Model model = new Model(adjList);
            try {
                distance = model.bellmanFord(source);
            } catch (NegativeNodeException ex) {
                response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "Negative cycle has occured!");
                return;
            }

            //Dijkstra algorithm
            try {
                distance = model.algoDijkstra(source);
            } catch (NegativeNodeException ex) {
                response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "Negative cycle has occured!");
                return;
            }

            out.println("The path using Bellman Ford algorithm:");
            for (int i = 0; i < distance.size(); i++) {
                if (distance.get(i) != Integer.MAX_VALUE) {
                    out.printf("%d: %d \n", i, distance.get(i));
                }
            }

            out.println("<p> The path using Dijkstra algorithm:");
            for (int i = 0; i < distance.size(); i++) {
                if (distance.get(i) != Integer.MAX_VALUE) {
                    out.printf("%d: %d \n", i, distance.get(i));
                }
            }

            EntityManager em = Persistence.createEntityManagerFactory("DijkstraBellman").createEntityManager();

            try {
                Data data = new Data();
                HistoryData historyData = new HistoryData();
                data.setId(historyData.getId());
                em.getTransaction().begin();
                em.persist(data);
                historyData.setData(data);
                historyData.setSourceNode(source);
                historyData.setAlgorithm("Dijkstra");
                historyData.setAlgorithm("BellmanFord");
                historyData.setDistanceValue(distance.toString().replaceAll("2147483647", ""));
                em.persist(historyData);
                em.getTransaction().commit();
                out.println("Both Dijkstra and Bellman were added");

            } catch (Exception e) {
                em.getTransaction().rollback();
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
    }

}
