package com.aws.petproject.persistence.dto;

import com.aws.petproject.persistence.entitiy.ProfilePicture;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by 212476263 on 2016.10.03..
 */
@JsonInclude( JsonInclude.Include.NON_NULL)
public class PersonDTO {

    private String lastName;

    private String forname;

    private ProfilePictureDTO profilePicture;

    private String username;

    private String password;

    public String getLastName() {
        return lastName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    public String getForname() {
        return forname;
    }

    public void setForname( String forname ) {
        this.forname = forname;
    }

    public ProfilePictureDTO getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture( ProfilePictureDTO profilePicture ) {
        this.profilePicture = profilePicture;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }
}
