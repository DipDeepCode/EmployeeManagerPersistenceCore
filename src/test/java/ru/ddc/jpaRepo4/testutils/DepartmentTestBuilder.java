package ru.ddc.jpaRepo4.testutils;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;
import ru.ddc.jpaRepo4.model.Department;
import ru.ddc.jpaRepo4.model.Vacancy;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(staticName = "aDepartment")
@With
public class DepartmentTestBuilder implements TestBuilder<Department> {
    private String number = "department_number";
    private String name = "department_name";
    private List<Vacancy> vacancyList = new ArrayList<>();

    @Override
    public Department build() {
        Department department = new Department();
        department.setName(name);
        department.setNumber(number);
        department.setVacancies(vacancyList);
        return department;
    }
}
