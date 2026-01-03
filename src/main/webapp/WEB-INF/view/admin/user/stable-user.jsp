<%@ page contentType="text/html; charset=UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>User Management</title>

    <!-- Bootstrap 5 -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
  </head>

  <body>
    <div class="container mt-5">
      <!-- Header -->
      <div class="d-flex justify-content-between align-items-center mb-4">
        <h3>Table users</h3>

        <a href="/admin/user/create" class="btn btn-primary"> Create a user </a>
      </div>

      <!-- User Table -->
      <table class="table table-bordered align-middle">
        <thead class="table-light">
          <tr>
            <th style="width: 80px">ID</th>
            <th>Email</th>
            <th>Full Name</th>
            <th style="width: 260px">Action</th>
          </tr>
        </thead>

        <tbody>
          <c:forEach var="user" items="${users1}">
            <tr>
              <td>${user.id}</td>
              <td>${user.email}</td>
              <td>${user.fullName}</td>
              <td>
                <a
                  href="/admin/user/view/${user.id}"
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
                  onclick="return confirm('Are you sure?');"
                >
                  Delete
                </a>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </body>
</html>
