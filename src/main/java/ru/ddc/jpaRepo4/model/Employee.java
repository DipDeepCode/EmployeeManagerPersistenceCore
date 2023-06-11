package ru.ddc.jpaRepo4.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personnel_number", nullable = false)
    private Long personnelNumber;

    @Column(name = "firstname", length = 64)
    private String firstname;

    @Column(name = "lastname", length = 64)
    private String lastname;

    @Column(name = "patronymic", length = 64)
    private String patronymic;

    @ToString.Exclude
    @OneToOne(mappedBy = "employee")
    private Vacancy vacancy;

}