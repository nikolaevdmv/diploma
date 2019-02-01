package com.nikolaev.user.dto;

public class BriefUserDto {
    protected Long id;
    protected String username;
    protected String email;
    protected String firstname;
    protected String lastname;

    public BriefUserDto() {
    }

    public BriefUserDto(String username) {
        this.username = username;
    }

    public BriefUserDto(Long id, String username, String email, String firstname, String lastname) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
