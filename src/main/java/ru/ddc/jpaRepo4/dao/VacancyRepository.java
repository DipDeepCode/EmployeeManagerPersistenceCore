package ru.ddc.jpaRepo4.dao;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ddc.jpaRepo4.model.Vacancy;

import java.util.List;
import java.util.Optional;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    @EntityGraph(attributePaths = {"department", "employee"})
    List<Vacancy> findAll();

    @EntityGraph(attributePaths = {"department", "employee"})
    List<Vacancy> findByDepartmentId(@Nonnull Long departmentId);

    @EntityGraph(attributePaths = {"department", "employee"})
    Optional<Vacancy> findById(@Nonnull Long id);
}