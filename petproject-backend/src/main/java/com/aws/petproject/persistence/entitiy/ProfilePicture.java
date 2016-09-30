package com.aws.petproject.persistence.entitiy;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



/**
 * Created by 212476263 on 2016.09.29..
 */
@JsonAutoDetect
@Entity
@Table(name = "profile_picture")
public class ProfilePicture {

    @Id
    @NotNull
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "profile_picture_id", updatable = false )
    private Integer profilePictureId;

    @Column(name = "picture_path", nullable = false)
    private String picturePath;

    public Integer getProfilePictureId() {
        return profilePictureId;
    }

    public void setProfilePictureId( Integer profilePictureId ) {
        this.profilePictureId = profilePictureId;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath( String picturePath ) {
        this.picturePath = picturePath;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        ProfilePicture that = (ProfilePicture) o;
        return Objects.equals( profilePictureId, that.profilePictureId ) &&
                 Objects.equals( picturePath, that.picturePath );
    }

    @Override
    public int hashCode() {
        return Objects.hash( profilePictureId, picturePath );
    }

    @Override
    public String toString() {
        return "ProfilePicture{" +
                 "profilePictureId=" + profilePictureId +
                 ", picturePath='" + picturePath + '\'' +
                 '}';
    }
}
