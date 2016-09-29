package com.aws.petproject.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.aws.petproject.persistence.entitiy.Person;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Created by 212476263 on 2016.09.29..
 */
@JsonInclude( Include.NON_NULL)
public class PersonResponse {

    public PersonResponse( Person person ) {
        this.person = person;
    }

    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson( Person person ) {
        this.person = person;
    }
}
