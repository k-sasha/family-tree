package com.alex.familytree.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "family_tree")
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "relationships",
            joinColumns = {@JoinColumn (name = "child_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "stepparent_id", referencedColumnName = "id")}
    )
    private Set<Human> stepparents = new HashSet<>();


    @JsonIgnore
    @ManyToMany(mappedBy = "stepparents",  cascade = CascadeType.ALL)
    private Set<Human> children = new HashSet<>();


    public Human() {
    }

    public Human(int id, String name, String surname, String gender, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<Human> getStepparents() {
        return stepparents;
    }

    public void setStepparents(Set<Human> parents) {
        this.stepparents = parents;
    }

    public Set<Human> getChildren() {
        return children;
    }

    public void setChildren(Set<Human> children) {
        this.children = children;
    }
}
