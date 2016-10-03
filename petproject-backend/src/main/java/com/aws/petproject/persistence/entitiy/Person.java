package com.aws.petproject.persistence.entitiy;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


/**
 * Created by 212476263 on 2016.09.29..
 */
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "forname", nullable = false)
    private String forname;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_picture_id")
    private ProfilePicture profilePicture;

    private String username;

    private String password;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

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

    public ProfilePicture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture( ProfilePicture profilePicture ) {
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

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Person person = (Person) o;
        return Objects.equals( id, person.id ) &&
                 Objects.equals( lastName, person.lastName ) &&
                 Objects.equals( forname, person.forname ) &&
                 Objects.equals( profilePicture, person.profilePicture ) &&
                 Objects.equals( username, person.username ) &&
                 Objects.equals( password, person.password );
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, lastName, forname, profilePicture, username, password );
    }

    @Override
    public String toString() {
        return "Person{" +
                 "id=" + id +
                 ", lastName='" + lastName + '\'' +
                 ", forname='" + forname + '\'' +
                 ", profilePicture=" + profilePicture +
                 ", username='" + username + '\'' +
                 ", password='" + password + '\'' +
                 '}';
    }
}
