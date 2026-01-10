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
    <meta name="description" content="Admin - User Management" />
    <meta name="author" content="Admin" />
    <title>User Management - Admin</title>

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
            <h1 class="mt-4">Manage Users</h1>

            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item">
                <a href="/admin">Dashboard</a>
              </li>
              <li class="breadcrumb-item active">Users</li>
            </ol>

            <!-- Action -->
            <div class="mb-3">
              <a href="/admin/user/create" class="btn btn-primary">
                <i class="fas fa-plus"></i> Create User
              </a>
            </div>

            <!-- User Table -->
            <div class="card mb-4">
              <div class="card-header">
                <i class="fas fa-table me-1"></i>
                User List
              </div>

              <div class="card-body">
                <table class="table table-bordered align-middle">
                  <thead class="table-light">
                    <tr>
                      <th style="width: 80px">ID</th>
                      <th>Email</th>
                      <th>Full Name</th>
                      <th>Role</th>
                      <th style="width: 260px">Action</th>
                    </tr>
                  </thead>

                  <tbody>
                    <c:forEach var="user" items="${users1}">
                      <tr>
                        <td>${user.id}</td>
                        <td>${user.email}</td>
                        <td>${user.fullName}</td>
                        <td>${user.role.name}</td>
                        <td>
                          <a
                            href="/admin/user/${user.id}"
                            class="btn btn-success btn-sm"
                          >
                            View
                          </a>
                          <a
                            href="/admin/user/update/${user.id}"
                            class="btn btn-warning btn-sm mx-1"
                          >
                            Update
                          </a>
                          <a
                            href="/admin/user/delete/${user.id}"
                            class="btn btn-danger btn-sm"
                          >
                            Delete
                          </a>
                        </td>
                      </tr>
                    </c:forEach>
                  </tbody>
                </table>
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
