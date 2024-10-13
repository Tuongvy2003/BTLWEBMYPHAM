package com.example.banmypham.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "danhmuc")
public class DanhMuc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer danhmucid;
    @Column(name = "tendanhmuc", nullable = false)
    private String tendanhmuc;
    @Column(name = "anh")
    private String anh;


    @JsonIgnore
    @OneToMany(mappedBy = "danhmuc", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SanPham> sanphams;

    // Constructors
    public DanhMuc() {}

    public DanhMuc(String tendanhmuc, String anh) {
        this.tendanhmuc = tendanhmuc;
        this.anh = anh;
    }

    // Getters and Setters
    public Integer getDanhMucid() {
        return danhmucid;
    }
    public void setDanhMucid(Integer danhmucid) { // Make sure this is correct
        this.danhmucid = danhmucid;
    }
    public String getTenDanhMuc() {
        return tendanhmuc;
    }
    public void setTenDanhMuc(String tendanhmuc) {
        this.tendanhmuc = tendanhmuc;
    }
    public String getAnh() {
        return anh;
    }
    public void setAnh(String anh) {
        this.anh = anh;
    }
}
