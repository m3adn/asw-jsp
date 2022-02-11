<!DOCTYPE html>
<html>
    <head>
        <meta charset='utf-8'>
        <meta http-equiv='X-UA-Compatible' content='IE=edge'>
        <title>Swappstter</title>
        <meta name='viewport' content='width=device-width, initial-scale=1'>
        <l    <link rel="preconnect" href="https://fonts.googleapis.com">
            <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
            <link href="https://fonts.googleapis.com/css2?family=Questrial&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="bootstrap-5.1.3-dist/css/bootstrap.min.css"/>
        <link href="bootstrap-5.1.3-dist/css/bootstrap-theme.css" rel="stylesheet" />
        <link rel='stylesheet' type='text/css' media='screen' href='./css/style.css'> 
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <script src='./js/main.js'></script>
    </head>
    <div class="title-div">
        <a class="title" href="./index.jsp">
            <span id="title1">SWAPPSTTER.</span><span id="title2">IO</span>
        </a>
    </div>
    
    <body>
        <div class="main">
            
          <p class="sign" align="center">Sign in</p>
          <form name="Login" method="post" action="Servlet/Login" class="form1">
            <input class="un " type="text" align="center" name="Email" placeholder="Email">
            <input class="pass" type="password" align="center" name="Password" placeholder="Password">
            <input type="submit" value="Autentication" />
          </form>  
            <p class="forgot" align="center"><a href="./acc-recovery.jsp" id="forgot">Forgot Password?</p>
            <p class="register" align="center"><a href="./register.jsp" id="register">No account? Click to register!</p>
                      
          </div>
      </body>

</html>