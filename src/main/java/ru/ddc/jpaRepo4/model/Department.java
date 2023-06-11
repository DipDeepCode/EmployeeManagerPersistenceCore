package ru.ddc.jpaRepo4.model;

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
    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    private List<Vacancy> vacancies = new ArrayList<>();

}