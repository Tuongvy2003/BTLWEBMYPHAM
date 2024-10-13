package com.example.banmypham.repository;

import com.example.banmypham.model.DonHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonHangRepository extends JpaRepository<DonHang, Integer> {
    // Bạn có thể thêm các phương thức tùy chỉnh tại đây nếu cần
}
