package com.alex.familytree.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
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
    private String name;

    @Column(name = "last_name")
    private String surname;

    @Column(name = "gender")
    private String gender;

    @Column(name = "date_of_birth")
    @JsonFormat(pattern = "MM-dd-yyyy")
    private Date dateOfBirth;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "relationships",
            joinColumns = {@JoinColumn (name = "child_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "parent_id", referencedColumnName = "id")}
    )
    private Set<Human> parents = new HashSet<>();


    @JsonIgnore
    @ManyToMany(mappedBy = "parents")
    private Set<Human> children = new HashSet<>();


    public Human() {
    }

    public Human(int id, String name, String surname, String gender, Date dateOfBirth) {
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<Human> getParents() {
        return parents;
    }

    public void setParents(Set<Human> parents) {
        this.parents = parents;
    }

    public Set<Human> getChildren() {
        return children;
    }

    public void setChildren(Set<Human> children) {
        this.children = children;
    }
}
