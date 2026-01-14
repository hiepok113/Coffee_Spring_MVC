<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content="Admin - Delete Product" />
    <meta name="author" content="Admin" />
    <title>Delete Product - Admin</title>

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
            <h1 class="mt-4 text-danger">Delete Product</h1>

            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item">
                <a href="/admin">Dashboard</a>
              </li>
              <li class="breadcrumb-item">
                <a href="/admin/product">Products</a>
              </li>
              <li class="breadcrumb-item active text-danger">Delete</li>
            </ol>

            <!-- Warning Alert -->
            <div class="alert alert-danger" role="alert">
              <i class="fas fa-exclamation-triangle me-2"></i>
              <strong>Warning!</strong> This action cannot be undone.
            </div>

            <!-- Product Info Card -->
            <div class="card mb-4 border-danger">
              <div class="card-header bg-danger text-white">
                <i class="fas fa-box-open me-1"></i>
                Confirm Delete Product
              </div>

              <div class="card-body">
                <div class="row g-4">
                  <!-- Image -->
                  <div class="col-md-4">
                    <div class="border rounded p-3 text-center">
                      <c:choose>
                        <c:when test="${not empty product.image}">
                          <!-- Nếu DB lưu 'product/abc.jpg' => /uploads/${product.image} -->
                          <!-- Nếu DB lưu full URL => đổi src="${product.image}" -->
                          <img
                            src="/images/product/${product.image}"
                            alt="${product.name}"
                            class="img-fluid rounded"
                            style="max-height: 220px; object-fit: cover"
                          />
                        </c:when>
                        <c:otherwise>
                          <div class="text-muted">No image</div>
                        </c:otherwise>
                      </c:choose>
                    </div>
                  </div>

                  <!-- Info -->
                  <div class="col-md-8">
                    <table class="table table-bordered">
                      <tr>
                        <th style="width: 200px">ID</th>
                        <td>${product.id}</td>
                      </tr>
                      <tr>
                        <th>Name</th>
                        <td>${product.name}</td>
                      </tr>
                      <tr>
                        <th>Price</th>
                        <td>${product.price}</td>
                      </tr>
                      <tr>
                        <th>Quantity</th>
                        <td>${product.quantity}</td>
                      </tr>
                      <tr>
                        <th>Factory</th>
                        <td>${product.factory}</td>
                      </tr>
                      <tr>
                        <th>Target</th>
                        <td>${product.target}</td>
                      </tr>
                      <tr>
                        <th>Short Desc</th>
                        <td>${product.shortDesc}</td>
                      </tr>
                    </table>
                  </div>
                </div>

                <!-- Delete Form -->
                <form
                  action="/admin/product/delete"
                  method="post"
                  modelAttribute="product"
                >
                  <input type="hidden" name="id" value="${product.id}" />

                  <div class="d-flex justify-content-between mt-4">
                    <a href="/admin/product" class="btn btn-secondary">
                      <i class="fas fa-arrow-left"></i> Cancel
                    </a>

                    <button type="submit" class="btn btn-danger">
                      <i class="fas fa-trash"></i> Confirm Delete
                    </button>
                  </div>
                </form>
              </div>
            </div>
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
  </body>
</html>
