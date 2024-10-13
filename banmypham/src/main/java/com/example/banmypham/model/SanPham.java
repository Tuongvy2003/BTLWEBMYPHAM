package com.example.banmypham.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sanpham")
@Data
public class SanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sanphamid;

    @Column(name = "tensanpham", nullable = false)
    private String tensanpham;

    @Column(name = "anhsanpham")
    private String anhsanpham;

    @Column(name = "gia", nullable = false)
    private Double gia;

    @Column(name = "soluong", nullable = false)
    private Integer soluong;

    @Column(name = "mota")
    private String mota;


    private Long danhmucid;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "danhmucid", insertable = false, nullable = false, updatable = false)
    private DanhMuc danhmuc;

    // Constructors
//    public SanPham() {}
//
//    public SanPham(String tensanpham, String anhsanpham, Double gia, Integer soluong, String mota, DanhMuc danhmuc) {
//        this.tensanpham = tensanpham;
//        this.anhsanpham = anhsanpham;
//        this.gia = gia;
//        this.soluong = soluong;
//        this.mota = mota;
//        this.danhmuc = danhmuc;
//    }
//
//
//
//    // Getters and Setters
//    public Integer getSanPhamId() {
//        return sanphamid;
//    }
//    public void setSanPhamId(Integer sanphamid) {
//        this.sanphamid = sanphamid;
//    }
//    public String getTenSanPham() {
//        return tensanpham;
//    }
//    public void setTenSanPham(String tensanpham) {
//        this.tensanpham = tensanpham;
//    }
//    public String getAnhSanPham() {
//        return anhsanpham;
//    }
//    public void setAnhSanPham(String anhsanpham) {
//        this.anhsanpham = anhsanpham;
//    }
//    public Double getGia() {
//        return gia;
//    }
//    public void setGia(Double gia) {
//        this.gia = gia;
//    }
//    public Integer getSoLuong() {
//        return soluong;
//    }
//    public void setSoLuong(Integer soluong) {
//        this.soluong = soluong;
//    }
//    public String getMoTa() {
//        return mota;
//    }
//    public void setMoTa(String mota) {
//        this.mota = mota;
//    }
//    public DanhMuc getDanhMuc() {
//        return danhmuc;
//    }
//    public void setDanhMuc(DanhMuc danhmuc) {
//        this.danhmuc = danhmuc;
//    }
}
