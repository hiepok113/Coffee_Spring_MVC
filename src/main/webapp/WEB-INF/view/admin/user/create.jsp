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
              <li class="breadcrumb-item">
                <a href="/admin">Dashboard</a>
              </li>
              <li class="breadcrumb-item">
                <a href="/admin/user">Users</a>
              </li>
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
                      <div class="mb-3">
                        <label class="form-label">Email</label>
                        <form:input
                          path="email"
                          type="email"
                          class="form-control"
                          placeholder="Enter email"
                          required="true"
                        />
                      </div>

                      <!-- Password -->
                      <div class="mb-3">
                        <label class="form-label">Password</label>
                        <form:password
                          path="password"
                          class="form-control"
                          placeholder="Enter password"
                          required="true"
                        />
                      </div>

                      <!-- Role -->
                      <div class="mb-3">
                        <label class="form-label">Role</label>
                        <form:select class="form-select" path="role.name">
                          <form:option value="">-- Select role --</form:option>
                          <form:option value="USER">User</form:option>
                          <form:option value="ADMIN">Admin</form:option>
                        </form:select>
                      </div>
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

                      <!-- Avatar Upload -->
                      <div class="mb-3">
                        <label for="avatarFile" class="form-label">
                          Avatar
                        </label>
                        <input
                          class="form-control"
                          type="file"
                          id="avatarFile"
                          name="avatarFile"
                          accept=".png, .jpg, .jpeg"
                        />
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
