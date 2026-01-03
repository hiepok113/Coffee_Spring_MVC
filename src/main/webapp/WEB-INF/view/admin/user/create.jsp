<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <%@taglib
uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>User Form</title>

    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
  </head>

  <body class="container mt-5">
    <h2>User Registration</h2>

    <form:form
      action="/admin/user/create"
      method="POST"
      modelAttribute="newUser"
    >
      <!-- Email -->
      <div class="mb-3">
        <label class="form-label">Email</label>
        <form:input
          type="email"
          class="form-control"
          name="email"
          path="email"
        />
      </div>

      <!-- Password -->
      <div class="mb-3">
        <label class="form-label">Password</label>
        <form:input
          type="password"
          class="form-control"
          name="password"
          path="password"
        />
      </div>

      <!-- Full Name -->
      <div class="mb-3">
        <label class="form-label">Full Name</label>
        <form:input
          type="text"
          class="form-control"
          name="fullName"
          path="fullName"
        />
      </div>

      <!-- Address -->
      <div class="mb-3">
        <label class="form-label">Address</label>
        <form:input
          type="text"
          class="form-control"
          name="address"
          path="address"
        />
      </div>

      <!-- Phone Number -->
      <div class="mb-3">
        <label class="form-label">Phone Number</label>
        <form:input
          type="text"
          class="form-control"
          name="number"
          path="number"
        />
      </div>

      <button type="submit" class="btn btn-primary">Submit</button>
    </form:form>
  </body>
</html>
