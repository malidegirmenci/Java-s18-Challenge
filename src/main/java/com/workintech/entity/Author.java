package com.workintech.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "author", schema = "s18d5")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name ="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @OneToMany(mappedBy = "author", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
    private List<Book> books;

    public void addBook(Book book){
        if(books == null){
            books = new ArrayList<>();
        }
        books.add(book);
    }
    public String getFullName(){
        return getFirstName() + " " + getLastName();
    }


}
