package com.example.banmypham.service;

import com.example.banmypham.model.Roles;
import com.example.banmypham.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolesService {

    @Autowired
    private RolesRepository rolesRepository;

    // Lấy tất cả các vai trò
    public List<Roles> findAll() {
        return rolesRepository.findAll();
    }

    // Tìm vai trò theo ID
    public Optional<Roles> findById(Integer id) {
        return rolesRepository.findById(id);
    }

    // Tạo mới hoặc cập nhật vai trò
    public Roles save(Roles roles) {
        return rolesRepository.save(roles);
    }

    // Xóa vai trò theo ID
    public void deleteById(Integer id) {
        if (rolesRepository.existsById(id)) {
            rolesRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Vai trò với ID " + id + " không tồn tại.");
        }
    }
}
