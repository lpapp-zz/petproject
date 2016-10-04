package com.aws.petproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aws.petproject.persistence.dto.PersonDTO;
import com.aws.petproject.persistence.dto.ProfilePictureDTO;
import com.aws.petproject.persistence.entitiy.Person;
import com.aws.petproject.persistence.repository.PersonRepository;

/**
 * Created by 212476263 on 2016.10.03..
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person convertAndSavePerson( PersonDTO personDTO) {
        Person person = new Person();
        person.setForname( personDTO.getForname() );
        person.setLastName( personDTO.getLastName() );
        person.setUsername( personDTO.getUsername() );
        person.setPassword( personDTO.getPassword() );

        System.out.println("+++++++++++++++++: " + person);

        return personRepository.saveAndFlush( person );
    }

    public PersonDTO getPersonDTO( Integer personId) {
        Person person = getPerson( personId );
        PersonDTO personDTO = new PersonDTO();
        personDTO.setForname( person.getForname() );
        personDTO.setLastName( person.getLastName() );

        ProfilePictureDTO profilePictureDTO = new ProfilePictureDTO();
        profilePictureDTO.setPicturePath(person.getProfilePicture() != null ? person.getProfilePicture().getPicturePath() : null);
        personDTO.setProfilePicture( profilePictureDTO );

        return personDTO;
    }

    public Person getPerson(Integer personId) {
        return personRepository.getOne( personId );
    }

    public Person savePerson(Person person) {
        return personRepository.saveAndFlush( person );
    }

}
