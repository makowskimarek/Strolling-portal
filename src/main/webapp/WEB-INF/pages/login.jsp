<!DOCTYPE html>
<html lang="pl">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	
    <title>Strolling meeting</title>
    
    <!-- meta data -->
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <meta name="description" content="Strolling meeting - walk together" />
	<meta name="keywords" content="Strolling, meet, meeting, together" />
    <meta name="author" content="RepubliC">
    
    <!--JQuerry-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

    <!-- Bootstrap -->
    <link href="resources/sources/css/bootstrap.min.css" rel="stylesheet"> 
    <script src="resources/sources/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!--Own CSS-->
    <link href="resources/sources/styles/strolling.css" rel="stylesheet"> 

    <!--Own JS script-->
    <script type="text/javascript">
        //function to validate login form
        function validLogin(){
            //init variables from form
            var nick = document.login.nick.value;
            var password = document.login.password.value;
            
            //check username
            if(nick == "" || nick == null){
                 alert("nie wypełniono loginu");
                 document.login.nick.focus();
                 return false;
             }
            
            //check password
            if(password == "" || password == null){
                 alert("nie wypełniono hasła");
                 document.login.password.focus();
                 return false;
             }
            else
                //check length of password
                if(password.length < 6 || password.length > 15){
                    alert("Hasło nie jest z zakresu 6-15 znaków");
                    document.login.password.focus();
                    return false;
                }
            
            return ( true );
        }
    </script>
</head>
<body>
    <div class = "container-fluid login">
	
        <div class="window"></div>
             
		  <form action="/Login" 
                method="post" onsubmit="return validLogin();"
                id="login" name="login"
                class="formDisplay">    
              
		    <h3 class="header">
                Page disabled to watch while not login
            </h3>
                
			  
			  <input type="text" class="form-control" name="nick" placeholder="Login" required="" autofocus="" />
			  <input type="password" class="form-control" name="password" placeholder="Hasło" required=""/>     		  
			 
			  <button class="btn btn-lg btn-primary btn-block"  name="Submit" value="Login" type="Submit">Login</button>  			
		  </form>
    
    </div>
        
</body>
</html>