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
    <meta name="description" content="" />
    <meta name="author" content="" />

    <title>Register - SB Admin</title>

    <link href="/css/style.css" rel="stylesheet" />
    <script
      src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
      crossorigin="anonymous"
    ></script>
  </head>

  <body class="bg-primary">
    <div id="layoutAuthentication">
      <div id="layoutAuthentication_content">
        <main>
          <div class="container">
            <div class="row justify-content-center">
              <div class="col-lg-7">
                <div class="card shadow-lg border-0 rounded-lg mt-5">
                  <div class="card-header">
                    <h3 class="text-center font-weight-light my-4">
                      Create Account
                    </h3>
                  </div>

                  <div class="card-body">
                    <!-- ✅ Spring form bind với RegisterDTO -->
                    <form:form
                      method="post"
                      action="/register"
                      modelAttribute="registerDTO"
                    >
                      <!-- (Tuỳ chọn) Hiển thị lỗi validate tổng -->
                      <!-- <form:errors path="*" cssClass="text-danger" /> -->

                      <div class="row mb-3">
                        <div class="col-md-6">
                          <div class="form-floating mb-3 mb-md-0">
                            <form:input
                              path="firstName"
                              cssClass="form-control"
                              id="inputFirstName"
                              placeholder="Enter your first name"
                            />
                            <label for="inputFirstName">First name</label>

                            <!-- (Tuỳ chọn) Lỗi cho field -->
                            <!-- <form:errors path="firstName" cssClass="text-danger small" /> -->
                          </div>
                        </div>

                        <div class="col-md-6">
                          <div class="form-floating">
                            <form:input
                              path="lastName"
                              cssClass="form-control"
                              id="inputLastName"
                              placeholder="Enter your last name"
                            />
                            <label for="inputLastName">Last name</label>
                            <!-- <form:errors path="lastName" cssClass="text-danger small" /> -->
                          </div>
                        </div>
                      </div>

                      <div class="form-floating mb-3">
                        <spring:bind path="email">
                          <form:input
                            path="email"
                            type="email"
                            id="inputEmail"
                            cssClass="form-control ${status.error ? 'is-invalid' : ''}"
                            placeholder="name@example.com"
                          />
                          <label for="inputEmail">Email address</label>
                          <form:errors
                            path="email"
                            cssClass="invalid-feedback d-block"
                          />
                        </spring:bind>
                      </div>

                      <div class="row mb-3">
                        <div class="col-md-6">
                          <div class="form-floating mb-3 mb-md-0">
                            <spring:bind path="password">
                              <form:password
                                path="password"
                                id="inputPassword"
                                cssClass="form-control ${status.error ? 'is-invalid' : ''}"
                                placeholder="Create a password"
                              />
                              <label for="inputPassword">Password</label>
                              <form:errors
                                path="password"
                                cssClass="invalid-feedback d-block"
                              />
                            </spring:bind>
                          </div>
                        </div>

                        <div class="col-md-6">
                          <div class="form-floating mb-3 mb-md-0">
                            <spring:bind path="confirmPassword">
                              <form:password
                                path="confirmPassword"
                                id="inputPasswordConfirm"
                                cssClass="form-control ${status.error ? 'is-invalid' : ''}"
                                placeholder="Confirm password"
                              />
                              <label for="inputPasswordConfirm"
                                >Confirm Password</label
                              >
                              <form:errors
                                path="confirmPassword"
                                cssClass="invalid-feedback d-block"
                              />
                            </spring:bind>
                          </div>
                        </div>
                      </div>

                      <div class="mt-4 mb-0">
                        <div class="d-grid">
                          <!-- ✅ Submit đúng chuẩn -->
                          <button
                            type="submit"
                            class="btn btn-primary btn-block"
                          >
                            Create Account
                          </button>
                        </div>
                      </div>
                    </form:form>
                  </div>

                  <div class="card-footer text-center py-3">
                    <div class="small">
                      <a href="/login">Have an account? Go to login</a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </main>
      </div>

      <div id="layoutAuthentication_footer">
        <footer class="py-4 bg-light mt-auto">
          <div class="container-fluid px-4">
            <div
              class="d-flex align-items-center justify-content-between small"
            >
              <div class="text-muted">Copyright &copy; Your Website 2023</div>
              <div>
                <a href="#">Privacy Policy</a>
                &middot;
                <a href="#">Terms &amp; Conditions</a>
              </div>
            </div>
          </div>
        </footer>
      </div>
    </div>

    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      crossorigin="anonymous"
    ></script>
    <script src="/js/scripts.js"></script>
  </body>
</html>
