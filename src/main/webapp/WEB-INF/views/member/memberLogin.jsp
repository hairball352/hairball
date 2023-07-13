<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/templates/header.jsp" %>    
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<div id="container" class="container">
    <!-- FORM SECTION -->
    <div class="row">
    
      <!-- SIGN IN -->
      <div class="col align-items-center flex-col sign-in">
        <div class="form-wrapper align-items-center">
          <div class="form sign-in">
            <div class="input-group">
              <i class='bx bxs-user'></i>
              <input type="text" placeholder="Username">
            </div>
            <div class="input-group">
              <i class='bx bxs-lock-alt'></i>
              <input type="password" placeholder="Password">
            </div>
            <button>
              털뭉치들 로그인
            </button>
            <p>
              <b>
                Forgot password?
              </b>
            </p>
            <p>
              <span>
                Don't have an account?
              </span>
              <b onclick="toggle()" class="pointer">
                Sign up here
              </b>
            </p>
          </div>
        </div>
        <div class="form-wrapper">
    
        </div>
      </div>
      <!-- END SIGN IN -->
        </div>
    <!-- END FORM SECTION -->
      <script>
      let container = document.getElementById('container')

      toggle = () => {
        container.classList.toggle('sign-in')
        container.classList.toggle('sign-up')
      }

      setTimeout(() => {
        container.classList.add('sign-in')
      }, 200)
      </script>
</body>
</html>
<%@ include file="/WEB-INF/views/templates/footer.jsp" %>