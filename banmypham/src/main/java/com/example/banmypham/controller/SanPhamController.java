package com.example.banmypham.controller;

import com.example.banmypham.model.SanPham;
import com.example.banmypham.repository.SanPhamRepository;
import com.example.banmypham.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sanpham")
@CrossOrigin(origins = "*")
public class SanPhamController {

    @Autowired
    private SanPhamService sanphamService;

    @Autowired
    private SanPhamRepository sanphamRepository;

    // Lấy tất cả các sản phẩm
    @GetMapping("/getAll")
    public List<SanPham> getAllSanPham() {
        return sanphamService.findAll();
    }

    // Lấy sản phẩm theo ID
    @GetMapping("/{id}")
    public ResponseEntity<SanPham> getSanPhamById(@PathVariable Integer id) {
        Optional<SanPham> sanpham = sanphamService.findById(id);
        return sanpham.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Tạo sản phẩm mới
//    @PostMapping("/create")
//    public SanPham createSanPham(@RequestBody SanPham sanpham) {
//        return sanphamService.save(sanpham);
//    }
    private String saveImage(MultipartFile imageFile) throws IOException {
        String folder = "uploads/";  // Đường dẫn đến thư mục lưu ảnh
        // Lấy thời gian hiện tại và format theo kiểu yyyyMMdd_HHmmss
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        // Tạo tên file mới với thời gian hiện tại
        String fileName = timestamp + "_" + imageFile.getOriginalFilename();
        Path filePath = Paths.get(folder + fileName);
        // Tạo thư mục nếu chưa tồn tại
        Files.createDirectories(filePath.getParent());
        // Lưu file vào thư mục
        Files.write(filePath, imageFile.getBytes());
        return fileName;  // Trả về tên file để lưu vào database
    }
    ///api theem sản phẩm
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> createProduct(
            @RequestParam("tensanpham") String tensanpham,
            @RequestParam("mota") String mota,
            @RequestParam("gia") Double gia,
            @RequestParam("soluong") int soluong,
            @RequestParam("danhmucid") String danhmucid,
            @RequestParam("anhsanpham") MultipartFile imageFile
           ) {
        try {
            // Kiểm tra xem tên sản phẩm đã tồn tại hay chưa
            Optional<SanPham> existingProduct = sanphamRepository.findByTensanpham(tensanpham);
            if (existingProduct.isPresent()) {
                // Nếu sản phẩm đã tồn tại, trả về mã trạng thái 409 (Conflict)
                return new ResponseEntity<>("Tên sản phẩm đã tồn tại", HttpStatus.CONFLICT);
            }
            // Lưu file ảnh lên server
            String imagePath = saveImage(imageFile);

            // Tạo sản phẩm mới
            SanPham product = new SanPham();
            product.setTensanpham(tensanpham);
            product.setMota(mota);
            product.setGia(gia);
            product.setSoluong(soluong);
            product.setDanhmucid(Long.valueOf(danhmucid));
            product.setAnhsanpham(imagePath);
            // Kiểm tra các điều kiện đầu vào
            if (product.getTensanpham() == null || product.getTensanpham().isEmpty()) {
                // Nếu tên sản phẩm rỗng, trả về mã trạng thái 400 (Bad Request)
                return new ResponseEntity<>("Tên sản phẩm không được để trống", HttpStatus.BAD_REQUEST);
            }
            if (product.getGia() < 0) {
                // Nếu giá sản phẩm không hợp lệ, trả về mã trạng thái 400 (Bad Request)
                return new ResponseEntity<>("Giá sản phẩm không hợp lệ", HttpStatus.BAD_REQUEST);
            }

            // Lưu sản phẩm vào database
            sanphamRepository.save(product);

            // Trả về mã trạng thái 201 (Created) khi thêm sản phẩm thành công
            return new ResponseEntity<>("Thêm sản phẩm thành công: " + product.getSanphamid(), HttpStatus.CREATED);
        } catch (Exception e) {
            // Trả về mã trạng thái 500 (Internal Server Error) khi có lỗi xảy ra
            return new ResponseEntity<>("Có lỗi xảy ra khi thêm sản phẩm: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Cập nhật sản phẩm theo ID
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateSanPham(
            @PathVariable Integer id,
            @RequestParam("tensanpham") String tensanpham,
            @RequestParam("mota") String mota,
            @RequestParam("gia") Double gia,
            @RequestParam("soluong") Integer soluong,
            @RequestParam("danhmucid") String danhmucid,
            @RequestParam(value = "anhsanpham", required = false) MultipartFile anhsanpham) {
        try {
            // Check if the product exists
            Optional<SanPham> existingSanPham = sanphamService.findById(id);
            if (!existingSanPham.isPresent()) {
                return new ResponseEntity<>("Sản phẩm không tồn tại", HttpStatus.NOT_FOUND);
            }
            // Get the existing product
            SanPham product = existingSanPham.get();

            // Validate input fields
            if (tensanpham == null || tensanpham.isEmpty()) {
                return new ResponseEntity<>("Tên sản phẩm không được để trống", HttpStatus.BAD_REQUEST);
            }

            if (gia < 0) {
                return new ResponseEntity<>("Giá sản phẩm không hợp lệ", HttpStatus.BAD_REQUEST);
            }
            // Update product fields
            product.setTensanpham(tensanpham);
            product.setMota(mota);
            product.setGia(gia);
            product.setSoluong(soluong);
            product.setDanhmucid(Long.valueOf(danhmucid));

            // Handle the uploaded image if it exists
            if (anhsanpham != null && !anhsanpham.isEmpty()) {
                String imagePath = saveImage(anhsanpham); // Implement your logic to save the image
                product.setAnhsanpham(imagePath); // Update the product's image path
            }

            // Save the updated product to the database
            sanphamService.save(product);

            return new ResponseEntity<>("Cập nhật sản phẩm thành công: " + product.getSanphamid(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Có lỗi xảy ra khi cập nhật sản phẩm: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Xóa sản phẩm theo ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSanPham(@PathVariable Integer id) {
        if (sanphamService.findById(id).isPresent()) {
            sanphamService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
