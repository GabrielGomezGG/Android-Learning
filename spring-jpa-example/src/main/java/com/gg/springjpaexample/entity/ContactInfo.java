package com.gg.springjpaexample.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contact_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name = "student_id")
    private Student student;


    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String zip;
}
