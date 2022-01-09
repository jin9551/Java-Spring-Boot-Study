package com.example.study2.demo.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private int age;

    private String hobby;

    @NonNull
    private String bloodType;

    private String address;

    private LocalDate birthday;

    private String job;

    @ToString.Exclude //필드에 따로 선언하는게 클래스 상단에 선언하는 것보다 좋다.
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Block block;

}
