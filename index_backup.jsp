<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>P2P Exchange</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel="stylesheet" href="bootstrap-5.1.3-dist/css/bootstrap.min.css"/>
    <link href="bootstrap-5.1.3-dist/css/bootstrap-theme.css" rel="stylesheet" />
    <link rel='stylesheet' type='text/css' media='screen' href='./css/dashboard.css'> 
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Questrial&display=swap" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src='./js/main.js'></script>

</head>
<body>
  

  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <a class="navbar-brand" href="#">P2P Trade</a>
      <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="#">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Our mission</a>
          
          </li>
          <li class="nav-item">
            <a class="nav-link disabled">Contacts</a>
          </li>
        </ul>
        
          <li class="login">
            <a href="./login.jsp" class="btn btn-primary">Authentication</a>
          </li>
      
      </div>
    </div>
  </nav>
          
<div class="body">    
  <h2 class="wellcome-title">
    Welcome to the best P2P Cryto exchange platform
  </h2>
<div id="trends">
  <div class="row">
          <div class="card" style="width:20rem;">
        
            
            <div class="card-body">
              <div class="titleandimg">
              <img id="cherry" src="https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/120/google/313/upwards-button_1f53c.png" class="card-img-top" alt="...">
              <h5 class="card-title">Winners</h5>
            </div>
              <p class="card-text">...</p>
              <!-- TradingView Widget BEGIN -->
<div class="tradingview-widget-container">
  <div class="tradingview-widget-container__widget"></div>

  <script type="text/javascript" src="https://s3.tradingview.com/external-embedding/embed-widget-screener.js" async>
  {
  "width": "200",
  "height": "490",
  "defaultColumn": "overview",
  "screener_type": "crypto_mkt",
  "displayCurrency": "USD",
  "colorTheme": "dark",
  "locale": "en",
  "isTransparent": true
}
  </script>
</div>
<!-- TradingView Widget END -->
              <a href="#" class="btn btn-warning">Winners</a>
            </div>
          </div>
          <div class="card" style="width: 20rem;">
        
            
            <div class="card-body">
              <div class="titleandimg">
              <img id="cherry" src="https://emojipedia-us.s3.amazonaws.com/source/skype/289/red-triangle-pointed-down_1f53b.png" class="card-img-top" alt="...">
              <h5 class="card-title">Loosers</h5>
            </div>
              <p class="card-text">...</p>
              <!-- TradingView Widget BEGIN -->
<div class="tradingview-widget-container">
  <div class="tradingview-widget-container__widget"></div>

  <script type="text/javascript" src="https://s3.tradingview.com/external-embedding/embed-widget-screener.js" async>
  {
  "width": "200",
  "height": "490",
  "defaultColumn": "overview",
  "screener_type": "crypto_mkt",
  "displayCurrency": "USD",
  "colorTheme": "dark",
  "locale": "en",
  "isTransparent": true
}
  </script>
</div>
<!-- TradingView Widget END -->
              <a href="#" class="btn btn-danger">Loosers</a>
            </div>
          </div>

        
        </div>
      </div>
    </div>
    <!-- TradingView Widget BEGIN -->
<div class="tradingview-widget-container">
  <div id="tradingview_efa2b"></div>
  <div class="tradingview-widget-copyright"><a href="https://www.tradingview.com/symbols/NASDAQ-AAPL/" rel="noopener" target="_blank"><span class="blue-text">AAPL Chart</span></a> by TradingView</div>
  <script type="text/javascript" src="https://s3.tradingview.com/tv.js"></script>
  <script type="text/javascript">
  new TradingView.widget(
  {
  "width": 980,
  "height": 400,
  "symbol": "NASDAQ:AAPL",
  "interval": "D",
  "timezone": "Etc/UTC",
  "theme": "dark",
  "style": "1",
  "locale": "en",
  "toolbar_bg": "#f1f3f6",
  "enable_publishing": false,
  "allow_symbol_change": true,
  "container_id": "tradingview_efa2b"
}
  );
  </script>
</div>
<!-- TradingView Widget END -->

<body class="d-flex flex-column">
  <div id="page-content">
    <div class="container text-center">
      <div class="row justify-content-center">
        <div class="col-md-7">
          <h1 class="fw-light mt-4 text-white">Easy Trade</h1>
          <p class="lead text-white-50">Buy/Sell today..................</p>
        </div>
      </div>
    </div>
  </div>
</div>
  <footer id="sticky-footer" class="flex-shrink-0 py-4 bg-dark text-white-50">
    <div class="container text-center">
      <small>Copyright &copy; P2P Trade LTD</small>
    </div>
  </footer>
</body>
</body>
</html>