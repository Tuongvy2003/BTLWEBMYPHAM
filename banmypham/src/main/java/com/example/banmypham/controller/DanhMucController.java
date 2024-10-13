package com.example.banmypham.controller;

import com.example.banmypham.model.DanhMuc;
import com.example.banmypham.service.DanhMucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/danhmuc")
@CrossOrigin(origins = "*")
public class DanhMucController {
    @Autowired
    private DanhMucService danhmucService;

    // Lấy tất cả các danh mục
    @GetMapping("/getAll")
    public List<DanhMuc> getAllDanhMuc() {
        return danhmucService.findAll();
    }

    // Lấy danh mục theo ID
    @GetMapping("/{id}")
    public ResponseEntity<DanhMuc> getDanhMucById(@PathVariable Integer id) {
        Optional<DanhMuc> danhmuc = danhmucService.findById(id);
        return danhmuc.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public DanhMuc createCategory(@RequestBody DanhMuc danhmuc) {
        return danhmucService.save(danhmuc);
    }

    // Cập nhật danh mục theo ID http://localhost:8079/uploads/20241005_234518_anhcuatui.jpg
    @PutMapping("/update/{id}")
    public ResponseEntity<DanhMuc> updateDanhMuc(@PathVariable Integer id, @RequestBody DanhMuc danhmuc) {
        if (danhmucService.findById(id).isPresent()) {
            danhmuc.setDanhMucid(id); // Đúng phương thức để thiết lập ID
            return ResponseEntity.ok(danhmucService.save(danhmuc));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Xóa danh mục theo ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDanhMuc(@PathVariable Integer id) {
        if (danhmucService.findById(id).isPresent()) {
            danhmucService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
