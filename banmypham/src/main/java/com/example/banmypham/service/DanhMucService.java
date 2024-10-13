package com.example.banmypham.service;

import com.example.banmypham.model.DanhMuc;
import com.example.banmypham.repository.DanhMucRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DanhMucService {

    @Autowired
    private DanhMucRepository danhmucRepository;

    // Retrieve all categories
    public List<DanhMuc> findAll() {
        return danhmucRepository.findAll();
    }

    // Find a category by ID
    public Optional<DanhMuc> findById(Integer id) {
        return danhmucRepository.findById(id);
    }

    // Save or create a new category
    @Transactional
    public DanhMuc save(DanhMuc danhmuc) {
        Integer maxId = danhmucRepository.findMaxDanhMucid(); // Get the current maximum ID
        if (maxId != null) {
            danhmuc.setDanhMucid(maxId + 1); // Increment the ID
        } else {
            danhmuc.setDanhMucid(1); // Start with ID 1 if no categories exist
        }
        return danhmucRepository.save(danhmuc); // Save the new category
    }

    // Delete a category by ID
    public void deleteById(Integer id) {
        // Check if the category exists before attempting to delete
        if (danhmucRepository.existsById(id)) {
            danhmucRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Category with ID " + id + " does not exist.");
        }
    }
}
