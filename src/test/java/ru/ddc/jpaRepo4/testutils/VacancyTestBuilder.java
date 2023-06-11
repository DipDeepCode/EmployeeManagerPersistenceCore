package ru.ddc.jpaRepo4.testutils;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;
import ru.ddc.jpaRepo4.model.Department;
import ru.ddc.jpaRepo4.model.Employee;
import ru.ddc.jpaRepo4.model.Vacancy;
import ru.ddc.jpaRepo4.testutils.TestBuilder;

@AllArgsConstructor
@NoArgsConstructor(staticName = "aVacancy")
@With
public class VacancyTestBuilder implements TestBuilder<Vacancy> {
    private String position = "position";
    private Department department = null;
    private Employee employee = null;

    @Override
    public Vacancy build() {
        Vacancy vacancy = new Vacancy();
        vacancy.setPosition(position);
        vacancy.setDepartment(department);
        vacancy.setEmployee(employee);
        return vacancy;
    }
}
