package com.example.banmypham.controller;

import com.example.banmypham.model.Roles;
import com.example.banmypham.model.TaiKhoan;
import com.example.banmypham.service.RolesService;
import com.example.banmypham.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/taikhoan")
//@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})
@CrossOrigin(origins = "*")
public class TaiKhoanController {

    @Autowired
    private TaiKhoanService taiKhoanService;

    @Autowired
    private RolesService rolesService;

    // Lấy tất cả tài khoản
    @GetMapping("/getAll")
    public List<TaiKhoan> getAllTaiKhoan() {
        return taiKhoanService.findAll();
    }

    // Lấy tài khoản theo ID
    @GetMapping("/{id}")
    public ResponseEntity<TaiKhoan> getTaiKhoanById(@PathVariable Integer id) {
        Optional<TaiKhoan> taikhoan = taiKhoanService.findById(id);
        return taikhoan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Tạo tài khoản mới với vai trò mặc định là User
    @PostMapping("/create")
    public ResponseEntity<?> createTaiKhoan(@RequestBody TaiKhoan taiKhoan) {
        // Kiểm tra nếu username đã tồn tại
        if (taiKhoanService.findByUsername(taiKhoan.getUsername()).isPresent()) {
            return ResponseEntity.status(401).body("Tài khoản đã tồn tại"); // Trả về HTTP 401
        }

        // Kiểm tra thông tin không để trống
        if (taiKhoan.getUsername() == null || taiKhoan.getUsername().isEmpty() ||
                taiKhoan.getPassword() == null || taiKhoan.getPassword().isEmpty() ||
                taiKhoan.getEmail() == null || taiKhoan.getEmail().isEmpty() ||
                taiKhoan.getSdt() == null || taiKhoan.getSdt().isEmpty() ||
                taiKhoan.getDiaChi() == null || taiKhoan.getDiaChi().isEmpty()) {
            return ResponseEntity.status(401).body("Thông tin không được để trống"); // Trả về HTTP 401
        }

        // Lấy vai trò mặc định từ bảng Roles
        Roles defaultRole = rolesService.findById(2)
                .orElseThrow(() -> new RuntimeException("Vai trò không tồn tại"));
        taiKhoan.setRole(defaultRole); // Thiết lập vai trò mặc định

        try {
            TaiKhoan savedTaiKhoan = taiKhoanService.save(taiKhoan);
            return ResponseEntity.ok(savedTaiKhoan);
        } catch (RuntimeException e) {
            // Ghi log chi tiết lỗi
            System.err.println("Lỗi: " + e.getMessage());
            return ResponseEntity.status(401).body("Thông tin không hợp lệ"); // Trả về HTTP 401
        } catch (Exception e) {
            // Ghi log lỗi tổng quát
            System.err.println("Lỗi hệ thống: " + e.getMessage());
            return ResponseEntity.status(500).body("Có lỗi xảy ra"); // Trả về HTTP 500
        }
    }

    // Cập nhật tài khoản theo ID
    @PutMapping("/update/{id}")
    public ResponseEntity<TaiKhoan> updateTaiKhoan(@PathVariable Integer id, @RequestBody TaiKhoan taikhoan) {
        if (taiKhoanService.findById(id).isPresent()) {
            taikhoan.setTaiKhoanID(id);
            return ResponseEntity.ok(taiKhoanService.save(taikhoan));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Xóa tài khoản theo ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTaiKhoan(@PathVariable Integer id) {
        if (taiKhoanService.findById(id).isPresent()) {
            taiKhoanService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // Cập nhật vai trò theo ID
    @PutMapping("/{id}/role")
    public ResponseEntity<String> updateRole(@PathVariable Integer id, @RequestBody Integer roleid) {
        Optional<TaiKhoan> taiKhoanOptional = taiKhoanService.findById(id);
        if (taiKhoanOptional.isPresent()) {
            TaiKhoan taiKhoan = taiKhoanOptional.get();

            // Lấy vai trò từ bảng Roles
            Roles newRole = rolesService.findById(roleid)
                    .orElseThrow(() -> new RuntimeException("Vai trò không tồn tại"));

            taiKhoan.setRole(newRole); // Thiết lập vai trò mới
            taiKhoanService.save(taiKhoan); // Lưu tài khoản với vai trò mới

            return ResponseEntity.ok("Cập nhật vai trò thành công");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // API Đăng nhập
    @PostMapping("/login")
    public ResponseEntity<TaiKhoan> login(@RequestParam String username, @RequestParam String password) {
        Optional<TaiKhoan> taiKhoanOptional = taiKhoanService.findByUsername(username);

        if (taiKhoanOptional.isPresent()) {
            TaiKhoan taiKhoan = taiKhoanOptional.get();
            if (taiKhoan.getPassword().equals(password)) {
                return ResponseEntity.ok(taiKhoan); // Trả về thông tin tài khoản
            } else {
                return ResponseEntity.status(401).body(null); // Sai mật khẩu
            }
        } else {
            return ResponseEntity.status(404).body(null); // Tài khoản không tồn tại
        }
    }
}
