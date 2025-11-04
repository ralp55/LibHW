package org.example.entity;

import java.time.LocalDate;
import java.util.List;

public class author {
    private Long id;
    private String firstName;
    private String lastName;
    private String birthCity;
    private LocalDate birthDate;
    private LocalDate deathDate;
    private List<book> books;

    public author() {}

    public author(String firstName, String lastName, String birthCity,
                  LocalDate birthDate, LocalDate deathDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthCity = birthCity;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getBirthCity() { return birthCity; }
    public void setBirthCity(String birthCity) { this.birthCity = birthCity; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public LocalDate getDeathDate() { return deathDate; }
    public void setDeathDate(LocalDate deathDate) { this.deathDate = deathDate; }

    public List<book> getBooks() { return books; }
    public void setBooks(List<book> books) { this.books = books; }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthCity='" + birthCity + '\'' +
                ", birthDate=" + birthDate +
                ", deathDate=" + deathDate +
                '}';
    }
}