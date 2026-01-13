<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib
prefix="form" uri="http://www.springframework.org/tags/form"%> <%@ taglib
prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content="Admin - Create User" />
    <meta name="author" content="Admin" />
    <title>Create User - Admin</title>

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
            <h1 class="mt-4">Create User</h1>

            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
              <li class="breadcrumb-item"><a href="/admin/user">Users</a></li>
              <li class="breadcrumb-item active">Create</li>
            </ol>

            <!-- FORM -->
            <form:form
              action="/admin/user/create"
              method="POST"
              modelAttribute="newUser"
              enctype="multipart/form-data"
            >
              <div class="row">
                <!-- LEFT COLUMN -->
                <div class="col-md-6">
                  <div class="card mb-4">
                    <div class="card-header">
                      <i class="fas fa-user-shield me-1"></i>
                      Account Information
                    </div>

                    <div class="card-body">
                      <!-- Email -->
                      <spring:bind path="email">
                        <div class="mb-3">
                          <label class="form-label">Email</label>
                          <form:input
                            path="email"
                            type="email"
                            cssClass="form-control ${status.error ? 'is-invalid' : ''}"
                            placeholder="Enter email"
                          />
                          <form:errors
                            path="email"
                            cssClass="invalid-feedback d-block"
                          />
                        </div>
                      </spring:bind>

                      <!-- Password -->
                      <spring:bind path="password">
                        <div class="mb-3">
                          <label class="form-label">Password</label>
                          <form:password
                            path="password"
                            cssClass="form-control ${status.error ? 'is-invalid' : ''}"
                            placeholder="Enter password"
                          />
                          <form:errors
                            path="password"
                            cssClass="invalid-feedback d-block"
                          />
                        </div>
                      </spring:bind>

                      <!-- Role -->
                      <spring:bind path="role">
                        <div class="mb-3">
                          <label class="form-label">Role</label>

                          <form:select
                            path="role"
                            cssClass="form-select ${status.error ? 'is-invalid' : ''}"
                          >
                            <form:option value="" label="-- Select role --" />
                            <!-- roles: list<Role> từ controller -->
                            <form:options
                              items="${roles}"
                              itemValue="id"
                              itemLabel="name"
                            />
                          </form:select>

                          <form:errors
                            path="role"
                            cssClass="invalid-feedback d-block"
                          />
                        </div>
                      </spring:bind>
                    </div>
                  </div>
                </div>

                <!-- RIGHT COLUMN -->
                <div class="col-md-6">
                  <div class="card mb-4">
                    <div class="card-header">
                      <i class="fas fa-id-card me-1"></i>
                      Personal Information
                    </div>

                    <div class="card-body">
                      <!-- Full Name -->
                      <spring:bind path="fullName">
                        <div class="mb-3">
                          <label class="form-label">Full Name</label>
                          <form:input
                            path="fullName"
                            type="text"
                            cssClass="form-control ${status.error ? 'is-invalid' : ''}"
                            placeholder="Enter full name"
                          />
                          <form:errors
                            path="fullName"
                            cssClass="invalid-feedback d-block"
                          />
                        </div>
                      </spring:bind>

                      <!-- Address -->
                      <spring:bind path="address">
                        <div class="mb-3">
                          <label class="form-label">Address</label>
                          <form:input
                            path="address"
                            type="text"
                            cssClass="form-control ${status.error ? 'is-invalid' : ''}"
                            placeholder="Enter address"
                          />
                          <form:errors
                            path="address"
                            cssClass="invalid-feedback d-block"
                          />
                        </div>
                      </spring:bind>

                      <!-- Phone Number -->
                      <spring:bind path="number">
                        <div class="mb-3">
                          <label class="form-label">Phone Number</label>
                          <form:input
                            path="number"
                            type="text"
                            cssClass="form-control ${status.error ? 'is-invalid' : ''}"
                            placeholder="Enter phone number"
                          />
                          <form:errors
                            path="number"
                            cssClass="invalid-feedback d-block"
                          />
                        </div>
                      </spring:bind>

                      <!-- Avatar Upload -->
                      <div class="mb-3">
                        <label for="avatarFile" class="form-label"
                          >Avatar</label
                        >
                        <input
                          class="form-control"
                          type="file"
                          id="avatarFile"
                          name="avatarFile"
                          accept=".png, .jpg, .jpeg"
                        />
                        <div class="form-text">Allowed: .png, .jpg, .jpeg</div>
                      </div>

                      <!-- Avatar Preview -->
                      <div class="mb-3">
                        <img
                          id="avatarPreview"
                          class="img-thumbnail"
                          style="max-height: 250px; display: none"
                          alt="Avatar preview"
                        />
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <!-- ACTION BUTTONS -->
              <div class="d-flex justify-content-between">
                <a href="/admin/user" class="btn btn-secondary">
                  <i class="fas fa-arrow-left"></i> Back
                </a>

                <button type="submit" class="btn btn-primary">
                  <i class="fas fa-save"></i> Create User
                </button>
              </div>
            </form:form>
          </div>
        </main>

        <!-- Footer -->
        <jsp:include page="/WEB-INF/view/admin/layout/footer.jsp" />
      </div>
    </div>

    <!-- Bootstrap -->
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      crossorigin="anonymous"
    ></script>
    <script src="/js/scripts.js"></script>

    <!-- AVATAR PREVIEW SCRIPT -->
    <script>
      const avatarInput = document.getElementById("avatarFile");
      const avatarPreview = document.getElementById("avatarPreview");

      avatarInput.addEventListener("change", function () {
        const file = this.files[0];

        if (!file) {
          avatarPreview.style.display = "none";
          avatarPreview.src = "";
          return;
        }

        if (!file.type.startsWith("image/")) {
          alert("Please select a valid image file!");
          this.value = "";
          avatarPreview.style.display = "none";
          return;
        }

        const reader = new FileReader();
        reader.onload = function (e) {
          avatarPreview.src = e.target.result;
          avatarPreview.style.display = "block";
        };
        reader.readAsDataURL(file);
      });
    </script>
  </body>
</html>
