package com.example.bai2_letranbaokha.Service;

import com.example.bai2_letranbaokha.Model.Book;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@Service
public class BookService {
    private List<Book> books = new ArrayList<>();

    // Hàm khởi tạo để tạo sẵn 5 cuốn sách mẫu
    public BookService() {
        books.add(new Book(1, "Lập trình Java Cơ Bản", "Nguyễn Văn A"));
        books.add(new Book(2, "Spring Boot Cho Người Mới", "Trần Thị B"));
        books.add(new Book(3, "Cấu trúc dữ liệu & Giải thuật", "Lê Văn C"));
        books.add(new Book(4, "Thiết kế Web với J2EE", "Phạm Minh D"));
        books.add(new Book(5, "Tối ưu hóa mã nguồn", "Hoàng Gia E"));
    }

    // Lấy toàn bộ danh sách sách
    public List<Book> getAllBooks() {
        return books;
    }

    // Tìm sách theo ID
    public Book getBookById(int id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Thêm mới một cuốn sách
    public void addBook(Book book) {
        books.add(book);
    }

    // Cập nhật thông tin sách đã tồn tại
    public void updateBook(int id, Book updatedBook) {
        books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .ifPresent(book -> {
                    book.setTitle(updatedBook.getTitle());
                    book.setAuthor(updatedBook.getAuthor());
                }); // Dấu đóng ngoặc phải nằm ở đây
    }

    // Xóa sách khỏi danh sách theo ID
    public void deleteBook(int id) {
        books.removeIf(book -> book.getId() == id);
    }
}