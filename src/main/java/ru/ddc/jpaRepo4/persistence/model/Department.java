package ru.ddc.jpaRepo4.persistence.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "name", length = 64)
    private String name;

    @ToString.Exclude
    @OneToMany(
            mappedBy = "department",
            fetch = FetchType.EAGER
    )
    private List<Vacancy> vacancies = new ArrayList<>();

    public void addVacancy(Vacancy vacancy) {
        if (vacancies == null) {
            vacancies = new ArrayList<>();
        }
        vacancies.add(vacancy);
        vacancy.setDepartment(this);
    }

    public void removeVacancy(Vacancy vacancy) {
        if (vacancies != null) {
            vacancies.remove(vacancy);
            vacancy.setDepartment(null);
        }
    }
}