package ru.ddc.jpaRepo4.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.ddc.jpaRepo4.model.Department;
import ru.ddc.jpaRepo4.model.Employee;
import ru.ddc.jpaRepo4.model.Vacancy;

import java.util.List;

@DataJpaTest
public class DaoTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private VacancyRepository vacancyRepository;

    @Test
    public void whenFindAllDepartmentsThenOneSelectQuery() {
        List<Department> departmentList = departmentRepository.findAll();
        departmentList.forEach(department -> {
            System.out.println(department);
            department.getVacancies().forEach(vacancy -> System.out.println("   -" + vacancy));
        });
    }

    @Test
    public void whenFindAllVacanciesThenOneSelectQuery() {
        List<Vacancy> vacancyList = vacancyRepository.findAll();
        vacancyList.forEach(vacancy -> {
            System.out.println(vacancy);
            System.out.println("   -" + vacancy.getEmployee());
        });
    }

    @Test
    public void whenFindAllEmployeesThenOneSelectQuery() {
        List<Employee> employeeList = employeeRepository.findAll();
        employeeList.forEach(System.out::println);
    }
}
