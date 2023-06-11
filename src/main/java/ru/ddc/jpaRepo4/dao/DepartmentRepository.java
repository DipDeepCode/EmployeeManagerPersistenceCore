package ru.ddc.jpaRepo4.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ddc.jpaRepo4.model.Department;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @EntityGraph(attributePaths = "vacancies")
    List<Department> findAll();
}