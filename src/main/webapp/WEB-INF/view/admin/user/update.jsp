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
    <meta name="description" content="Admin - Update User" />
    <meta name="author" content="Admin" />
    <title>Update User - Admin</title>

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
            <h1 class="mt-4">Update User</h1>

            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item">
                <a href="/admin">Dashboard</a>
              </li>
              <li class="breadcrumb-item">
                <a href="/admin/user">Users</a>
              </li>
              <li class="breadcrumb-item active">Update</li>
            </ol>

            <!-- Form Card -->
            <div class="card mb-4">
              <div class="card-header">
                <i class="fas fa-user-edit me-1"></i>
                Update User Information
              </div>

              <div class="card-body">
                <form:form
                  action="/admin/user/update"
                  method="POST"
                  modelAttribute="user"
                >
                  <!-- ID (hidden) -->
                  <form:hidden path="id" />

                  <!-- Email (readonly) -->
                  <div class="mb-3">
                    <label class="form-label">Email</label>
                    <form:input
                      path="email"
                      type="email"
                      class="form-control"
                      disabled="true"
                    />
                  </div>

                  <!-- Full Name -->
                  <div class="mb-3">
                    <label class="form-label">Full Name</label>
                    <form:input
                      path="fullName"
                      type="text"
                      class="form-control"
                      placeholder="Enter full name"
                    />
                  </div>

                  <!-- Address -->
                  <div class="mb-3">
                    <label class="form-label">Address</label>
                    <form:input
                      path="address"
                      type="text"
                      class="form-control"
                      placeholder="Enter address"
                    />
                  </div>

                  <!-- Phone Number -->
                  <div class="mb-3">
                    <label class="form-label">Phone Number</label>
                    <form:input
                      path="number"
                      type="text"
                      class="form-control"
                      placeholder="Enter phone number"
                    />
                  </div>

                  <!-- Buttons -->
                  <div class="d-flex justify-content-between">
                    <a href="/admin/user" class="btn btn-secondary">
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
