package com.example.bai2_letranbaokha.Service;

import com.example.bai2_letranbaokha.Model.Category;
import com.example.bai2_letranbaokha.Model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private List<Product> listProduct = new ArrayList<>();

    // Constructor để tạo dữ liệu mẫu khi Service được khởi tạo


    public List<Product> getAll() {
        return listProduct;
    }

    public Product get(int id) {
        return listProduct.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void add(Product newProduct) {
        // Tìm ID lớn nhất hiện tại và cộng thêm 1
        int maxId = listProduct.stream()
                .mapToInt(Product::getId)
                .max()
                .orElse(0);
        newProduct.setId(maxId + 1);
        listProduct.add(newProduct);
    }

    public void update(Product editProduct) {
        Product find = get(editProduct.getId());
        if (find != null) {
            find.setPrice(editProduct.getPrice());
            find.setName(editProduct.getName());
            // Chỉ cập nhật tên file ảnh nếu có file mới được upload
            if (editProduct.getImage() != null && !editProduct.getImage().isEmpty()) {
                find.setImage(editProduct.getImage());
            }
            // Cập nhật luôn category nếu cần thiết
            if (editProduct.getCategory() != null) {
                find.setCategory(editProduct.getCategory());
            }
        }
    }

    public void updateImage(Product newProduct, MultipartFile imageProduct) {
        if (imageProduct != null && !imageProduct.isEmpty()) {
            try {
                // 1. Đường dẫn vật lý để hệ thống tìm chỗ lưu file
                Path dirImages = Paths.get("src/main/resources/static/images");
                if (!Files.exists(dirImages)) Files.createDirectories(dirImages);

                // 2. Tạo tên file duy nhất
                String newFileName = UUID.randomUUID().toString() + "_" + imageProduct.getOriginalFilename();
                Path pathFileUpload = dirImages.resolve(newFileName);

                // 3. Thực hiện copy file vào ổ cứng
                Files.copy(imageProduct.getInputStream(), pathFileUpload, StandardCopyOption.REPLACE_EXISTING);

                // 4. CHỈ LƯU TÊN FILE vào đối tượng Product (Không lưu 'src/main/...')
                newProduct.setImage(newFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // Thêm vào trong class ProductService
    public void delete(int id) {
        // Tìm và xóa sản phẩm có ID trùng khớp
        listProduct.removeIf(p -> p.getId() == id);
    }
}