package com.alex.familytree.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "family_tree")
@Getter @Setter @NoArgsConstructor
public class Human {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "humanSeq")
    @SequenceGenerator(name = "humanSeq", sequenceName = "human_seq", allocationSize = 1)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    @Size(min = 2, message = "name must be min 2 symbols")
    @NotBlank(message = "name is required")
    private String name;

    @Column(name = "last_name")
    @NotBlank(message = "surname is required")
    private String surname;

    @Column(name = "gender")
    private String gender;

    @Column(name = "date_of_birth")
    @JsonFormat(pattern = "MM-dd-yyyy")
    @Past(message = "Date of birth must be in the past and have the format 'MM-dd-yyyy'")
    private LocalDate dateOfBirth;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "relationships",
            joinColumns = {@JoinColumn (name = "child_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "stepparent_id", referencedColumnName = "id")}
    )
    private Set<Human> stepparents = new HashSet<>();


    @JsonIgnore
    @ManyToMany(mappedBy = "stepparents")
    private Set<Human> children = new HashSet<>();

    public Human(int id, String name, String surname, String gender, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

}
