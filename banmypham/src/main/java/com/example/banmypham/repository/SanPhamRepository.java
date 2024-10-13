package com.example.banmypham.repository;

import com.example.banmypham.model.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Integer> {

    Optional<SanPham> findByTensanpham(String name);
    // Bạn có thể thêm các phương thức truy vấn tùy chỉnh tại đây nếu cần

}
