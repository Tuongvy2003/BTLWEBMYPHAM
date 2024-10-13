package com.example.banmypham.controller;

import com.example.banmypham.model.ChiTietDonHang;
import com.example.banmypham.model.DonHang;
import com.example.banmypham.model.GioHang;
import com.example.banmypham.model.SanPham;
import com.example.banmypham.service.DonHangService;
import com.example.banmypham.service.SanPhamService; // Import the SanPhamService
import com.example.banmypham.service.ChiTietDonHangService; // Import the ChiTietDonHangService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/donhang")
@CrossOrigin(origins = "*")
public class DonHangController {

    @Autowired
    private DonHangService donHangService;

    @Autowired
    private SanPhamService sanPhamService; // Inject SanPhamService

    @Autowired
    private ChiTietDonHangService chiTietDonHangService; // Inject ChiTietDonHangService

    // Lấy tất cả đơn hàng
    @GetMapping("/getAll")
    public List<DonHang> getAllDonHang() {
        return donHangService.findAll();
    }

    // Lấy đơn hàng theo ID
    @GetMapping("/{id}")
    public ResponseEntity<DonHang> getDonHangById(@PathVariable Integer id) {
        return donHangService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Tạo đơn hàng mới từ giỏ hàng trong session
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createDonHang(@RequestParam Integer taikhoanid, @RequestBody List<GioHang> giohang) {
        Map<String, Object> response = new HashMap<>();

        if (giohang == null || giohang.isEmpty()) {
            response.put("message", "Giỏ hàng trống! Không thể tạo đơn hàng.");
            return ResponseEntity.badRequest().body(response);
        }

        // Tính tổng giá trị đơn hàng
        double tongGiaDonHang = giohang.stream()
                .mapToDouble(GioHang::getTongGiaTri)
                .sum();

        // Tạo đối tượng đơn hàng
        DonHang donhang = new DonHang();
        donhang.setNgaydathang(LocalDateTime.now());
        donhang.setTonggiadonhang(tongGiaDonHang);
        donhang.setTrangthai("Chưa duyệt");
        donhang.setTaiKhoanID(taikhoanid);

        // Lưu đơn hàng vào database
        DonHang savedDonHang = donHangService.save(donhang);

        // Thêm chi tiết đơn hàng từ giỏ hàng
        for (GioHang item : giohang) {
            ChiTietDonHang chiTiet = new ChiTietDonHang();
            chiTiet.setDonHang(savedDonHang); // Liên kết với đơn hàng vừa tạo

            // Tìm sản phẩm theo ID
            Optional<SanPham> sanphamOpt = sanPhamService.findById(item.getSanPhamId());
            if (!sanphamOpt.isPresent()) {
                response.put("message", "Sản phẩm với ID " + item.getSanPhamId() + " không tồn tại.");
                return ResponseEntity.badRequest().body(response);
            }

            SanPham sanpham = sanphamOpt.get();
            chiTiet.setSanPham(sanpham); // Liên kết với sản phẩm từ giỏ hàng
            chiTiet.setSoLuongMua(item.getSoLuong()); // Lấy số lượng từ giỏ hàng
            chiTiet.setGiaTien((double) item.getGia()); // Lấy giá tiền từ giỏ hàng

            // Lưu chi tiết đơn hàng vào database
            chiTietDonHangService.save(chiTiet);
        }

        response.put("message", "Đơn hàng và chi tiết đơn hàng đã được tạo thành công!");
        return ResponseEntity.ok(response);
    }

    // Cập nhật đơn hàng theo ID
    @PutMapping("/update/{id}")
    public ResponseEntity<DonHang> updateDonHang(@PathVariable Integer id, @RequestBody DonHang donhang) {
        if (donHangService.findById(id).isPresent()) {
            donhang.setDonhangid(id); // Thiết lập ID cho đơn hàng
            return ResponseEntity.ok(donHangService.save(donhang));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Xóa đơn hàng theo ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDonHang(@PathVariable Integer id) {
        if (donHangService.findById(id).isPresent()) {
            donHangService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
