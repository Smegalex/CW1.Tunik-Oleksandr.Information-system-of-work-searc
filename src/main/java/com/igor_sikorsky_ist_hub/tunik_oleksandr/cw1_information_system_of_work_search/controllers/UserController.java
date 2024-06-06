/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.controllers;

import com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.dao.EmployeeDao;
import com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.dao.EmployerDao;
import com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.model.Employee;
import com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.model.Employer;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Iterator;
import javax.naming.InitialContext;
import org.apache.catalina.Role;
import org.apache.catalina.User;
import org.apache.catalina.UserDatabase;

/**
 *
 * @author sasha
 */
@WebServlet(name = "UserManager", urlPatterns = {"/users"})
public class UserController extends HttpServlet {

    private EmployeeDao employeeDao;
    private EmployerDao employerDao;

    @Override
    public void init() throws ServletException {
        super.init();
        employeeDao = (EmployeeDao) getServletContext().getAttribute("employeeDao");
        employerDao = (EmployerDao) getServletContext().getAttribute("employerDao");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String command = request.getParameter("command");
        if (command != null) {
            switch (command) {
                case "signup":
                    request.getRequestDispatcher("WEB-INF/jsp/account/signup.jsp").forward(request, response);
                    return;
                case "logout":
                    if (session != null) {
                        session.invalidate();
                        request.getRequestDispatcher("WEB-INF/jsp/account/login.jsp").forward(request, response);
                        return;
                    }
            }
        }
        String role;
        String user = request.getRemoteUser();
        Employee employee = this.employeeDao.findByLogin(user);
        Employer employer = this.employerDao.findByLogin(user);
        if (employee == null && employer == null) {
            role = null;
        } else if (employee == null) {
            role = "employer";
        } else {
            role = "employee";
        }

        if (role == null) {
            request.getRequestDispatcher("WEB-INF/jsp/account/login.jsp").forward(request, response);
        } else {
            switch (role) {
                case "employee":
                    request.setAttribute("user", employee);
                    request.getRequestDispatcher("WEB-INF/jsp/employee/profile.jsp").forward(request, response);
                    break;
                case "employer":
                    request.setAttribute("user", employer);
                    request.getRequestDispatcher("WEB-INF/jsp/employer/profile.jsp").forward(request, response);
                    break;
                default:
                    request.getRequestDispatcher("WEB-INF/jsp/account/login.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");

        if (command == null) {
            response.sendError(400, "Wrong command to UserController.");
        } else {
            switch (command) {
                case "signup":
                    signup(request, response);
                    break;

                case "login":
                    login(request, response);
                    break;
                case "employer":
                    String user = request.getRemoteUser();
                    Employer employer = this.employerDao.findByLogin(user);
                    request.setAttribute("user", employer);
                    request.getRequestDispatcher("WEB-INF/jsp/employer/profile.jsp").forward(request, response);
                    break;
                default:
                    response.sendError(400, "Wrong command to UserController.");
            }
        }
    }

    private void signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String expectedName = "^[а-яА-ЯіїєґІЇЄҐ\\s\\-A-Za-z]+$";
        request.setAttribute("popupCondition", false);
        String username = request.getParameter("email");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        String roleName = request.getParameter("accountType");

        if (!fullName.matches(expectedName)) {
            request.setAttribute("popupCondition", true);
            request.setAttribute("popupTitle", "Invalid full name.");
            request.setAttribute("popupBody", "The full name can not be empty or contain any special symbols.");
            request.setAttribute("popupStatus", "error");
            request.getRequestDispatcher("WEB-INF/jsp/account/signup.jsp").forward(request, response);
            return;
        }
        boolean similarityCheck; //true if similar, false if OK
        if (null == roleName) {
            response.sendError(400, "Wrong role to UserController.");
            return;
        } else {
            switch (roleName) {
                case "employee":
                    similarityCheck = employeeDao.findByLogin(username) != null;
                    break;
                case "employer":
                    similarityCheck = employerDao.findByLogin(username) != null;
                    break;
                default:
                    response.sendError(400, "Wrong role to UserController.");
                    return;
            }
        }
        if (similarityCheck) {
            request.setAttribute("popupCondition", true);
            request.setAttribute("popupTitle", "A user with this email already exists.");
            request.setAttribute("popupBody", "There cannot be 2 users of the same role with the same email.");
            request.setAttribute("popupStatus", "error");
            request.getRequestDispatcher("WEB-INF/jsp/account/signup.jsp").forward(request, response);
            return;
        }

        try {
            UserDatabase ud = (UserDatabase) new InitialContext().lookup("java:comp/env/UserDatabase");

            User user = ud.createUser(username, password, fullName);
            Iterator<Role> roles = ud.getRoles();
            Role currentRole = null;
            while (roles.hasNext()) {
                currentRole = roles.next();
                if (currentRole.getName().equals(roleName)) {
                    break;
                }
            }
            if (currentRole == null) {
                currentRole = ud.createRole(roleName, "role description");
            } else if (!currentRole.getName().equals(roleName)) {
                currentRole = ud.createRole(roleName, "role description");
            }
            user.addRole(currentRole);
            ud.save();

            switch (roleName) {
                case "employee":
                    employeeDao.create(new Employee(fullName, username));
                    break;
                case "employer":
                    employerDao.create(new Employer(fullName, username));
                    break;
            }
            request.setAttribute("popupCondition", true);
            request.setAttribute("popupTitle", "User created successfully!");
            request.setAttribute("popupBody", fullName + ", welcome to ISoWS!");
            request.setAttribute("popupStatus", "success");
            request.getRequestDispatcher("WEB-INF/jsp/main.jsp").forward(request, response);

        } catch (Exception e) {

        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("popupCondition", false);
        String username = request.getParameter("email");
        String password = request.getParameter("password");

        Employee employee = employeeDao.findByLogin(username);
        Employer employer = employerDao.findByLogin(username);

        if (employee == null && employer == null) {
            request.setAttribute("popupCondition", true);
            request.setAttribute("popupTitle", "User with this email does not exist.");
            request.setAttribute("popupBody", "There are no users registered with this email.");
            request.setAttribute("popupStatus", "error");
            request.getRequestDispatcher("WEB-INF/jsp/account/login.jsp").forward(request, response);
            return;
        }

        try {

            request.login(username, password);
            if (employee != null) {
                request.getRequestDispatcher("WEB-INF/jsp/employee/profile.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("WEB-INF/jsp/employer/profile.jsp").forward(request, response);
            }
        } catch (ServletException e) {
            request.setAttribute("popupCondition", true);
            request.setAttribute("popupTitle", "Incorrect password entered.");
            request.setAttribute("popupBody", "");
            request.setAttribute("popupStatus", "error");
            request.getRequestDispatcher("WEB-INF/jsp/account/login.jsp").forward(request, response);
            return;
        }
    }

}
