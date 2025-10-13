package ma.cigma.rest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ma.cigma.rest.model.Employee;


public interface IEmployeeRepository extends CrudRepository<Employee, Long> {}
