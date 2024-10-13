package com.example.banmypham.service;

import com.example.banmypham.model.ChiTietDonHang;
import com.example.banmypham.repository.ChiTietDonHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChiTietDonHangService {

    @Autowired
    private ChiTietDonHangRepository chiTietDonHangRepository;

    // Lấy tất cả các chi tiết đơn hàng
    public List<ChiTietDonHang> findAll() {
        return chiTietDonHangRepository.findAll();
    }

    // Lấy chi tiết đơn hàng theo ID
    public Optional<ChiTietDonHang> findById(Integer id) {
        return chiTietDonHangRepository.findById(id);
    }

    // Lưu chi tiết đơn hàng mới
    public ChiTietDonHang save(ChiTietDonHang chiTietDonHang) {
        return chiTietDonHangRepository.save(chiTietDonHang);
    }

    // Xóa chi tiết đơn hàng theo ID
    public void deleteById(Integer id) {
        chiTietDonHangRepository.deleteById(id);
    }

    // Xóa chi tiết đơn hàng theo ID của đơn hàng
}
