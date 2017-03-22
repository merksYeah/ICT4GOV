/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.Gson;
import dao.DAOFactory;
import interfaces.ComplaintDAO;
import interfaces.FranchiseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Complainant;
import models.Complaint;
import models.Franchise;
import models.Vehicle;

/**
 *
 * @author migue
 */
public class fileComplaint extends HttpServlet {

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
            ComplaintDAO complaintDAO = factory.getComplaintDAO();
            FranchiseDAO franchiseDAO = factory.getFranchiseDAO();
            Complainant complainant = new Complainant();
            complainant.setName(request.getParameter("name"));
            complainant.setEmail(request.getParameter("email"));
            complainant.setMobileNumber(request.getParameter("mobile"));
            complainant.setPhoneNumber(request.getParameter("phone"));
            complainant.setMailingAddress(request.getParameter("mailing_address"));
            Vehicle vehicle = new Vehicle();
            vehicle.setPlateNumber(request.getParameter("plate_number"));
            Vehicle[] vehicles = {vehicle};
            Franchise franchise = franchiseDAO.getFranchiseByPlate(vehicle.getPlateNumber());
            franchise.setVehicles(vehicles);
            Complaint complaint = new Complaint();
            complaint.setComplaintType(Integer.parseInt(request.getParameter("complaint_type")));
            complaint.setComplaintStatus(1);
            complaint.setDescription(request.getParameter("description"));
            complaint.setSubject(request.getParameter("subject"));
            complaint.setFranchise(franchise);
            complaint.setComplainant(complainant);
            int key = complaintDAO.addComplaint(complaint);
            complaint.setReference_number(key);
            String json = null;
            json=new Gson().toJson(complaint);
            System.out.println(json);
            response.getWriter().write(json);
            //rd = request.getRequestDispatcher("/Navigate");
            //rd.forward(request, response);
            
            
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
