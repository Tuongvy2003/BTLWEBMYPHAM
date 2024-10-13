package com.example.banmypham.model;

import jakarta.persistence.*;

@Entity
@Table(name = "chitietdonhang")
public class ChiTietDonHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ctdonhangid; // ID chi tiết đơn hàng

    @ManyToOne // Quan hệ N:1 với DonHang
    @JoinColumn(name = "donhangid", nullable = false)
    private DonHang donhang; // Đơn hàng tương ứng

    @ManyToOne // Quan hệ N:1 với SanPham
    @JoinColumn(name = "sanphamid", nullable = false)
    private SanPham sanpham; // Sản phẩm tương ứng

    @Column(name = "soluongmua", nullable = false)
    private Integer soluongmua; // Số lượng mua

    @Column(name = "giatien", nullable = false)
    private Double giatien; // Giá tiền

    // Constructors
    public ChiTietDonHang() {}

    public ChiTietDonHang(DonHang donhang, SanPham sanpham, Integer soluongmua, Double giatien) {
        this.donhang = donhang;
        this.sanpham = sanpham;
        this.soluongmua = soluongmua;
        this.giatien = giatien;
    }

    // Getters and Setters
    public Integer getCtDonHangId() {
        return ctdonhangid;
    }

    public void setCtDonHangId(Integer ctdonhangid) {
        this.ctdonhangid = ctdonhangid;
    }

    public DonHang getDonHang() {
        return donhang;
    }

    public void setDonHang(DonHang donhang) {
        this.donhang = donhang;
    }

    public SanPham getSanPham() {
        return sanpham;
    }

    public void setSanPham(SanPham sanpham) {
        this.sanpham = sanpham;
    }

    public Integer getSoLuongMua() {
        return soluongmua;
    }

    public void setSoLuongMua(Integer soluongmua) {
        this.soluongmua = soluongmua;
    }

    public Double getGiaTien() {
        return giatien;
    }

    public void setGiaTien(Double giatien) {
        this.giatien = giatien;
    }
}
