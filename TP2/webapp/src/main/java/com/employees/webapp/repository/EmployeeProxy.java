package com.employees.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.employees.webapp.model.Employee;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmployeeProxy {
    @Autowired
    private CustomProperties props;

    /**
     * Get all employees
     * @return An iterable of all employees
     */
    public Iterable<Employee> getEmployees() {
        String baseApiUrl = props.getApiUrl();
        String getEmployeesUrl = baseApiUrl + "/employees";
        System.out.println("EmployeeProxy *** getEmployees() getEmployeesUrl " + getEmployeesUrl);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Employee>> response = restTemplate.exchange(
                getEmployeesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Employee>>() {}
        );

        System.out.println("EmployeeProxy *** getEmployees() response.getBody() " + response.getBody());
        log.debug("Get Employees call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Get an employee by id
     * @param id The id of the employee
     * @return The employee
     */
    public Employee getEmployee(int id) {
        String baseApiUrl = props.getApiUrl();
        String getEmployeeUrl = baseApiUrl + "/employee/" + id;
        System.out.println("EmployeeProxy *** getEmployee() getEmployeeUrl " + getEmployeeUrl);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee> response = restTemplate.exchange(
                getEmployeeUrl,
                HttpMethod.GET,
                null,
                Employee.class
        );

        System.out.println("EmployeeProxy *** getEmployee() response.getBody() " + response.getBody());
        log.debug("Get Employee call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Create a new employee
     * @param employee The employee to create
     * @return The created employee
     */
    public Employee createEmployee(Employee employee) {
        String baseApiUrl = props.getApiUrl();
        String createEmployeeUrl = baseApiUrl + "/employee";
        System.out.println("EmployeeProxy *** createEmployee() createEmployeeUrl " + createEmployeeUrl);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Employee> request = new HttpEntity<>(employee);
        ResponseEntity<Employee> response = restTemplate.exchange(
                createEmployeeUrl,
                HttpMethod.POST,
                request,
                Employee.class
        );

        System.out.println("EmployeeProxy *** createEmployee() response.getBody() " + response.getBody());
        log.debug("Create Employee call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Update an existing employee
     * @param employee The employee to update
     * @return The updated employee
     */
    public Employee updateEmployee(Employee employee) {
        String baseApiUrl = props.getApiUrl();
        String updateEmployeeUrl = baseApiUrl + "/employee/" + employee.getId();
        System.out.println("EmployeeProxy *** updateEmployee() updateEmployeeUrl " + updateEmployeeUrl);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Employee> request = new HttpEntity<>(employee);
        ResponseEntity<Employee> response = restTemplate.exchange(
                updateEmployeeUrl,
                HttpMethod.PUT,
                request,
                Employee.class
        );

        System.out.println("EmployeeProxy *** updateEmployee() response.getBody() " + response.getBody());
        log.debug("Update Employee call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Delete an employee by id
     * @param id The id of the employee to delete
     */
    public void deleteEmployee(long id) {
        String baseApiUrl = props.getApiUrl();
        String deleteEmployeeUrl = baseApiUrl + "/employee/" + id;
        System.out.println("EmployeeProxy *** deleteEmployee() deleteEmployeeUrl " + deleteEmployeeUrl);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteEmployeeUrl,
                HttpMethod.DELETE,
                null,
                Void.class
        );

        log.debug("Delete Employee call " + response.getStatusCode().toString());
    }
}