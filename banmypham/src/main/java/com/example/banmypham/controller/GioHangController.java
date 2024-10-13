package com.example.banmypham.controller;

import com.example.banmypham.model.GioHang;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class GioHangController {

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody GioHang cartItem, HttpSession session) {
        // Kiểm tra xem dữ liệu sản phẩm đã hợp lệ chưa
        if (cartItem == null || cartItem.getSanPhamId() <= 0 || cartItem.getSoLuong() <= 0) {
            return ResponseEntity.badRequest().body("Dữ liệu sản phẩm không hợp lệ.");
        }
        // Kiểm tra xem giỏ hàng đã tồn tại trong session hay chưa
        List<GioHang> cart = (List<GioHang>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        // Kiểm tra nếu sản phẩm đã tồn tại trong giỏ, thì tăng số lượng
        boolean found = false;
        for (GioHang item : cart) {
            if (item.getSanPhamId() == cartItem.getSanPhamId()) {
                item.setSoLuong(item.getSoLuong() + cartItem.getSoLuong());
                found = true;
                break;
            }
        }
        // Nếu không tìm thấy, thêm mới sản phẩm vào giỏ
        if (!found) {
            cart.add(cartItem);
        }
        session.setAttribute("cart", cart);
        return ResponseEntity.ok().body("Sản phẩm đã được thêm vào giỏ hàng!");
    }

}
