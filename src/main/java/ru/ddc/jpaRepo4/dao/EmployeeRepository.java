package ru.ddc.jpaRepo4.dao;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ddc.jpaRepo4.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @EntityGraph(attributePaths = "vacancy")
    List<Employee> findAll();

    @EntityGraph(attributePaths = "vacancy")
    Optional<Employee> findById(@Nonnull Long id);

    @EntityGraph(attributePaths = "vacancy")
    List<Employee> findByVacancyNull();
}