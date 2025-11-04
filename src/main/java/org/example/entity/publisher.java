package org.example.entity;

import java.util.List;

public class publisher {
    private Long id;
    private String name;
    private String headOfficeCity;
    private Integer foundationYear;
    private List<book> books;

    public publisher() {}

    public publisher(String name, String headOfficeCity, Integer foundationYear) {
        this.name = name;
        this.headOfficeCity = headOfficeCity;
        this.foundationYear = foundationYear;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getHeadOfficeCity() { return headOfficeCity; }
    public void setHeadOfficeCity(String headOfficeCity) { this.headOfficeCity = headOfficeCity; }

    public Integer getFoundationYear() { return foundationYear; }
    public void setFoundationYear(Integer foundationYear) { this.foundationYear = foundationYear; }

    public List<book> getBooks() { return books; }
    public void setBooks(List<book> books) { this.books = books; }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", headOfficeCity='" + headOfficeCity + '\'' +
                ", foundationYear=" + foundationYear +
                '}';
    }
}