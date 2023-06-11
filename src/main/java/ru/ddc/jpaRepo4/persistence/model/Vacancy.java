package ru.ddc.jpaRepo4.persistence.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "vacancy")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "position", length = 64)
    private String position;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "employee_personnel_number")
    private Employee employee;

}