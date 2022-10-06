package org.azati.first_test_task.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "PERSON")
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(generator = "increment")
    @Getter
    @Setter
    private Long id;

    @Column(name = "NAME")
    @Getter
    @Setter
    @NotEmpty
    @Size(min = 2, max = 30, message = "Name shouldn't be less than 2 and greater than 30 symbols")
    private String name;

    @Column(name = "BIRTH_DATE")
    @Getter
    @Setter
    @NotNull(message = "Birthdate must be filled")
    @Temporal(TemporalType.DATE)
    @Past(message = "Date of birth must contain past date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @Column(name = "EMAIL")
    @Getter
    @Setter
    @NotBlank(message = "Mail should be filled")
    @Email(message = "Enter valid mail")
    private String email;
}
