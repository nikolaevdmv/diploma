package com.nikolaev.conference_user_roles;

import com.nikolaev.conference.Conference;
import com.nikolaev.conference_role.ConferenceRoleListHolder;
import com.nikolaev.user.User;

import javax.persistence.*;

@Entity
@Table(name = "conference_user_roles", uniqueConstraints = @UniqueConstraint(columnNames = {"conference_id", "user_id", "role_list_id"}))
public class ConferenceUserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "conference_id")
    private Conference conference;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_list_id")
    private ConferenceRoleListHolder roleList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ConferenceRoleListHolder getRoleList() {
        return roleList;
    }

    public void setRoleList(ConferenceRoleListHolder roleList) {
        this.roleList = roleList;
    }
}
