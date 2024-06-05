/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.management.ManagementFactory;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

/**
 *
 * @author sasha
 */
@WebServlet(name = "UserManager", urlPatterns = {"/users"})
public class UserManager extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("email");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        String roleName = request.getParameter("accountType");
        String command = request.getParameter("command");

        try {
            MBeanServerConnection mbsc = ManagementFactory.getPlatformMBeanServer();
            ObjectName objectName = new ObjectName("Catalina:type=UserDatabase,database=UserDatabase");

            // Add a new user
            mbsc.invoke(objectName, "createUser", new Object[]{username, password, fullName}, new String[]{"java.lang.String", "java.lang.String", "java.lang.String"});

            // Assign the role to the user
            mbsc.invoke(objectName, "addRole", new Object[]{username, roleName}, new String[]{"java.lang.String", "java.lang.String"});

            response.getWriter().write("User added and role assigned successfully.");
        } catch (Exception e) {
            response.getWriter().write("An error occurred: " + e.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
