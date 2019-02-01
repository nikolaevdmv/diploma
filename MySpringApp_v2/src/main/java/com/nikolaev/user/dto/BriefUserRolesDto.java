package com.nikolaev.user.dto;

import java.util.List;

public class BriefUserRolesDto extends BriefUserDto {
    private List<Integer> roles;

    public BriefUserRolesDto() {
    }


    public BriefUserRolesDto(Long id, String username, String email, String firstname, String lastname, List<Integer> roles) {
        super(id, username, email, firstname, lastname);
        this.roles = roles;
    }

    public List<Integer> getRoles() {
        return roles;
    }
}
