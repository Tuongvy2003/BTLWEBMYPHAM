package com.example.banmypham.model;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleid;

    @Column(name = "rolename", nullable = false, unique = true) // Ensure role name is unique
    private String rolename;

    // Constructors
    public Roles() {}

    public Roles(String rolename) {
        this.rolename = rolename;
    }

    // Getters and Setters
    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "roleid=" + roleid +
                ", rolename='" + rolename + '\'' +
                '}';
    }
}
