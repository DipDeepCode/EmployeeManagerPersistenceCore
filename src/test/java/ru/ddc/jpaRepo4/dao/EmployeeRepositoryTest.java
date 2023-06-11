package ru.ddc.jpaRepo4.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import ru.ddc.jpaRepo4.model.Employee;

import static ru.ddc.jpaRepo4.testutils.EmployeeTestBuilder.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void whenFindAllEmployees_thenListSizeEqualsFive() {
        List<Employee> employeeList = employeeRepository.findAll();
        showEmployeeList(employeeList);
        assertEquals(5, employeeList.size());
    }

    @Test
    public void whenFindEmployeeById_thenEmployeeIdEqualsToSpecified() {
        Long employeeId = 1L;
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        showEmployee(employee);
        assertEquals(employeeId, employee.getId());
    }

    @Test
    public void whenFindEmployeesByVacancyIsNull_thenListSizeEqualsOne() {
        List<Employee> employeeList = employeeRepository.findByVacancyNull();
        showEmployeeList(employeeList);
        assertEquals(1, employeeList.size());
    }

    @Test
    public void whenSaveEmployee_thenEmployeeCounterLagerByOne() {
        long counterBeforeSave = employeeRepository.count();
        Employee employee = aEmployee().build();
        showEmployee(employee);
        employeeRepository.saveAndFlush(employee);
        long counterAfterSave = employeeRepository.count();
        assertEquals(1, counterAfterSave - counterBeforeSave);
    }

    @Test
    public void whenDeleteEmployeeByIdWithVacancy_thenThrowException() {
        Long employeeId = 1L;
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        employeeRepository.deleteById(employee.getId());
        assertThrows(DataIntegrityViolationException.class, () -> employeeRepository.flush());
    }

    @Test
    public void whenDeleteEmployeeByIdWithoutVacancy_thenEmployeeCounterLessByOne() {
        Long employeeId = 5L;
        long counterBeforeDelete = employeeRepository.count();
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        employeeRepository.deleteById(employee.getId());
        employeeRepository.flush();
        long counterAfterDelete = employeeRepository.count();
        assertEquals(1, counterBeforeDelete - counterAfterDelete);
    }

    private static void showEmployeeList(List<Employee> employeeList) {
        employeeList.forEach(EmployeeRepositoryTest::showEmployee);
    }

    private static void showEmployee(Employee employee) {
        System.out.println(employee);
    }


}
