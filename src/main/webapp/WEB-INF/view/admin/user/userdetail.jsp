<%@ page contentType="text/html; charset=UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>User Detail</title>

    <!-- Bootstrap 5 -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
  </head>

  <body>
    <div class="container mt-5">
      <h3 class="mb-4">User Detail</h3>

      <table class="table table-bordered">
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
      </table>

      <div class="mt-4">
        <a href="/admin/user" class="btn btn-secondary"> Back to list </a>

        <a href="/admin/user/update/${user.id}" class="btn btn-warning mx-1">
          Update
        </a>

        <a
          href="/admin/user/delete/${user.id}"
          class="btn btn-danger"
          onclick="return confirm('Are you sure?');"
        >
          Delete
        </a>
      </div>
    </div>
  </body>
</html>
