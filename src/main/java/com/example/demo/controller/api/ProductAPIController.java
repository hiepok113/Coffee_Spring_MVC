package com.example.demo.controller.api;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.domain.Product;
import com.example.demo.domain.DTO.RestResponse;
import com.example.demo.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/products")
public class ProductAPIController {

    private final ProductService productService;

    public ProductAPIController(ProductService productService) {
        this.productService = productService;
    }

    // Lấy tất cả Sản Phẩm -> Chuẩn hóa đầu ra (Model Response)
    @GetMapping
    public ResponseEntity<RestResponse<List<Product>>> getAllProductsAPI() {
        RestResponse<List<Product>> res = new RestResponse<>();
        
        List<Product> products = this.productService.getAllProducts();
        
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Lấy dữ liệu thành công!");
        res.setData(products);

        return ResponseEntity.ok(res); 
    }

    // Lấy chi tiết 1 Sản Phẩm
    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<Product>> getProductByIdAPI(@PathVariable Long id) {
        Optional<Product> productOpt = this.productService.getProductById(id);
        RestResponse<Product> res = new RestResponse<>();
        
        if (productOpt.isPresent()) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Tìm thấy sản phẩm id: " + id);
            res.setData(productOpt.get());
            return ResponseEntity.ok(res);
        }
        
        res.setStatusCode(HttpStatus.NOT_FOUND.value());
        res.setError("Not Found");
        res.setMessage("Không tìm thấy sản phẩm có id = " + id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }
    
    // TẠO MỚI (Cần validation dữ liệu chặt chẽ từ Annotation)
    @PostMapping
    public ResponseEntity<RestResponse<Product>> createProductAPI(@RequestBody @Valid Product product) {
        // Hàm này tự ném xuống GlobalExceptionController nếu @Valid thất bại (VD: Giá bán = -10 hoặc Thiếu tên)
        
        Product newProduct = this.productService.createProduct(product);
        
        RestResponse<Product> res = new RestResponse<>();
        res.setStatusCode(HttpStatus.CREATED.value()); // Mã 201 Created (Tạo thành công)
        res.setMessage("Tạo sản phẩm mới hoàn tất.");
        res.setData(newProduct);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    // UPDATE: Chỉnh sửa thông tin sản phẩm
    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<Product>> updateProductAPI(@RequestBody @Valid Product productDetails, @PathVariable Long id) {
        Optional<Product> existingProductOpt = this.productService.getProductById(id);
        RestResponse<Product> res = new RestResponse<>();
        
        if (!existingProductOpt.isPresent()) {
            res.setStatusCode(HttpStatus.NOT_FOUND.value());
            res.setError("Không tìm thấy sản phẩm id " + id + " để thao tác Sửa.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
        
        Product existingProduct = existingProductOpt.get();
        // Cập nhật thông tin mới
        existingProduct.setName(productDetails.getName());
        existingProduct.setPrice(productDetails.getPrice());
        existingProduct.setFactory(productDetails.getFactory());
        existingProduct.setShortDesc(productDetails.getShortDesc());
        existingProduct.setDetailDesc(productDetails.getDetailDesc());
        existingProduct.setQuantity(productDetails.getQuantity());
        
        // Save
        Product updatedProduct = this.productService.createProduct(existingProduct);
        
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Cập nhật sản phẩm thành công!");
        res.setData(updatedProduct);
        
        return ResponseEntity.ok(res);
    }

    // DELETE: Xóa sản phẩm
    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<Void>> deleteProductAPI(@PathVariable Long id) {
        RestResponse<Void> res = new RestResponse<>();
        Optional<Product> existingProductOpt = this.productService.getProductById(id);
             
        if (!existingProductOpt.isPresent()) {
            res.setStatusCode(HttpStatus.BAD_REQUEST.value());
            res.setError("Xóa thất bại. Sản phẩm (id: " + id + ") không tồn tại!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }
        
        this.productService.deleteProduct(id);
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Xóa thành công sản phẩm mang code: " + id);
        
        return ResponseEntity.ok(res);
    }
}
