package ru.ddc.jpaRepo4.testutils;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;
import ru.ddc.jpaRepo4.persistence.model.Employee;
import ru.ddc.jpaRepo4.persistence.model.Vacancy;

@AllArgsConstructor
@NoArgsConstructor(staticName = "aEmployee")
@With
public class EmployeeTestBuilder implements TestBuilder<Employee> {
    private String firstname = "firstname";
    private String lastname = "lastname";
    private String patronymic = "patronymic";
    private Vacancy vacancy = null;

    @Override
    public Employee build() {
        Employee employee = new Employee();
        employee.setFirstname(firstname);
        employee.setLastname(lastname);
        employee.setPatronymic(patronymic);
        employee.setVacancy(vacancy);
        return employee;
    }
}
