package ru.ddc.jpaRepo4.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import ru.ddc.jpaRepo4.model.Department;
import ru.ddc.jpaRepo4.model.Vacancy;

import static ru.ddc.jpaRepo4.testutils.VacancyTestBuilder.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@DataJpaTest
public class VacancyRepositoryTest {

    @Autowired
    private VacancyRepository vacancyRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void whenFindAllVacancies_thenListSizeEqualsSix() {
        List<Vacancy> vacancyList = vacancyRepository.findAll();
        showVacancyList(vacancyList);
        assertEquals(6, vacancyList.size());
    }

    @Test
    public void whenFindAllVacanciesByDepartmentNumber_thenListSizeEqualsSomeValue() {
        Long departmentId = 1L;
        List<Vacancy> vacancyList = vacancyRepository.findByDepartmentId(departmentId);
        showVacancyList(vacancyList);
        assertEquals(3, vacancyList.size());
    }

    @Test
    public void whenFindVacancyById_thenVacancyIdEqualsToSpecified() {
        Long vacancyId = 1L;
        Vacancy vacancy = vacancyRepository.findById(vacancyId).orElseThrow();
        showVacancy(vacancy);
        assertEquals(vacancyId, vacancy.getId());
    }

    @Test
    public void whenSaveVacancyWithoutDepartment_thenThrowException() {
        Vacancy vacancy = aVacancy().build();
        showVacancy(vacancy);
        assertThrows(DataIntegrityViolationException.class, () -> vacancyRepository.saveAndFlush(vacancy));
    }

    @Test
    public void whenSaveVacancyWithDepartment_thenVacancyCounterLagerByOne() {
        long counterBeforeSave = vacancyRepository.count();
        Vacancy vacancy = aVacancy().build();
        Department department = departmentRepository.findById(1L).orElseThrow();
        department.addVacancy(vacancy);
        showVacancy(vacancy);
        vacancyRepository.saveAndFlush(vacancy);
        long counterAfterSave = vacancyRepository.count();
        assertEquals(1, counterAfterSave - counterBeforeSave);
    }

    @Test
    public void whenDeleteVacancyById_thenVacancyCounterLessByOne() {
        long counterBeforeDelete = vacancyRepository.count();
        Long vacancyId = 1L;
        Vacancy vacancy = vacancyRepository.findById(vacancyId).orElseThrow();
        vacancyRepository.deleteById(vacancy.getId());
        vacancyRepository.flush();
        long counterAfterDelete = vacancyRepository.count();
        assertEquals(1, counterBeforeDelete - counterAfterDelete);
    }

    private static void showVacancyList(List<Vacancy> vacancyList) {
        vacancyList.forEach(VacancyRepositoryTest::showVacancy);
    }

    private static void showVacancy(Vacancy vacancy) {
        System.out.println(vacancy);
        System.out.println("    employee = " + vacancy.getEmployee());
        System.out.println("    department = " + vacancy.getDepartment());
    }
}
