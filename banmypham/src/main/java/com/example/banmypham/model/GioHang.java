package com.example.banmypham.model;

public class GioHang {
    private int sanphamid;
    private String tensanpham;
    private String anhsanpham;
    private int gia;
    private int soluong;

    // Constructor
    public GioHang(int sanphamid, String tensanpham, String anhsanpham, int gia, int soluong) {
        this.sanphamid = sanphamid;
        this.tensanpham = tensanpham;
        this.anhsanpham = anhsanpham;
        this.gia = gia;
        this.soluong = soluong;
    }

    // Getter và Setter cho sanphamid
    public int getSanPhamId() {
        return sanphamid;
    }

    public void setSanPhamId(int sanphamid) {
        this.sanphamid = sanphamid;
    }

    // Getter và Setter cho tensanpham
    public String getTenSanPham() {
        return tensanpham;
    }

    public void setTenSanPham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    // Getter và Setter cho anhsanpham
    public String getAnhSanPham() {
        return anhsanpham;
    }

    public void setAnhSanPham(String anhsanpham) {
        this.anhsanpham = anhsanpham;
    }

    // Getter và Setter cho gia
    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    // Getter và Setter cho soluong
    public int getSoLuong() {
        return soluong;
    }

    public void setSoLuong(int soluong) {
        this.soluong = soluong;
    }

    // Phương thức để tính tổng giá trị của sản phẩm trong giỏ hàng
    public int getTongGiaTri() {
        return gia * soluong;
    }

    @Override
    public String toString() {
        return "GioHang{" +
                "sanphamid=" + sanphamid +
                ", tensanpham='" + tensanpham + '\'' +
                ", anhsanpham='" + anhsanpham + '\'' +
                ", gia=" + gia +
                ", soluong=" + soluong +
                ", tongGiaTri=" + getTongGiaTri() +
                '}';
    }
}
