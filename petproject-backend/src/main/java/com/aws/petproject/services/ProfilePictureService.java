package com.aws.petproject.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.aws.petproject.persistence.entitiy.Person;
import com.aws.petproject.persistence.entitiy.ProfilePicture;
import com.aws.petproject.persistence.repository.PersonRepository;
import com.aws.petproject.persistence.repository.ProfilePictureRepository;
import com.aws.petproject.queue.SqsQueueSender;

/**
 * Created by 212476263 on 2016.09.28..
 */
@Service
public class ProfilePictureService {

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ProfilePictureRepository profilePictureRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private SqsQueueSender sqsQueueSender;

    public String uploadResource( MultipartFile multipartFile) throws IOException {
        String resourcePath = "s3://petproject-bucket/";
        String resourceUrl = resourcePath + multipartFile.getOriginalFilename();

        Resource resource = this.resourceLoader.getResource(resourceUrl);
        WritableResource writableResource = (WritableResource) resource;
        try (OutputStream outputStream = writableResource.getOutputStream()) {
            outputStream.write(multipartFile.getBytes());
        }

        return resourceUrl;
    }

    public InputStream downloadResource(String profilePicturePath) throws IOException {
        Resource resource = this.resourceLoader.getResource(profilePicturePath);
        return resource.getInputStream();
    }

    @Transactional
    public void saveProfilePicture(MultipartFile file, Integer personId) throws IOException {
        String fileUrl = uploadResource(file);
        Person person = personService.getPerson( personId );
        ProfilePicture profilePicture = Optional.ofNullable(person.getProfilePicture()).orElse( new ProfilePicture() );
        profilePicture.setPicturePath( fileUrl );

        profilePicture = profilePictureRepository.saveAndFlush( profilePicture );

        person.setProfilePicture( profilePicture );
        personService.savePerson( person );

        sqsQueueSender.send( fileUrl );
    }

    public InputStream loadProfilePicture(Integer personId) throws IOException {
        Person person = personService.getPerson( personId );

        if (person != null && person.getProfilePicture() != null && !person.getProfilePicture().getPicturePath().isEmpty()) {
            return downloadResource( person.getProfilePicture().getPicturePath() );
        } else {
            return null;
        }
    }
}
