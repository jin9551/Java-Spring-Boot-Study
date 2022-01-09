package com.example.study2.demo.service;

import com.example.study2.demo.domain.Block;
import com.example.study2.demo.domain.Person;
import com.example.study2.demo.repository.BlockRepository;
import com.example.study2.demo.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BlockRepository blockRepository;

    @Test
    void getPeopleExcludeBlocks(){
        givenPeople();
        givenBlocks();

        List<Person> result = personService.getPeopleExcludeBlocks();

//        System.out.println(result);
        result.forEach(System.out::println);
    }

    @Test
    void cascadeTest(){
        givenPeople();

        List<Person> result = personRepository.findAll();

        result.forEach(System.out::println);

        Person person = result.get(3);
        person.getBlock().setStartDate(LocalDate.now());
        person.getBlock().setEndDate(LocalDate.now());

        personRepository.save(person);
        personRepository.findAll().forEach(System.out::println);

//        personRepository.delete(person);
//        personRepository.findAll().forEach(System.out::println);
//        blockRepository.findAll().forEach(System.out::println);

        person.setBlock(null);
        personRepository.save(person);
        personRepository.findAll().forEach(System.out::println);
        blockRepository.findAll().forEach(System.out::println);
    }

    @Test
    void getPerson(){
        givenPeople();
        Person person = personService.getPerson(3L);
        System.out.println(person);
    }

    private void givenBlocks() {
        givenBlock("Jinha");
    }

    private Block givenBlock(String name) {
        return blockRepository.save(new Block(name));
    }

    private void givenBlockPerson(String name, int age, String bloodType){
        Person blockPerson = new Person(name, age, bloodType);
        blockPerson.setBlock(new Block(name));
        personRepository.save(blockPerson);
    }

    private void givenPeople() {
        givenPerson("Jinha", 10, "A");
        givenPerson("Jinho", 9, "B");
        givenBlockPerson("HaeYeon", 11, "O");
        givenBlockPerson("Jinha", 11, "AB");

    }

    private void givenPerson(String name, int age, String bloodType) {
        personRepository.save(new Person(name, age, bloodType));
    }

}