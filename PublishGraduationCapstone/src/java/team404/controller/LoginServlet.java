package team404.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import team404.project.ProjectDAO;
import team404.project.ProjectDTO;
import team404.user.UserDAO;
import team404.user.UserDTO;
import team404.utils.GoogleHelpers;

public class LoginServlet extends HttpServlet {

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

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String token = request.getParameter("token");

        try {
            ProjectDAO dao = new ProjectDAO();
            String[] semester = {"2022-Spring", "2022-Summer"};
            List<ProjectDTO> list = dao.filterSearchSemesterGetProjectsRemake(semester);
            for (ProjectDTO projectDTO : list) {
                System.out.println(projectDTO.toString());
            }
            int result = dao.filterSearchSemesterNumberOfResults(semester);
            System.out.println("result: "+result);
            GoogleHelpers googleHelper = new GoogleHelpers();
            String json = googleHelper.getUserInfo(token);
            UserDTO user = googleHelper.getUserFromJson(json);

            UserDAO userDAO = new UserDAO();

            String id = user.getSub();
            boolean idExisted = userDAO.checkId(id);

            if (!idExisted) {
                userDAO.createNewAcccount(user);
            }

            out.print(json);
            response.flushBuffer();
            out.flush();
        } catch (SQLException ex) {
            log("LoginServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("LoginServlet_Naming: " + ex.getMessage());
        } finally {
            out.close();
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
