<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib
uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>Update User</title>

    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
  </head>

  <body class="container mt-5">
    <h2>Update User</h2>

    <form:form action="/admin/user/update" method="POST" modelAttribute="user">
      <!-- ID (hidden) -->
      <form:hidden path="id" />

      <!-- ID (readonly display) -->
      <div class="mb-3" style="display: none">
        <label class="form-label">User ID</label>
        <input
          type="text"
          class="form-control"
          value="${user.id}"
          readonly
          disabled="true"
        />
      </div>

      <!-- Email -->
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
        <form:input path="fullName" type="text" class="form-control" />
      </div>

      <!-- Address -->
      <div class="mb-3">
        <label class="form-label">Address</label>
        <form:input path="address" type="text" class="form-control" />
      </div>

      <!-- Phone Number -->
      <div class="mb-3">
        <label class="form-label">Phone Number</label>
        <form:input path="number" type="text" class="form-control" />
      </div>

      <div class="mt-4">
        <button type="submit" class="btn btn-warning">Update</button>

        <a href="/admin/user" class="btn btn-secondary ms-2"> Cancel </a>
      </div>
    </form:form>
  </body>
</html>
