package com.example.banmypham.service;

import com.example.banmypham.model.DonHang;
import com.example.banmypham.repository.DonHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonHangService {

    @Autowired
    private DonHangRepository donHangRepository;

    // Lấy tất cả các đơn hàng
    public List<DonHang> findAll() {
        return donHangRepository.findAll();
    }

    // Lấy đơn hàng theo ID
    public Optional<DonHang> findById(Integer id) {
        return donHangRepository.findById(id);
    }

    // Lưu đơn hàng (tạo mới hoặc cập nhật)
    public DonHang save(DonHang donhang) {
        return donHangRepository.save(donhang);
    }

    // Xóa đơn hàng theo ID
    public void deleteById(Integer id) {
        donHangRepository.deleteById(id);
    }
}
