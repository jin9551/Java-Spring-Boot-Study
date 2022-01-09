package com.example.study2.demo.repository;

import com.example.study2.demo.domain.Person;
import com.sun.jdi.connect.Connector;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        person.setBloodType("A");

        personRepository.save(person);
        
        System.out.println(personRepository.findAll()); //전체 데이터 출력

        List<Person> people = personRepository.findAll();

        assertThat(people.size()).isEqualTo(1);
        assertThat(people.get(0).getName()).isEqualTo("Jinha");
        assertThat(people.get(0).getAge()).isEqualTo(21);
        assertThat(people.get(0).getBloodType()).isEqualTo("A");
    }

    @Test
    void hasCodeAndEquals(){
        Person person1 = new Person("Jinha", 10, "A");
        Person person2 = new Person("Jinha", 10, "B");

        System.out.println(person1.equals(person2));
        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());

        Map<Person, Integer> map = new HashMap<>();
        map.put(person1, person1.getAge());

        System.out.println(map);
        System.out.println(map.get(person2));
    }

}