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
    <meta name="description" content="Admin - User Detail" />
    <meta name="author" content="Admin" />
    <title>User Detail - Admin</title>

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
            <h1 class="mt-4">User Detail</h1>

            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item">
                <a href="/admin">Dashboard</a>
              </li>
              <li class="breadcrumb-item">
                <a href="/admin/user">Users</a>
              </li>
              <li class="breadcrumb-item active">Detail</li>
            </ol>

            <!-- User Info Card -->
            <div class="card mb-4">
              <div class="card-header">
                <i class="fas fa-user me-1"></i>
                User Information
              </div>

              <div class="card-body">
                <table class="table table-bordered w-50">
                  <tr>
                    <th style="width: 200px">ID</th>
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

                  <tr>
                    <th>Role</th>
                    <td>${user.role.name}</td>
                  </tr>
                </table>

                <!-- Action Buttons -->
                <div class="d-flex justify-content-between mt-4">
                  <a href="/admin/user" class="btn btn-secondary">
                    <i class="fas fa-arrow-left"></i> Back to list
                  </a>

                  <div>
                    <a
                      href="/admin/user/update/${user.id}"
                      class="btn btn-warning me-2"
                    >
                      <i class="fas fa-edit"></i> Update
                    </a>

                    <a
                      href="/admin/user/delete/${user.id}"
                      class="btn btn-danger"
                    >
                      <i class="fas fa-trash"></i> Delete
                    </a>
                  </div>
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
