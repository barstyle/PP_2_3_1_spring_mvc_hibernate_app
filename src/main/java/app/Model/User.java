package app.Model;

import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

// 2. Должен быть класс User с произвольными полями (id, name и т.п.).
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    @NotEmpty(message = "Firstname should not be empty")
    @Size(min = 2, max = 50, message = "Firstname should be between 2 and 30 characters")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "Lastname should not be empty")
    @Size(min = 2, max = 50, message = "Lastname should be between 2 and 30 characters")
    private String lastName;

    @Column(name = "email", unique = true)
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;

    public User() {
    }

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
               "id = " + id +
               ", firstName = '" + firstName + '\'' +
               ", lastName = '" + lastName + '\'' +
               ", email = '" + email + '\'' +
               '}';
    }
}
