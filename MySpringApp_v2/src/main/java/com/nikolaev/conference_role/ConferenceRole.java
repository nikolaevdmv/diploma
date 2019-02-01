package com.nikolaev.conference_role;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "conference_role")
public class ConferenceRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private ConferenceRoleName name;

    @ManyToMany(mappedBy = "roles")
    private List<ConferenceRoleListHolder> roleListHolder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConferenceRoleName getName() {
        return name;
    }

    public void setName(ConferenceRoleName name) {
        this.name = name;
    }

    public List<ConferenceRoleListHolder> getRoleListHolder() {
        return roleListHolder;
    }

    public void setRoleListHolder(List<ConferenceRoleListHolder> roleListHolder) {
        this.roleListHolder = roleListHolder;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof ConferenceRole)) {
            return false;
        }
        ConferenceRole role = (ConferenceRole) obj;
        return role.getName().equals(name);
    }

}
