package com.example.banmypham.model;

import jakarta.persistence.*;

@Entity
@Table(name = "taikhoan")
public class TaiKhoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taikhoanid;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToOne // Thiết lập quan hệ N:1 với Roles
    @JoinColumn(name = "RoleID", referencedColumnName = "RoleID", nullable = false)
    private Roles role;

    private String sdt;
    private String diachi;
    private String email;

    // Constructors
    public TaiKhoan() {}

    public TaiKhoan(String username, String password, Roles role, String sdt, String diachi, String email) {
        this.username = username;
        this.password = password;
        this.role = role; // Thiết lập vai trò
        this.sdt = sdt;
        this.diachi = diachi;
        this.email = email;
    }

    // Getters and Setters
    public Integer getTaiKhoanID() {
        return taikhoanid;
    }

    public void setTaiKhoanID(Integer taikhoanid) {
        this.taikhoanid = taikhoanid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diachi;
    }

    public void setDiaChi(String diachi) {
        this.diachi = diachi;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
