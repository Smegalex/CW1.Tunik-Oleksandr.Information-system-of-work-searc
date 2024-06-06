/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.util;

import com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.dao.EmployeeDao;
import com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.dao.EmployerDao;
import com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.dao.VacancyDao;
import com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.model.Employee;
import com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.model.Employer;
import com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.model.Vacancy;
import com.igor_sikorsky_ist_hub.tunik_oleksandr.cw1_information_system_of_work_search.model.VacancyAnswer;
import java.util.Iterator;
import javax.naming.InitialContext;
import org.apache.catalina.Role;
import org.apache.catalina.User;
import org.apache.catalina.UserDatabase;

/**
 *
 * @author sasha
 */
public class TestDatabaseCreator {

    public static void createTestDatabaseCreator(VacancyDao vacancyDao, EmployeeDao employeeDao, EmployerDao employerDao) {
        Employer playTech = new Employer("Play Tech", "enquiries@playtech.com");
        employerDao.create(playTech);

        Employee kravMax = new Employee("Kravchenko Maksym", "kravMax@gmail.com");
        employeeDao.create(kravMax);

        try {
            UserDatabase ud = (UserDatabase) new InitialContext().lookup("java:comp/env/UserDatabase");

            User max = ud.createUser(kravMax.getEmail(), "max", kravMax.getName());
            Iterator<Role> roles = ud.getRoles();
            String roleName = "employee";
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
            max.addRole(currentRole);

            User play = ud.createUser(playTech.getEmail(), "play", playTech.getName());
            roleName = "employer";
            currentRole = null;
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
            play.addRole(currentRole);
            ud.save();
        } catch (Exception e) {

        }

        Vacancy manager = new Vacancy("Retail store manager",
                "We are looking for competent store manager that will listen "
                + "to the upper management and manage all the workers in the store.", playTech);

        Vacancy retailer = new Vacancy("Retail store worker",
                "We are looking for a young and passionate retail tech store worker with "
                + "at least 20 years of experience in the industry that knows "
                + "how to follow management's orders.", playTech);
        
        vacancyDao.create(manager);
        
        VacancyAnswer answer = manager.addAnswer(kravMax, "Hi, my name is Max, I am just 20 years old but am very "
                + "interested in your proposition and have experience in the business. I have "
                + "good references and attitude. I also manage people quite well.");
        
        kravMax.addAnswer(answer);
        playTech.addVacancy(manager);
        playTech.addVacancy(retailer);

        vacancyDao.update(manager);
        employeeDao.update(kravMax);
        employerDao.update(playTech);
        vacancyDao.create(retailer);
    }
}
