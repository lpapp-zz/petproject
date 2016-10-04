package com.aws.petproject.rest;

import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

import javax.servlet.annotation.MultipartConfig;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.aws.petproject.ec2.ApplicationInfoBean;
import com.aws.petproject.persistence.dto.PersonDTO;
import com.aws.petproject.persistence.entitiy.ProfilePicture;
import com.aws.petproject.services.PersonService;
import com.aws.petproject.services.ProfilePictureService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.aws.petproject.persistence.entitiy.Person;

/**
 * Created by 212476263 on 2016.09.29..
 */
@RestController
public class PhonebookResource {

    private final PersonService personService;
    private final ProfilePictureService profilePictureService;

    private final ObjectMapper objectMapper;
    private final ApplicationInfoBean infoBean;
    private static final String ALL_ORIGIN = "*";

    @Autowired
    public PhonebookResource( ObjectMapper objectMapper, ApplicationInfoBean infoBean, PersonService personService, ProfilePictureService profilePictureService ) {
        this.objectMapper = objectMapper;
        this.infoBean = infoBean;
        this.personService = personService;
        this.profilePictureService = profilePictureService;
    }

    @RequestMapping(value = "/instance-info", method = RequestMethod.GET)
    public ApplicationInfoBean info() {
        return this.infoBean;
    }

    @Consumes( {"application/json"} )
    @Produces( {"application/json"} )
    @RequestMapping( method = RequestMethod.POST, value = "/person" )
    Response savePerson( @RequestBody PersonDTO personDTO ) {
        personService.convertAndSavePerson( personDTO );
        return Response.accepted().build();
    }

    @Produces( {"application/json"} )
    @RequestMapping( method = RequestMethod.GET, value = "/person/{personId}" )
    PersonDTO getPerson( @PathVariable Integer personId ) {
        return personService.getPersonDTO( personId );
    }

    @Consumes({"multipart/form-data"})
    @RequestMapping( method = RequestMethod.POST, value = "/upload/{personId}" )
    public Response handleFileUpload( MultipartHttpServletRequest request,
                                      @PathVariable Integer personId) {
        Iterator<String> iterator = request.getFileNames();
        try {
            while ( iterator.hasNext() ) {
                String fileName = iterator.next();
                System.out.println(fileName);
                MultipartFile file = request.getFile( fileName );

//                if ( !file.isEmpty() ) {
                    profilePictureService.saveProfilePicture( file, personId );
//                }
            }
        } catch ( IOException ex ) {
            return Response.serverError().build();
        }

        return Response.accepted().build();
    }

}
