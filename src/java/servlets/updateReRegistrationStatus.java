/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.DAOFactory;
import interfaces.ApplicationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Application;
import models.User;

/**
 *
 * @author migue
 */
public class updateReRegistrationStatus extends HttpServlet {

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
            RequestDispatcher rd = null;
           DAOFactory factory = DAOFactory.getDAOFactory(1);
           ApplicationDAO applicationDAO = factory.getApplicationDAO();
           Application app = new Application();
           HttpSession session = request.getSession();
           User user = (User) session.getAttribute("login");
           if(user.getRole().equals("tecnical evaluation")){
                 System.out.println("hey");
             if(request.getParameter("inspection_date") != null){
                 System.out.println(request.getParameter("inspection_date").isEmpty());
                 app.setCaseNumber((int) session.getAttribute("case_num"));
                 app.setStatusId(Integer.parseInt(request.getParameter("status")));
                 app.setInspectionDate(request.getParameter("inspection_date"));
                 applicationDAO.updateReRegisterInspection(app);
                 rd = request.getRequestDispatcher("/showTedReRegister");
                 rd.forward(request, response);
               }
             else{
                 app.setCaseNumber((int) session.getAttribute("case_num"));
                 app.setStatusId(Integer.parseInt(request.getParameter("status")));
                 applicationDAO.updateReRegisterStatus(app);
                 rd = request.getRequestDispatcher("/showTedReRegister");
                 rd.forward(request, response);
             }
            }
           else if(user.getRole().equals("legal")){
               if(request.getParameter("hearing_date") != null){
                 app.setCaseNumber((int) session.getAttribute("case_num"));
                 app.setStatusId(Integer.parseInt(request.getParameter("status")));
                 app.setHearingDate(request.getParameter("hearing_date"));
                 applicationDAO.updateReRegisterHearing(app);
                 rd = request.getRequestDispatcher("/showLegalApplications");
                 rd.forward(request, response);
               }
               else{
                 app.setCaseNumber((int) session.getAttribute("case_num"));
                 app.setStatusId(Integer.parseInt(request.getParameter("status")));
                 applicationDAO.updateReRegisterStatus(app);
                 rd = request.getRequestDispatcher("/showLegalApplications");
                 rd.forward(request, response);
               }
              
             }
            else if(user.getRole().equals("motor vehicle inspection")){
                 app.setCaseNumber((int) session.getAttribute("case_num"));
                 app.setStatusId(Integer.parseInt(request.getParameter("status")));
                 applicationDAO.updateReRegisterStatus(app);
                 rd = request.getRequestDispatcher("/showMotorApplications");
                 rd.forward(request, response);
                
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
