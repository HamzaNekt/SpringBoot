package ma.cigma.rest.service;
import java.util.Optional;

import ma.cigma.rest.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ma.cigma.rest.model.Employee;

import lombok.Data;
//@Service : tout comme l’annotation @Repository, c’est une spécialisation de @Component.
//Son rôle est le même, mais son nom a une valeur sémantique pour ceux qui lisent le code.
@Data
@Service
public class EmployeeService {
    @Autowired
    private IEmployeeRepository employeeRepository;
    public Optional<Employee> getEmployee(final Long id) {
        return employeeRepository.findById(id);
    }
    public Iterable<Employee> getEmployees() {
        return employeeRepository.findAll();
    }
    public void deleteEmployee(final Long id) {
        employeeRepository.deleteById(id);
    }
    public Employee saveEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return savedEmployee;
    }
}