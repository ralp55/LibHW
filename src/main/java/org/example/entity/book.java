package org.example.entity;


import java.time.LocalDate;

public class book {
    private Long id;
    private String title;
    private Integer printYear;
    private author author;
    private publisher publisher;
    private LocalDate createdDate;

    public book() {}

    public book(String title, Integer printYear, author author, publisher publisher) {
        this.title = title;
        this.printYear = printYear;
        this.author = author;
        this.publisher = publisher;
        this.createdDate = LocalDate.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Integer getPrintYear() { return printYear; }
    public void setPrintYear(Integer printYear) { this.printYear = printYear; }

    public author getAuthor() { return author; }
    public void setAuthor(author author) { this.author = author; }

    public publisher getPublisher() { return publisher; }
    public void setPublisher(publisher publisher) { this.publisher = publisher; }

    public LocalDate getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDate createdDate) { this.createdDate = createdDate; }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", printYear=" + printYear +
                ", author=" + author +
                ", publisher=" + publisher +
                ", createdDate=" + createdDate +
                '}';
    }
}