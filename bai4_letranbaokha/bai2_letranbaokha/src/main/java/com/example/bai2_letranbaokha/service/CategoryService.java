package com.example.bai2_letranbaokha.Service;

import com.example.bai2_letranbaokha.Model.Category;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    // Lưu trữ danh sách danh mục tạm thời trong bộ nhớ
    private List<Category> listCategory = new ArrayList<>();

    // Khởi tạo một số dữ liệu mẫu (Constructor)
    public CategoryService() {
        listCategory.add(new Category(1, "Điện thoại"));
        listCategory.add(new Category(2, "Máy tính bảng"));
        listCategory.add(new Category(3, "Laptop"));
        listCategory.add(new Category(4, "Phụ kiện"));
    }

    // Lấy toàn bộ danh sách danh mục để hiển thị lên Form
    public List<Category> getAll() {
        return listCategory;
    }

    // Tìm kiếm danh mục theo ID để gán vào sản phẩm
    public Category get(int id) {
        return listCategory.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Thêm danh mục mới (nếu cần)
    public void add(Category category) {
        listCategory.add(category);
    }
}