package com.aws.petproject.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aws.petproject.persistence.entitiy.Person;

/**
 * Created by 212476263 on 2016.09.29..
 */
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
