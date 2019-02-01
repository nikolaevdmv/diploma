package com.nikolaev.submission_role;

import com.nikolaev.submission_user_roles.SubmissionUserRoles;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "submission_role")
public class SubmissionRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private SubmissionRoleName name;

    @OneToMany(mappedBy = "role")
    private List<SubmissionUserRoles> userRoles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SubmissionRoleName getName() {
        return name;
    }

    public void setName(SubmissionRoleName name) {
        this.name = name;
    }

    public List<SubmissionUserRoles> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<SubmissionUserRoles> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof SubmissionRole)) {
            return false;
        }
        SubmissionRole role = (SubmissionRole) obj;
        return role.getName().equals(name);
    }
}
