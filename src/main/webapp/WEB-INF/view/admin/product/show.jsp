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
    <meta name="description" content="Admin - Product Management" />
    <meta name="author" content="Admin" />
    <title>Product Management - Admin</title>

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
            <h1 class="mt-4">Manage Products</h1>

            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item">
                <a href="/admin">Dashboard</a>
              </li>
              <li class="breadcrumb-item active">Products</li>
            </ol>

            <!-- Action -->
            <div class="mb-3">
              <a href="/admin/product/create" class="btn btn-primary">
                <i class="fas fa-plus"></i> Create Product
              </a>
            </div>

            <!-- Product Table -->
            <div class="card mb-4">
              <div class="card-header">
                <i class="fas fa-table me-1"></i>
                Product List
              </div>

              <div class="card-body">
                <div class="table-responsive">
                  <table class="table table-bordered align-middle">
                    <thead class="table-light">
                      <tr>
                        <th style="width: 80px">ID</th>
                        <th style="width: 120px">Image</th>
                        <th>Name</th>
                        <th style="width: 120px">Price</th>
                        <th style="width: 120px">Quantity</th>
                        <th style="width: 140px">Factory</th>
                        <th style="width: 140px">Target</th>
                        <th style="width: 260px">Action</th>
                      </tr>
                    </thead>

                    <tbody>
                      <c:forEach var="p" items="${products}">
                        <tr>
                          <td>${p.id}</td>

                          <td>
                            <c:choose>
                              <c:when test="${not empty p.image}">
                                <img
                                  src="/images/product/${p.image}"
                                  alt="${p.name}"
                                  class="img-thumbnail"
                                  style="height: 60px"
                                />
                              </c:when>
                              <c:otherwise>
                                <span class="text-muted">No image</span>
                              </c:otherwise>
                            </c:choose>
                          </td>

                          <td>
                            <div class="fw-semibold">${p.name}</div>
                            <div class="text-muted small">${p.shortDesc}</div>
                          </td>

                          <td>${p.price}</td>
                          <td>${p.quantity}</td>
                          <td>${p.factory}</td>
                          <td>${p.target}</td>

                          <td>
                            <!-- VIEW -->
                            <a
                              href="/admin/product/${p.id}"
                              class="btn btn-success btn-sm"
                            >
                              View
                            </a>

                            <!-- UPDATE -->
                            <a
                              href="/admin/product/update/${p.id}"
                              class="btn btn-warning btn-sm mx-1"
                            >
                              Update
                            </a>

                            <!-- DELETE -->
                            <a
                              href="/admin/product/delete/${p.id}"
                              class="btn btn-danger btn-sm"
                            >
                              Delete
                            </a>
                          </td>
                        </tr>
                      </c:forEach>

                      <c:if test="${empty products}">
                        <tr>
                          <td colspan="8" class="text-center text-muted py-4">
                            No products found.
                          </td>
                        </tr>
                      </c:if>
                    </tbody>
                  </table>
                </div>
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
