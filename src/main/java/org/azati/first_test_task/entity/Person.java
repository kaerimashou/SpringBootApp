package org.azati.first_test_task.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.azati.first_test_task.enums.Role;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PERSON")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(generator = "increment")
    private Long id;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "NAME")
    @NotEmpty
    @Size(min = 2, max = 30, message = "Name shouldn't be less than 2 and greater than 30 symbols")
    private String name;

    @Column(name = "BIRTH_DATE")
    @NotNull(message = "Birthdate must be filled")
    @Temporal(TemporalType.DATE)
    @Past(message = "Date of birth must contain past date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @Column(name = "EMAIL", unique = true)
    @NotBlank(message = "Mail should be filled")
    @Email(message = "Enter valid mail")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "borrower",fetch = FetchType.EAGER)
    private List<Book> borrowedBooks;
}
