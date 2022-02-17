/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team404.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import team404.project.ProjectDAO;
import team404.project.ProjectDTO;

/**
 *
 * @author tienhltse151104
 */
@WebServlet(name = "SearchProjectServlet", urlPatterns = {"/SearchProjectServlet"})
public class SearchProjectServlet extends HttpServlet {

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

        String keyword = request.getParameter("keyword");

        try {
            ProjectDAO dao = new ProjectDAO();
            List<ProjectDTO> list = dao.getSearchProjectList(keyword);
            
            JSONArray jsArr = new JSONArray();
            for (ProjectDTO projectDTO : list) {
                JSONObject jsObj = new JSONObject();
                jsObj.put("projectId", projectDTO.getProjectId());
                jsObj.put("projectName", projectDTO.getProjectName());
                jsObj.put("projectAva", projectDTO.getProjectAva());
                jsObj.put("semester", projectDTO.getSemester());
                jsArr.add(jsObj);
            }
            JSONObject jsObj = new JSONObject();
            jsObj.put("searchProject", jsArr);
            
            out.print(jsObj);
            //response.flushBuffer();
            //out.flush();
            /*
            RequestDispatcher rd = request.getRequestDispatcher("Search-BE.html");
            rd.forward(request, response);
            */
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
