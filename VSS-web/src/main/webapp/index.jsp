<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="banner_style.css">
        
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.5.1.min.js"></script>
        <script type="text/javascript">
            function changeMainContentView(view){
                $('#mainContentView').load(view)
            }
        </script>
        
        <title>Video Store Home</title>
    </head>
    <body>
        <h1>Welcome to our On-Line Video Store!!!</h1>
        <a href="login.htm">Account Login</a>
        <br>
        <a href="registerAccountView.htm">Register Account</a>
    </body>
</html>
