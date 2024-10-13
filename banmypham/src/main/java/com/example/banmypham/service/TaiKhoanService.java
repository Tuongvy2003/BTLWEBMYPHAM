package com.example.banmypham.service;

import com.example.banmypham.model.TaiKhoan;
import com.example.banmypham.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaiKhoanService {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    // Lấy tất cả tài khoản
    public List<TaiKhoan> findAll() {
        return taiKhoanRepository.findAll();
    }

    // Lấy tài khoản theo ID
    public Optional<TaiKhoan> findById(Integer id) {
        return taiKhoanRepository.findById(id);
    }

    // Lưu hoặc cập nhật tài khoản
    public TaiKhoan save(TaiKhoan taikhoan) {
        return taiKhoanRepository.save(taikhoan);
    }

    // Xóa tài khoản theo ID
    public void deleteById(Integer id) {
        taiKhoanRepository.deleteById(id);
    }

    // Tìm tài khoản theo tên đăng nhập
    public Optional<TaiKhoan> findByUsername(String username) {
        return taiKhoanRepository.findByUsername(username); // Giả sử bạn đã định nghĩa phương thức này trong repository
    }
}
