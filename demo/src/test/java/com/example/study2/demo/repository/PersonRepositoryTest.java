package com.example.study2.demo.repository;

import com.example.study2.demo.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;


    @Test
    void crud() {
        Person person = new Person();
        person.setName("Jinha");
        person.setAge(21);

        personRepository.save(person);
        
//        System.out.println(personRepository.findAll()); //전체 데이터 출력

        List<Person> people = personRepository.findAll();

        assertThat(people.size()).isEqualTo(1);
        assertThat(people.get(0).getName()).isEqualTo("Jinha");
        assertThat(people.get(0).getAge()).isEqualTo(21);
    }

}