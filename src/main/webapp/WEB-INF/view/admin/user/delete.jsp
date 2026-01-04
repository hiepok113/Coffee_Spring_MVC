<%@ page contentType="text/html; charset=UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>Delete User</title>

    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
  </head>

  <body class="container mt-5">
    <h3 class="text-danger mb-4">Delete User</h3>

    <!-- ALERT DANGER -->
    <div class="alert alert-danger" role="alert">
      <strong>Warning!</strong>
      This action cannot be undone.
    </div>

    <!-- USER INFO -->
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

    <!-- DELETE FORM -->
    <form action="/admin/user/delete" method="post">
      <input type="hidden" name="id" value="${user.id}" />

      <button type="submit" class="btn btn-danger">Confirm Delete</button>

      <a href="/admin/user" class="btn btn-secondary ms-2"> Cancel </a>
    </form>
  </body>
</html>
