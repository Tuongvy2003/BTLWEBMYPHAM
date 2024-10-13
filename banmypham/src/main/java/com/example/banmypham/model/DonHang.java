package com.example.banmypham.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "donhang")
public class DonHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer donhangid;

    private LocalDateTime ngaydathang;

    private Double tonggiadonhang;

    private String trangthai;

    @Column(name = "taikhoanid")
    private Integer taikhoanid; // Thay đổi thành Integer

    // Constructors
    public DonHang() {}

    public DonHang(LocalDateTime ngaydathang, Double tonggiadonhang, String trangthai, Integer taikhoanid) {
        this.ngaydathang = ngaydathang;
        this.tonggiadonhang = tonggiadonhang;
        this.trangthai = trangthai;
        this.taikhoanid = taikhoanid;
    }

    // Getters and Setters
    public Integer getDonhangid() {
        return donhangid;
    }

    public void setDonhangid(Integer donhangid) {
        this.donhangid = donhangid;
    }

    public LocalDateTime getNgaydathang() {
        return ngaydathang;
    }

    public void setNgaydathang(LocalDateTime ngaydathang) {
        this.ngaydathang = ngaydathang;
    }

    public Double getTonggiadonhang() {
        return tonggiadonhang;
    }

    public void setTonggiadonhang(Double tonggiadonhang) {
        this.tonggiadonhang = tonggiadonhang;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public Integer getTaiKhoanID() {
        return taikhoanid;
    }

    public void setTaiKhoanID(Integer taikhoanid) {
        this.taikhoanid = taikhoanid;
    }
}
