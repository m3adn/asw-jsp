<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>P2P Exchange</title>
    <meta name='viewport' content='width=device-width, initial-scale=1', user-scalable=yes">
    <link rel="stylesheet" href="bootstrap-5.1.3-dist/css/bootstrap.min.css"/>
    <link href="bootstrap-5.1.3-dist/css/bootstrap-theme.css" rel="stylesheet" />
    <link rel='stylesheet' type='text/css' media='screen' href='./css/dashboard.css'> 
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Questrial&display=swap" rel="stylesheet">
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js">  </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    
    <script src="https://cdn.jsdelivr.net/npm/chart.js@3.7.0/dist/chart.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.7.0/dist/chart.esm.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.7.0/dist/chart.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.7.0/dist/helpers.esm.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.js"></script>

</head>
<body>
  <!-- Vertical navbar -->
<div class="vertical-nav bg-white" id="sidebar">
  <div class="py-4 px-3 mb-4 bg-light">
    <div class="media d-flex align-items-center"><a class="navbar-brand" href="#">SWAPPSTTER.IO</a>
      
    </div>
  </div>

  <p class="text-gray font-weight-bold text-uppercase px-3 small pb-4 mb-0">Main</p>

  <ul class="nav flex-column bg-white mb-0">
    <li class="nav-item">
      <a href="./charts.jsp" class="nav-link text-dark font-italic bg-light">
        <i class="fa fa-th-large mr-3 text-primary fa-fw"></i>
        Charts
      </a>
    </li>
    <li class="nav-item">
      <a href="#" class="nav-link text-dark font-italic">
        <i class="fa fa-address-card mr-3 text-primary fa-fw"></i>
        Trade Pairs
      </a>
    </li>
    <li class="nav-item">
      <a href="#" class="nav-link text-dark font-italic">
        <i class="fa fa-cubes mr-3 text-primary fa-fw"></i>
        Dashboard
      </a>
    </li>
    <li class="nav-item">
      <a href="#" class="nav-link text-dark font-italic">
        <i class="fa fa-history mr-3 text-primary fa-fw" aria-hidden="true"></i>
      Transaction History
      </a>
    </li>
  </ul>

  <p class="text-gray font-weight-bold text-uppercase px-3 small py-4 mb-0">Useful</p>

  <ul class="nav flex-column bg-white mb-0">
    <li class="nav-item">
      <a href="#" class="nav-link text-dark font-italic">
        
        <i class="fa fa-cogs mr-3 text-primary fa-fw" aria-hidden="true"></i>
        Settings
      </a>
    </li>
    <li class="nav-item">
      <a href="#" class="nav-link text-dark font-italic">
        <i class="fa fa-spinner fa-spin  mr-3 text-primary fa-fw"></i>
        Help Desk
      </a>
    </li>
    <li class="nav-item">
      <a href="#" class="nav-link text-dark font-italic">
        <i class="fa fa-language mr-3 text-primary fa-fw" aria-hidden="true"></i>
        About Us
      </a>
    </li>
    <li class="nav-item">
      <a id="Logout" href="#" class="nav-link text-dark font-italic">
        <i class="fa fa-cogs mr-3 text-primary fa-fw" aria-hidden="true"></i>
        Log Out
      </a>
    </li>
    <li class="nav-item">
      <a id="DeleteAccount" href="#" class="nav-link text-dark font-italic">
        <i class="fa fa-solid fa-trash mr-3 text-primary" aria-hidden="true"></i>
        Delete Account
      </a>
    </li>
  </ul>
  

  
  
</div>
<!-- End vertical navbar -->

  
          
<div class="body">
  
  <div class="user-dash">
  
<div id="profile">
   <a href=""><img src="./images/R.png" width="auto" height="30px"> </a> 
  </div>

<div id="profile">
  <a href=""><img src="./images/message.png" width="auto" height="30px"> </a>
</div>

  <div id="profile">
    <a href="./useraccount.jsp"><img src="./images/profile.png" width="auto" height="30px"> </a>
  
    </div>
    <div id="profile">
    <!-- Toggle button -->
    <button id="sidebarCollapse" type="button" class="btn btn-light bg-white shadow-sm rounded-pill px-4 mb-4"><i class="fa fa-bars mr-2"></i><small class="text-uppercase font-weight-bold">Toggle</small></button>
  </div>
</div>

<div id="Welcome" class="fw-bold text-center fs-1"></div>
<div id="Balance" class="fw-bold text-center fs-2"></div>

  <div class="page-content p-5" id="content">
     

   
<div class="dash-principal">
    
    
  <section id="transactions1">
    <h1 id="title">Your Portfolio</h1>
    <div id="Wallet">
      
  <table id="portfolio">
    <tr>
      <th>Date Time</th>
      <th>Coin</th>
      <th>Units</th>
    </tr>
  </table>
      </div>
  </section>
<br>
<section id="transactions2">
  <h1 id="title">Transactions Available</h1>  
  <div id="Wallet">
    
<table id="CoinsAvailable">
  <tr>
      <th>Seller</th>
      <th>Date Time</th>
      <th>Coin</th>
      <th>Units</th>
  </tr>
</table>
    </div>
</section>
<br>


</div>


<br>


</div>

</div>

</body>

<footer id="sticky-footer" class="flex-shrink-0 py-4 bg-dark text-white-50">
  <div class="container text-center">
    
    <div id="profile">
    <div class="container h-100">
    <div class="d-flex justify-content-center h-100">
      <div class="searchbar">
        <input class="search_input" type="text" name="" placeholder="Search...">
        <a href="#" class="search_icon"><i class="fas fa-search"></i></a>
      </div>
    </div>
  </div>
    
    </div>
    <br>
    <small>Copyright &copy; P2P Trade LTD</small>


  </div>
  
  
</footer>

<script src='./js/main.js'></script>
<script src="./js/Dashboard.js"></script>
</html>