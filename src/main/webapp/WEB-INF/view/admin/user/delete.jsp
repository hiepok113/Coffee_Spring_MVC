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
    <meta name="description" content="Admin - Delete User" />
    <meta name="author" content="Admin" />
    <title>Delete User - Admin</title>

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
            <h1 class="mt-4 text-danger">Delete User</h1>

            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item">
                <a href="/admin">Dashboard</a>
              </li>
              <li class="breadcrumb-item">
                <a href="/admin/user">Users</a>
              </li>
              <li class="breadcrumb-item active text-danger">Delete</li>
            </ol>

            <!-- Warning Alert -->
            <div class="alert alert-danger" role="alert">
              <i class="fas fa-exclamation-triangle me-2"></i>
              <strong>Warning!</strong> This action cannot be undone.
            </div>

            <!-- User Info Card -->
            <div class="card mb-4 border-danger">
              <div class="card-header bg-danger text-white">
                <i class="fas fa-user-times me-1"></i>
                Confirm Delete User
              </div>

              <div class="card-body">
                <table class="table table-bordered w-50">
                  <tr>
                    <th style="width: 150px">ID</th>
                    <td>${user.id}</td>
                  </tr>
                  <tr>
                    <th>Email</th>
                    <td>${user.email}</td>
                  </tr>
                  <tr>
                    <th>Full Name</th>
                    <td>${user.fullName}</td>
                  </tr>
                </table>

                <!-- Delete Form -->
                <form action="/admin/user/delete" method="post">
                  <input type="hidden" name="id" value="${user.id}" />

                  <div class="d-flex justify-content-between mt-4">
                    <a href="/admin/user" class="btn btn-secondary">
                      <i class="fas fa-arrow-left"></i> Cancel
                    </a>
                      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                    <div class="d-flex justify-content-between mt-4">
                      <a href="/admin/user" class="btn btn-secondary">
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
