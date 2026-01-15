package com.example.demo.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.UploadService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;



@Controller
public class ProductController {
    private final ProductService productService;
    private final UploadService uploadService;

    public ProductController(ProductService productService, UploadService uploadService) {
        this.productService = productService;
        this.uploadService = uploadService;
    }

    @GetMapping("/admin/product")
    public String getProductPage(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "admin/product/show";
    }
    @GetMapping("/admin/product/create")
    public String getCreateProductPage(Model model ) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }
    @PostMapping("/admin/product/create")
    public String postCreateProduct(@ModelAttribute("newProduct") @Valid Product newProduct, BindingResult bindingResult , @RequestParam("imageFile") MultipartFile image) {
        if(bindingResult.hasErrors()) {
            return "admin/product/create";
        }
        String imageUrl = this.uploadService.handleUploadFile(image , "product");
        newProduct.setImage(imageUrl);
        productService.createProduct(newProduct);
        return "redirect:/admin/product";
    }
    @GetMapping("/admin/product/{id}")
    public String getProductDetailPage(@PathVariable("id") long id, Model model) {
        Product product = productService.getProductById(id).orElse(null);
        model.addAttribute("product", product);
        model.addAttribute("id" , id);
        return "admin/product/detail";
    }
    @GetMapping("/admin/product/delete/{id}")
    public String getDeleteProductPage(@PathVariable("id") long id, Model model) {
        Product product = productService.getProductById(id).orElse(null);
        model.addAttribute("product", product);
        model.addAttribute("id" , id);
        return "admin/product/delete";
    }
    @PostMapping("/admin/product/delete")
    public String postDeleteProduct(@RequestParam("id") long id) {
    productService.deleteProduct(id);
    return "redirect:/admin/product";
}
    @GetMapping("/admin/product/update/{id}")
    public String getUpdateProductPage(@PathVariable("id") long id, Model model) {
        Product product = productService.getProductById(id).orElse(null);
        model.addAttribute("product", product);
        model.addAttribute("id" , id);
        return "admin/product/update";
    }
    @PostMapping("/admin/product/update")
    public String handleUpdateProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, @RequestParam("imageFile") MultipartFile image) {
        if (bindingResult.hasErrors()) {
            return "admin/product/update";
        }
        Product currentProduct = this.productService.getProductById(product.getId()).orElse(null);
        if(currentProduct != null) {
            currentProduct.setName(product.getName());
            currentProduct.setPrice(product.getPrice());
            currentProduct.setShortDesc(product.getShortDesc());
            currentProduct.setDetailDesc(product.getDetailDesc());
            currentProduct.setQuantity(product.getQuantity());
            if(!image.isEmpty()) {
                String imageUrl = this.uploadService.handleUploadFile(image , "product");
                currentProduct.setImage(imageUrl);
            }
            this.productService.createProduct(currentProduct);
        }
        return "redirect:/admin/product";
    }

   

}
