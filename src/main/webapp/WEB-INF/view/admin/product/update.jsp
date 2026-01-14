<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib
prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content="Admin - Update Product" />
    <meta name="author" content="Admin" />
    <title>Update Product - Admin</title>

    <link href="/css/style.css" rel="stylesheet" />
    <script
      src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
      crossorigin="anonymous"
    ></script>
  </head>

  <body class="sb-nav-fixed">
    <jsp:include page="/WEB-INF/view/admin/layout/header.jsp" />

    <div id="layoutSidenav">
      <jsp:include page="/WEB-INF/view/admin/layout/sidebar.jsp" />

      <div id="layoutSidenav_content">
        <main>
          <div class="container-fluid px-4">
            <h1 class="mt-4">Update Product</h1>

            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
              <li class="breadcrumb-item">
                <a href="/admin/product">Products</a>
              </li>
              <li class="breadcrumb-item active">Update</li>
            </ol>

            <div class="card mb-4">
              <div class="card-header">
                <i class="fas fa-box-open me-1"></i>
                Update Product Information
              </div>

              <div class="card-body">
                <form:form
                  action="/admin/product/update"
                  method="POST"
                  modelAttribute="product"
                  enctype="multipart/form-data"
                >
                  <!-- ID (hidden) -->
                  <form:hidden path="id" />

                  <!-- giữ ảnh cũ nếu không upload ảnh mới -->
                  <form:hidden path="image" />

                  <div class="row g-4">
                    <!-- Name -->
                    <div class="col-md-6">
                      <label class="form-label fw-semibold">Name</label>
                      <form:input
                        path="name"
                        type="text"
                        class="form-control"
                        placeholder="Enter product name"
                      />
                    </div>

                    <!-- Price -->
                    <div class="col-md-6">
                      <label class="form-label fw-semibold">Price</label>
                      <form:input
                        path="price"
                        type="number"
                        step="0.01"
                        min="0"
                        class="form-control"
                      />
                    </div>

                    <!-- Detail Desc -->
                    <div class="col-12">
                      <label class="form-label fw-semibold"
                        >Detail description</label
                      >
                      <form:textarea
                        path="detailDesc"
                        class="form-control"
                        rows="4"
                        placeholder="Enter detail description"
                      />
                    </div>

                    <!-- Short Desc -->
                    <div class="col-md-6">
                      <label class="form-label fw-semibold"
                        >Short description</label
                      >
                      <form:input
                        path="shortDesc"
                        type="text"
                        class="form-control"
                        placeholder="Enter short description"
                      />
                    </div>

                    <!-- Quantity -->
                    <div class="col-md-6">
                      <label class="form-label fw-semibold">Quantity</label>
                      <form:input
                        path="quantity"
                        type="number"
                        min="0"
                        class="form-control"
                      />
                    </div>

                    <!-- Factory -->
                    <div class="col-md-6">
                      <label class="form-label fw-semibold">Factory</label>
                      <form:select path="factory" class="form-select">
                        <form:option value="" label="-- Select factory --" />
                        <form:option value="HOUSE_BLEND"
                          >House Blend (Quán rang)</form:option
                        >
                        <form:option value="ARABICA_DALAT"
                          >Arabica Đà Lạt</form:option
                        >
                        <form:option value="ROBUSTA_BMT"
                          >Robusta Buôn Ma Thuột</form:option
                        >
                        <form:option value="CAU_DAT">Cầu Đất Farm</form:option>
                        <form:option value="ETHIOPIA"
                          >Import - Ethiopia</form:option
                        >
                        <form:option value="COLOMBIA"
                          >Import - Colombia</form:option
                        >
                        <form:option value="BRAZIL"
                          >Import - Brazil</form:option
                        >
                      </form:select>
                    </div>

                    <!-- Target -->
                    <div class="col-md-6">
                      <label class="form-label fw-semibold">Target</label>
                      <form:select path="target" class="form-select">
                        <form:option value="" label="-- Select target --" />
                        <form:option value="ESPRESSO">Espresso</form:option>
                        <form:option value="MILK_COFFEE"
                          >Milk Coffee</form:option
                        >
                        <form:option value="TAKE_AWAY">Take Away</form:option>
                        <form:option value="OFFICE">Office</form:option>
                        <form:option value="STUDENT">Student</form:option>
                        <form:option value="PREMIUM">Premium</form:option>
                        <form:option value="SIGNATURE">Signature</form:option>
                        <form:option value="COLD_BREW">Cold Brew</form:option>
                        <form:option value="MANUAL">Manual Brew</form:option>
                        <form:option value="WORKING">Làm việc lâu</form:option>
                      </form:select>
                    </div>

                    <!-- Current Image -->
                    <div class="col-12">
                      <label class="form-label fw-semibold"
                        >Current Image</label
                      >
                      <div class="d-flex align-items-center gap-3">
                        <c:choose>
                          <c:when test="${not empty product.image}">
                            <!-- Nếu image là URL đầy đủ -> src="${product.image}" -->
                            <!-- Nếu image là tên file và bạn map /images/product/ -> src="/images/product/${product.image}" -->
                            <img
                              id="currentImage"
                              src="/images/product/${product.image}"
                              alt="${product.name}"
                              class="img-thumbnail"
                              style="
                                height: 80px;
                                width: 80px;
                                object-fit: cover;
                              "
                            />
                          </c:when>
                          <c:otherwise>
                            <span class="text-muted">No image</span>
                          </c:otherwise>
                        </c:choose>

                        <small class="text-muted">
                          Nếu bạn chọn ảnh mới bên dưới thì hệ thống sẽ thay
                          ảnh.
                        </small>
                      </div>
                    </div>

                    <!-- Upload new Image -->
                    <div class="col-12">
                      <label class="form-label fw-semibold">Change Image</label>
                      <input
                        class="form-control"
                        type="file"
                        id="imageFile"
                        name="imageFile"
                        accept=".png,.jpg,.jpeg,.webp"
                      />
                      <div class="form-text">
                        Supported: png, jpg, jpeg, webp
                      </div>
                    </div>

                    <!-- Preview -->
                    <div class="col-12">
                      <img
                        id="imagePreview"
                        class="img-thumbnail"
                        style="max-height: 220px; display: none"
                        alt="Image preview"
                      />
                    </div>
                  </div>

                  <!-- Buttons -->
                  <div class="d-flex justify-content-between mt-4">
                    <a href="/admin/product" class="btn btn-secondary">
                      <i class="fas fa-arrow-left"></i> Back
                    </a>
                    <button type="submit" class="btn btn-warning">
                      <i class="fas fa-save"></i> Update
                    </button>
                  </div>
                </form:form>
              </div>
            </div>
          </div>
        </main>

        <jsp:include page="/WEB-INF/view/admin/layout/footer.jsp" />
      </div>
    </div>

    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      crossorigin="anonymous"
    ></script>
    <script src="/js/scripts.js"></script>

    <script>
      const imageInput = document.getElementById("imageFile");
      const imagePreview = document.getElementById("imagePreview");

      if (imageInput) {
        imageInput.addEventListener("change", function () {
          const file = this.files && this.files[0];

          if (!file) {
            imagePreview.style.display = "none";
            imagePreview.src = "";
            return;
          }

          if (!file.type.startsWith("image/")) {
            alert("Please select a valid image file!");
            this.value = "";
            imagePreview.style.display = "none";
            return;
          }

          const reader = new FileReader();
          reader.onload = function (e) {
            imagePreview.src = e.target.result;
            imagePreview.style.display = "block";
          };
          reader.readAsDataURL(file);
        });
      }
    </script>
  </body>
</html>
