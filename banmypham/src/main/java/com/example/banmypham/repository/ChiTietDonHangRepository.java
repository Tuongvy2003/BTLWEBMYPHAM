package com.example.banmypham.repository;

import com.example.banmypham.model.ChiTietDonHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChiTietDonHangRepository extends JpaRepository<ChiTietDonHang, Integer> {
    // Bạn có thể thêm các phương thức tùy chỉnh nếu cần
}
