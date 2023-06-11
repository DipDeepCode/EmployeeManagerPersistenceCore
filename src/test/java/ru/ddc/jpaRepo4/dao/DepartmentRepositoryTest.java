package ru.ddc.jpaRepo4.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import ru.ddc.jpaRepo4.persistence.model.Department;
import ru.ddc.jpaRepo4.persistence.dao.DepartmentRepository;

import static ru.ddc.jpaRepo4.testutils.DepartmentTestBuilder.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@DataJpaTest
public class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void whenFindAllDepartments_thenListSizeEqualsFour() {
        List<Department> departmentList = departmentRepository.findAll();
        showDepartmentList(departmentList);
        assertEquals(4, departmentList.size());
    }

    @Test
    public void whenFindDepartmentById_thenDepartmentIdEqualsToSpecified() {
        Long departmentId = 1L;
        Department department = departmentRepository.findById(departmentId).orElseThrow();
        showDepartment(department);
        assertEquals(departmentId, department.getId());
    }

    @Test
    public void whenSaveDepartment_thenDepartmentCounterLagerByOne() {
        long counterBeforeSave = departmentRepository.count();
        Department department = aDepartment().build();
        showDepartment(department);
        departmentRepository.save(department);
        long counterAfterSave = departmentRepository.count();
        assertEquals(1, counterAfterSave - counterBeforeSave);
    }

    @Test
    public void whenUpdateDepartment_thenNewValueWillBeSaved() {
        Long departmentId = 1L;
        String newName = "new_name";
        Department department = departmentRepository.findById(departmentId).orElseThrow();
        showDepartment(department);
        department.setName(newName);
        department = departmentRepository.saveAndFlush(department);
        showDepartment(department);
        assertEquals(newName, department.getName());
    }

    @Test
    public void whenDeleteDepartmentByIdWithVacancies_thenThrownException() {
        Long departmentId = 1L;
        Department department = departmentRepository.findById(departmentId).orElseThrow();
        departmentRepository.deleteById(department.getId());
        assertThrows(DataIntegrityViolationException.class, () -> departmentRepository.flush());
    }

    @Test
    public void whenDeleteDepartmentByIdWithoutVacancies_thenDepartmentCounterLessByOne() {
        Long departmentId = 4L;
        Department department = departmentRepository.findById(departmentId).orElseThrow();
        long counterBeforeDelete = departmentRepository.count();
        departmentRepository.deleteById(department.getId());
        departmentRepository.flush();
        long counterAfterDelete = departmentRepository.count();
        assertEquals(1, counterBeforeDelete - counterAfterDelete);
    }

    private static void showDepartmentList(List<Department> departmentList) {
        departmentList.forEach(DepartmentRepositoryTest::showDepartment);
    }

    private static void showDepartment(Department department) {
        System.out.println(department);
        department.getVacancies().forEach(vacancy -> System.out.println("   -" + vacancy));
    }
}
