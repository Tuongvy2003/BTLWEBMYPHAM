package com.example.banmypham.controller;

import com.example.banmypham.model.ChiTietDonHang;
import com.example.banmypham.service.ChiTietDonHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chitietdonhang")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})
public class ChiTietDonHangController {

    @Autowired
    private ChiTietDonHangService chiTietDonHangService;

    // Lấy tất cả các chi tiết đơn hàng
    @GetMapping("/getAll")
    public List<ChiTietDonHang> getAllChiTietDonHang() {
        return chiTietDonHangService.findAll();
    }

    // Lấy chi tiết đơn hàng theo ID
    @GetMapping("{id}")
    public ResponseEntity<ChiTietDonHang> getChiTietDonHangById(@PathVariable Integer id) {
        Optional<ChiTietDonHang> chitietdonhang = chiTietDonHangService.findById(id);
        return chitietdonhang.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Tạo chi tiết đơn hàng mới
    @PostMapping("/create")
    public ChiTietDonHang createChiTietDonHang(@RequestBody ChiTietDonHang chitietdonhang) {
        return chiTietDonHangService.save(chitietdonhang);
    }

    // Cập nhật chi tiết đơn hàng theo ID
    @PutMapping("/update/{id}")
    public ResponseEntity<ChiTietDonHang> updateChiTietDonHang(@PathVariable Integer id, @RequestBody ChiTietDonHang chitietdonhang) {
        if (chiTietDonHangService.findById(id).isPresent()) {
            chitietdonhang.setCtDonHangId(id); // Thiết lập ID cho chi tiết đơn hàng
            return ResponseEntity.ok(chiTietDonHangService.save(chitietdonhang));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Xóa chi tiết đơn hàng theo ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteChiTietDonHang(@PathVariable Integer id) {
        if (chiTietDonHangService.findById(id).isPresent()) {
            chiTietDonHangService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
