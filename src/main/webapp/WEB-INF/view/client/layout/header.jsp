<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Navbar start -->
<div class="container-fluid fixed-top">
  <div class="container px-0">
    <nav class="navbar navbar-light bg-white navbar-expand-xl">
      <a href="/" class="navbar-brand">
        <h1 class="text-primary display-6">Coffee Shop Turtle</h1>
      </a>

      <button
        class="navbar-toggler py-2 px-3"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarCollapse"
        aria-controls="navbarCollapse"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="fa fa-bars text-primary"></span>
      </button>

      <div class="collapse navbar-collapse bg-white" id="navbarCollapse">
        <!-- Menu -->
        <div class="navbar-nav mx-auto">
          <a href="/" class="nav-item nav-link active">Trang chủ</a>
          <a href="/shop" class="nav-item nav-link">Sản phẩm</a>
        </div>

        <!-- Icons -->
        <div class="d-flex m-3 me-0 align-items-center">
          <!-- Search -->
          <button
            class="btn-search btn border border-secondary btn-md-square rounded-circle bg-white me-4"
            type="button"
            data-bs-toggle="modal"
            data-bs-target="#searchModal"
          >
            <i class="fas fa-search text-primary"></i>
          </button>

          <!-- Cart -->
          <a href="/cart" class="position-relative me-4 my-auto">
            <i class="fa fa-shopping-bag fa-2x"></i>
            <span
              class="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1"
              style="top: -5px; left: 15px; height: 20px; min-width: 20px"
            >
              3
            </span>
          </a>

          <!-- ================= CHƯA ĐĂNG NHẬP ================= -->
          <c:if test="${pageContext.request.userPrincipal == null}">
            <a href="/login" class="my-auto" title="Đăng nhập">
              <i class="fas fa-user fa-2x"></i>
            </a>
          </c:if>

          <!-- ================= ĐÃ ĐĂNG NHẬP ================= -->
          <c:if test="${pageContext.request.userPrincipal != null}">
            <div class="dropdown my-auto">
              <!-- Bootstrap 5 dropdown -->
              <button
                class="btn p-0 border-0 bg-transparent"
                type="button"
                id="clientUserDropdown"
                data-bs-toggle="dropdown"
                aria-expanded="false"
                title="Tài khoản"
              >
                <i class="fas fa-user fa-2x"></i>
              </button>

              <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="clientUserDropdown">
                <li class="dropdown-item-text">
                  Xin chào, <strong>${pageContext.request.userPrincipal.name}</strong>
                </li>

                <li><hr class="dropdown-divider" /></li>

                <li>
                  <form action="/login" method="post" class="px-3">
                    <!-- CSRF (Spring Security) -->
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <button type="submit" class="btn btn-danger w-100">
                      <i class="fas fa-sign-out-alt me-1"></i> Đăng xuất
                    </button>
                  </form>
                </li>
              </ul>
            </div>
          </c:if>
        </div>
      </div>
    </nav>
  </div>
</div>
<!-- Navbar End -->
