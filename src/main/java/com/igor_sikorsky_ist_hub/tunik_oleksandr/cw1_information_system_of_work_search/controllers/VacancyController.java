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
import com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.model.VacancyAnswer;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
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
                return;
            case "search":
                search(request, response);
                return;
            case "showList":
            default:
                showList(request, response);
        }
//        request.getRequestDispatcher("WEB-INF/jsp/main.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");

        if (command == null) {
            command = "showList";
        }
        switch (command) {
            case "leaveAnswer":
                leaveAnswer(request, response);
                return;
            case "createRedirect":
                request.getRequestDispatcher("WEB-INF/jsp/employer/create.jsp").forward(request, response);
                return;
            case "create":
                createVacancy(request, response);
                return;
            case "answers":
                request.setAttribute("vacancy", vacancyDao.findByIdAndClosed(Long.valueOf(request.getParameter("id"))));
                request.getRequestDispatcher("WEB-INF/jsp/employer/answers.jsp").forward(request, response);
                return;
            case "editRedirect":
                request.setAttribute("vacancy", vacancyDao.findByIdAndClosed(Long.valueOf(request.getParameter("id"))));
                request.getRequestDispatcher("WEB-INF/jsp/employer/edit.jsp").forward(request, response);
                return;
            case "edit":
                edit(request, response);
                return;
            case "delete":
                delete(request, response);
                return;
            default:
                showList(request, response);
        }

    }

    public void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        vacancyDao.deleteById(id);

        request.setAttribute("popupCondition", true);
        request.setAttribute("popupTitle", "Vacancy deleted successfully.");
        request.setAttribute("popupBody", "");
        request.setAttribute("popupStatus", "success");
        request.getRequestDispatcher("/users?command=employer").forward(request, response);
    }

    public void edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        Long id = Long.valueOf(request.getParameter("id"));

        String user = request.getRemoteUser();
        Employer employer = this.employerDao.findByLogin(user);

        Vacancy vacancy = vacancyDao.findByIdAndClosed(id);

        vacancy.setOpenStatus(status);
        vacancy.setTitle(title);
        vacancy.setDescription(description);

        vacancyDao.update(vacancy);

        request.setAttribute("popupCondition", true);
        request.setAttribute("popupTitle", "Vacancy updated successfully.");
        request.setAttribute("popupBody", "");
        request.setAttribute("popupStatus", "success");
        request.getRequestDispatcher("/users?command=employer").forward(request, response);
    }

    private void showList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TreeSet<Vacancy> vacancies = new TreeSet<>(new DateTimeComparator());
        vacancies.addAll(vacancyDao.findAll());
        request.setAttribute("vacancies", vacancies);
        request.getRequestDispatcher("WEB-INF/jsp/main.jsp").forward(request, response);
    }

    private void createVacancy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        String user = request.getRemoteUser();
        Employer employer = this.employerDao.findByLogin(user);

        Vacancy vacancy = new Vacancy(title, description, employer);
        vacancyDao.create(vacancy);
        employer.addVacancy(vacancy);
        employerDao.update(employer);
        request.setAttribute("popupCondition", true);
        request.setAttribute("popupTitle", "Vacancy created successfully.");
        request.setAttribute("popupBody", "");
        request.setAttribute("popupStatus", "success");
        request.getRequestDispatcher("/users?command=employer").forward(request, response);
    }

    private void viewDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id;
        try {
            id = Long.valueOf(request.getParameter("id"));
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
        if (vacancy == null) {
            response.sendError(400, "Item with ID does not exist");
            return;
        }
        request.setAttribute("vacancy", vacancy);
        request.setAttribute("role", role);
        request.getRequestDispatcher("WEB-INF/jsp/vacancy_details.jsp").forward(request, response);
    }

    private void leaveAnswer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id;
        try {
            id = Long.valueOf(request.getParameter("vacancyId"));
        } catch (NumberFormatException e) {
            response.sendError(400, "Invalid item id");
            return;
        }
        String user = request.getRemoteUser();
        Employee employee = employeeDao.findByLogin(user);
        String content = request.getParameter("answer");

        Vacancy vacancy = vacancyDao.findById(id);
        if (vacancy == null) {
            response.sendError(400, "Item with ID does not exist");
            return;
        }
        VacancyAnswer answer = vacancy.addAnswer(employee, content);
        employee.addAnswer(answer);
        vacancyDao.update(vacancy);
        request.setAttribute("popupCondition", true);
        request.setAttribute("popupTitle", "Answer recorded successfully.");
        request.setAttribute("popupBody", "We have passed your answer to the employer.");
        request.setAttribute("popupStatus", "success");
        request.getRequestDispatcher("/vacancies?command=showList").forward(request, response);
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keywords = request.getParameter("searchText");
        String sorting = request.getParameter("sorting");
        Collection<Vacancy> vacancies = vacancyDao.findAll();
        TreeMap<Vacancy, Float> selected = new TreeMap<>(new DateTimeComparator());
        Float currentFitness = 0f;
        for (Vacancy vacancy : vacancies) {
            currentFitness = vacancy.checkKeywords(keywords);
            if (currentFitness != 0) {
                selected.put(vacancy, currentFitness);
            }
        }
        request.setAttribute("sortBy", sorting);
        request.setAttribute("prevSearch", keywords);
        TreeMap<Vacancy, Float> sorted;
        switch (sorting) {
            case "newToOld":
                sorted = (new TreeMap<>(new DateTimeComparator()));
                sorted.putAll(selected);
                request.setAttribute("vacancies", sorted.keySet());
                break;
            case "oldToNew":
                sorted = (new TreeMap<>(new DateTimeComparator().reversed()));
                sorted.putAll(selected);
                request.setAttribute("vacancies", sorted.keySet());
                break;
            case "contrAlphabetically":
                sorted = (new TreeMap<>(new AlphabetComparator().reversed()));
                sorted.putAll(selected);
                request.setAttribute("vacancies", sorted.keySet());
                break;
            case "alphabetically":
                sorted = (new TreeMap<>(new AlphabetComparator()));
                sorted.putAll(selected);
                request.setAttribute("vacancies", sorted.keySet());
                break;
            case "byRelevance":
            default:
                request.setAttribute("vacancies", entriesSortedByValues(selected));
        }
        request.getRequestDispatcher("WEB-INF/jsp/main.jsp").forward(request, response);
    }

    static <K, V extends Comparable<? super V>>
            List<K> entriesSortedByValues(Map<K, V> map) {
        SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet<Map.Entry<K, V>>(
                new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
                int res = e2.getValue().compareTo(e1.getValue());
                return res != 0 ? res : 1;
            }
        }
        );

        sortedEntries.addAll(map.entrySet());
        List<K> keys = new ArrayList<>();

        // Iterate over the sorted set and add keys to the list
        for (Map.Entry<K, V> entry : sortedEntries) {
            keys.add(entry.getKey());
        }

        return keys;
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
        currentComparing = Long.compare(o1.getAnswers().size(), o2.getAnswers().size());
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
