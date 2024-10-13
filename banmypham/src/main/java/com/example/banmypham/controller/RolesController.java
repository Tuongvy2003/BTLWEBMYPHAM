package com.example.banmypham.controller;

import com.example.banmypham.model.Roles;
import com.example.banmypham.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})
public class RolesController {

    @Autowired
    private RolesService rolesService;

    // Lấy tất cả các vai trò (roles)
    @GetMapping("/getAll")
    public List<Roles> getAllRoles() {
        return rolesService.findAll();
    }

    // Lấy vai trò theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Roles> getRolesById(@PathVariable Integer id) {
        Optional<Roles> roles = rolesService.findById(id);
        return roles.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Tạo vai trò mới
    @PostMapping("/create")
    public Roles createRoles(@RequestBody Roles roles) {
        return rolesService.save(roles);
    }

    // Cập nhật vai trò theo ID
    @PutMapping("/update/{id}")
    public ResponseEntity<Roles> updateRoles(@PathVariable Integer id, @RequestBody Roles roles) {
        if (rolesService.findById(id).isPresent()) {
            roles.setRoleid(id); // Đúng phương thức để thiết lập ID
            return ResponseEntity.ok(rolesService.save(roles));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Xóa vai trò theo ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRoles(@PathVariable Integer id) {
        if (rolesService.findById(id).isPresent()) {
            rolesService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
