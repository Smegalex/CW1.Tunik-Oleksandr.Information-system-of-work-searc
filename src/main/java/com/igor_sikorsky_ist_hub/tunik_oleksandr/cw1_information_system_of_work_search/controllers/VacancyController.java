/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.controllers;

import com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.dao.EmployeeDao;
import com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.dao.EmployerDao;
import com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.dao.VacancyDao;
import com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.model.Employee;
import com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.model.Employer;
import com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.model.Vacancy;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

/**
 *
 * @author sasha
 */
@WebServlet(name = "VacanciesController", urlPatterns = {"/vacancies", "/index.html"})
public class VacancyController extends HttpServlet {

    private VacancyDao vacancyDao;
    private EmployeeDao employeeDao;
    private EmployerDao employerDao;

    @Override
    public void init() throws ServletException {
        super.init();
        vacancyDao = (VacancyDao) getServletContext().getAttribute("vacancyDao");
        employeeDao = (EmployeeDao) getServletContext().getAttribute("employeeDao");
        employerDao = (EmployerDao) getServletContext().getAttribute("employerDao");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        if (command == null) {
            command = "showList";
        }
        switch (command) {
            case "view":
                viewDetails(request, response);
            case "showList":
            default:
                showList(request, response);
        }
        request.getRequestDispatcher("WEB-INF/jsp/main.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    private void showList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TreeSet<Vacancy> vacancies = new TreeSet<>(new DateTimeComparator());
        vacancies.addAll(vacancyDao.findAll());
        request.setAttribute("vacancies", vacancies);
        request.getRequestDispatcher("WEB-INF/jsp/main.jsp").forward(request, response);
    }

    private void viewDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id;
        try {
            id = Integer.valueOf(request.getParameter("id"));
        } catch (NumberFormatException e) {
            response.sendError(400, "Invalid item id");
            return;
        }
        String role = null;
        String user = request.getRemoteUser();
        Employee employee = this.employeeDao.findByLogin(user);
        Employer employer = this.employerDao.findByLogin(user);
        if (employee == null && employer == null) {
            role = null;
        } else if (employee == null) {
            if (employer.getPostedVacancies().get(id) != null) {
                role = "owner";
            }
        } else {
            role = "employee";
        }

        Vacancy vacancy = vacancyDao.findById(id);
        request.setAttribute("vacancy", vacancy);
        request.setAttribute("role", role);
        request.getRequestDispatcher("WEB-INF/jsp/vacancy_details.jsp").forward(request, response);
    }

}

class DateTimeComparator implements Comparator<Vacancy> {

    @Override
    public int compare(Vacancy o1, Vacancy o2) {
        int currentComparing;
        currentComparing = o1.getDateTimePosted().compareTo(o2.getDateTimePosted());
        if (currentComparing != 0) {
            return currentComparing;
        }
        return o1.getId().compareTo(o2.getId());
    }

    @Override
    public Comparator reversed() {
        return Comparator.super.reversed(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}

class AlphabetComparator implements Comparator<Vacancy> {

    @Override
    public int compare(Vacancy o1, Vacancy o2) {
        int currentComparing;
        currentComparing = o1.getTitle().compareToIgnoreCase(o2.getTitle());
        if (currentComparing != 0) {
            return currentComparing;
        }
        currentComparing = o1.getDescription().compareToIgnoreCase(o2.getDescription());
        if (currentComparing != 0) {
            return currentComparing;
        }
        currentComparing = o1.getEmployer().getName().compareToIgnoreCase(o2.getEmployer().getName());
        if (currentComparing != 0) {
            return currentComparing;
        }
        return o1.getId().compareTo(o2.getId());
    }

    @Override
    public Comparator<Vacancy> reversed() {
        return Comparator.super.reversed(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
}

class AnswerNumberComparator implements Comparator<Vacancy> {

    @Override
    public int compare(Vacancy o1, Vacancy o2) {
        int currentComparing;
        currentComparing = Integer.compare(o1.getAnswers().size(), o2.getAnswers().size());
        if (currentComparing != 0) {
            return currentComparing;
        }

        return o1.getId().compareTo(o2.getId());
    }

    @Override
    public Comparator<Vacancy> reversed() {
        return Comparator.super.reversed(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
}
