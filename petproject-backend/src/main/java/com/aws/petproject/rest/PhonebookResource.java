package com.aws.petproject.rest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aws.petproject.domain.PersonResponse;
import com.aws.petproject.ec2.ApplicationInfoBean;
import com.aws.petproject.persistence.repository.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.aws.petproject.persistence.entitiy.Person;

/**
 * Created by 212476263 on 2016.09.29..
 */
@RestController
public class PhonebookResource {

    private final PersonRepository personRepository;

    private final ObjectMapper objectMapper;
    private final ApplicationInfoBean infoBean;
    private static final String ALL_ORIGIN = "*";

    @Autowired
    public PhonebookResource( ObjectMapper objectMapper, ApplicationInfoBean infoBean, PersonRepository personRepository ) {
        this.objectMapper = objectMapper;
        this.infoBean = infoBean;
        this.personRepository = personRepository;
    }

    @RequestMapping(value = "/instance-info", method = RequestMethod.GET)
    public ApplicationInfoBean info() {
        return this.infoBean;
    }

    @POST
    @Consumes( {"application/json"} )
    @Produces( {"application/json"} )
    Response savePerson( @NotNull(message = "Invalid input encountered") @Valid Person person ) {
        personRepository.saveAndFlush(person);
        return Response.accepted().build();
    }

    @Produces( {"application/json"} )
    @RequestMapping( method = RequestMethod.GET, value = "/person/{personId}" )
    Person getPerson( @PathVariable Integer personId ) {
        return personRepository.getOne( personId );
    }
}
