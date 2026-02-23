package com.example.demo.domain.DTO;

import java.util.List;

public class ResCartDTO {
    private long id;
    private int sum;
    private List<ResCartDetailDTO> items;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public List<ResCartDetailDTO> getItems() {
        return items;
    }

    public void setItems(List<ResCartDetailDTO> items) {
        this.items = items;
    }

    // Class nội bộ cho chi tiết món hàng
    public static class ResCartDetailDTO {
        private long id;
        private String productName;
        private double price;
        private long quantity;
        private String image;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public long getQuantity() {
            return quantity;
        }

        public void setQuantity(long quantity) {
            this.quantity = quantity;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
