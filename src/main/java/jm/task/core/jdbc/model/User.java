package jm.task.core.jdbc.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users", schema = "kata_pp", catalog = "")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private Byte age;

}
