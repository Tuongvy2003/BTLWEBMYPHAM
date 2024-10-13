package com.example.banmypham.repository;

import com.example.banmypham.model.DanhMuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DanhMucRepository extends JpaRepository<DanhMuc, Integer> {

    // Custom query to find the maximum DanhMucID
    @Query("SELECT MAX(d.danhmucid) FROM DanhMuc d")
    Integer findMaxDanhMucid();
}
