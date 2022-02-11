<html>
<head>
<title>User Logged In</title>
</head>
<body>
     
    <form name="Account" method="post" action="Servlet/Account/Forget">
        Username: <input type="text" name="Username"/> <br/>
        Password: <input type="password" name="Password"/> <br/>
        Email: <input type="text" name="Email"/> <br/>
        PhoneNumber: <input type="text" name="PhoneNumber"/> <br/>
        ReferralCode: <input type="text" name="ReferralCode"/> <br/>
        <input type="submit" value="Login" />
    </form>
    
    <form name="Forget" method="post" action="Servlet/Login/Forget">
        Email: <input type="text" name="Email"/> <br/>
        PhoneNumber: <input type="text" name="PhoneNumber"/> <br/>
        <input type="submit" value="Submit" />
    </form>

</body>
</html>