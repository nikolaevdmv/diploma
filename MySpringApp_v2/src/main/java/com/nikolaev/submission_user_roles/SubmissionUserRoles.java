package com.nikolaev.submission_user_roles;


import com.nikolaev.submission.Submission;
import com.nikolaev.submission_role.SubmissionRole;
import com.nikolaev.user.User;

import javax.persistence.*;

@Entity
@Table(name = "submission_user_roles", uniqueConstraints = @UniqueConstraint(columnNames = {"submission_id", "user_id"}))
public class SubmissionUserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "submission_id")
    private Submission submission;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private SubmissionRole role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SubmissionRole getRole() {
        return role;
    }

    public void setRole(SubmissionRole role) {
        this.role = role;
    }
}
