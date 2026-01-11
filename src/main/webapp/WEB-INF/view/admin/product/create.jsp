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
    <meta name="description" content="Admin - Create Product" />
    <meta name="author" content="Admin" />
    <title>Create Product - Admin</title>

    <!-- Admin CSS -->
    <link href="/css/style.css" rel="stylesheet" />

    <!-- FontAwesome -->
    <script
      src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
      crossorigin="anonymous"
    ></script>
  </head>

  <body class="sb-nav-fixed">
    <!-- Header -->
    <jsp:include page="/WEB-INF/view/admin/layout/header.jsp" />

    <div id="layoutSidenav">
      <!-- Sidebar -->
      <jsp:include page="/WEB-INF/view/admin/layout/sidebar.jsp" />

      <!-- Content -->
      <div id="layoutSidenav_content">
        <main>
          <div class="container-fluid px-4">
            <h1 class="mt-4">Create a product</h1>

            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
              <li class="breadcrumb-item">
                <a href="/admin/product">Products</a>
              </li>
              <li class="breadcrumb-item active">Create</li>
            </ol>

            <!-- FORM -->
            <form:form
              action="/admin/product/create"
              method="POST"
              modelAttribute="newProduct"
              enctype="multipart/form-data"
            >
              <div class="card mb-4">
                <div class="card-body">
                  <div class="row g-4">
                    <!-- Name -->
                    <div class="col-md-6">
                      <label class="form-label fw-semibold">Name:</label>
                      <form:input
                        path="name"
                        type="text"
                        class="form-control"
                        placeholder="Enter product name"
                        required="true"
                      />
                    </div>

                    <!-- Price -->
                    <div class="col-md-6">
                      <label class="form-label fw-semibold">Price:</label>
                      <form:input
                        path="price"
                        type="number"
                        step="0.01"
                        min="0"
                        class="form-control"
                        value="0.0"
                        placeholder="0.0"
                        required="true"
                      />
                    </div>

                    <!-- Detail Description -->
                    <div class="col-12">
                      <label class="form-label fw-semibold"
                        >Detail description:</label
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
                        >Short description:</label
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
                      <label class="form-label fw-semibold">Quantity:</label>
                      <form:input
                        path="quantity"
                        type="number"
                        min="0"
                        class="form-control"
                        value="0"
                        placeholder="0"
                      />
                    </div>

                    <!-- Factory -->
                    <div class="col-md-6">
                      <label class="form-label fw-semibold">Factory:</label>
                      <form:select path="factory" class="form-select">
                        <form:option value="">-- Select factory --</form:option>
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
                      <label class="form-label fw-semibold">Target:</label>
                      <form:select path="target" class="form-select">
                        <form:option value="">-- Select target --</form:option>
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

                    <!-- Image -->
                    <div class="col-12">
                      <label class="form-label fw-semibold">Image:</label>
                      <input
                        class="form-control"
                        type="file"
                        id="imageFile"
                        name="imageFile"
                        accept=".png,.jpg,.jpeg,.webp"
                      />
                      <small class="text-muted"
                        >Supported: png, jpg, jpeg, webp</small
                      >
                    </div>

                    <!-- Image Preview -->
                    <div class="col-12">
                      <img
                        id="imagePreview"
                        class="img-thumbnail"
                        style="max-height: 220px; display: none"
                        alt="Image preview"
                      />
                    </div>
                  </div>

                  <!-- ACTION BUTTONS -->
                  <div class="d-flex justify-content-between mt-4">
                    <a href="/admin/product" class="btn btn-secondary">
                      <i class="fas fa-arrow-left"></i> Back
                    </a>

                    <button type="submit" class="btn btn-primary">
                      <i class="fas fa-save"></i> Create product
                    </button>
                  </div>
                </div>
              </div>
            </form:form>
          </div>
        </main>

        <!-- Footer -->
        <jsp:include page="/WEB-INF/view/admin/layout/footer.jsp" />
      </div>
    </div>

    <!-- Scripts -->
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      crossorigin="anonymous"
    ></script>
    <script src="/js/scripts.js"></script>

    <!-- IMAGE PREVIEW SCRIPT -->
    <script>
      const imageInput = document.getElementById("imageFile");
      const imagePreview = document.getElementById("imagePreview");

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
    </script>
  </body>
</html>
