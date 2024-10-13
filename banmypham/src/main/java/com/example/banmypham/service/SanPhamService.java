package com.example.banmypham.service;

import com.example.banmypham.model.SanPham;
import com.example.banmypham.repository.SanPhamRepository;
import com.example.banmypham.repository.DanhMucRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SanPhamService {

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Autowired
    private DanhMucRepository danhMucRepository;

    // Retrieve all products
    public List<SanPham> findAll() {
        return sanPhamRepository.findAll();
    }

    // Find a product by ID
    public Optional<SanPham> findById(Integer id) {
        return sanPhamRepository.findById(id);
    }

    // Save or create a new product
    @Transactional
    public SanPham save(SanPham sanPham) {
        if (sanPham.getDanhmucid() != null && sanPham.getDanhmucid() != null) {
            if (!danhMucRepository.existsById(Math.toIntExact(sanPham.getDanhmucid()))) {
                throw new IllegalArgumentException("Danh mục không tồn tại.");
            }
        }
        return sanPhamRepository.save(sanPham); // Save the new product
    }

    // Delete a product by ID
    public void deleteById(Integer id) {
        // Check if the product exists before attempting to delete
        if (sanPhamRepository.existsById(id)) {
            sanPhamRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Sản phẩm với ID " + id + " không tồn tại.");
        }
    }
}
