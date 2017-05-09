﻿<!DOCTYPE html>
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
            var username = document.login.username.value;
            var password = document.login.password.value;
            
            //check username
            if(username == "" || username == null){
                 alert("nie wypełniono loginu");
                 document.login.username.focus();
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
        //function to validate register form
        function validRegister(){
            //init variables from form
            var firstName = document.register.firstName.value;
            var lastName = document.register.lastName.value;
            var city = document.register.city.value;
            
            var nick = document.register.nick.value;
            var mail = document.register.mail.value;
            var password = document.register.password.value;
            
            //check if all fields are set
            
            //check fisrtname
             if(firstName == "" || firstName == null){
                 alert("nie wypełniono imienia");
                 document.register.firstName.focus();
                 return false;
             }
            
            //check lastname
            if(lastName == "" || lastName == null){
                 alert("nie wypełniono nazwiska");
                 document.register.lastName.focus();
                 return false;
             }
            
            //check city
            if(city == "" || city == null){
                 alert("nie wypełniono miejscowości");
                 document.register.city.focus();
                 return false;
             }
            
            //check nick
            if(nick == "" || nick == null){
                 alert("nie wypełniono loginu");
                 document.register.nick.focus();
                 return false;
             }
            
            //check mail
            if(mail == "" || mail == null){
                 alert("nie wypełniono mailu");
                 document.register.mail.focus();
                 return false;
             }
            else{
                    atpos = mail.indexOf("@");
                    dotpos = mail.lastIndexOf(".");

                    if (atpos < 1 || dotpos < atpos+2 || dotpos+2 >= mail.length ) 
                    {
                        alert("Niepoprawny format maila")
                        document.register.mail.focus() ;
                        return false;
                    }
            }
            
            //check password
            if(password == "" || password == null){
                 alert("nie wypełniono hasła");
                 document.register.password.focus();
                 return false;
             }
            else
                //check length of password
                if(password.length < 6 || password.length > 15){
                    alert("Hasło nie jest z zakresu 6-15 znaków");
                    document.register.password.focus();
                    return false;
                }
            
            return ( true );
        }
    </script>

</head>
<body>
    
    <div class="container-fluid test">
    
        <!--Pasek nawigacji-->
        <nav id="mainNav" 
             class="navbar navbar-default"
             style="background:#009000">
            
            <div class="container-fluid">
            
                <div class="navbar-header">
                    <a class="navbar-brand" href="#" style="color: #fff">Strolling meeting</a>
                </div>
                
                <!--Logowanie-->
                <form id="login" name="login"
                      method="post"
                      action="/Login" onsubmit="return validLogin();"
                      class="navbar-form navbar-right" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" name="username" placeholder="Login">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="password" placeholder="Hasło">
                    </div>
                    <button type="submit" class="btn btn-default">Zaloguj</button>
                </form>
                
            </div>
            
        </nav>
        
        <!--Ciało strony-->
        <div class="container-fluid">
            <div class="row">
                <!--Opis-->
                <div class="col-sm-7 box">
                    <h3> Strolling meeting </h3>
                    <p>
                        Serwis społeczniościowy służący do organizowania spacerów z innymi użytkownikami serwisu.
                    </p>
                </div>
                
                <!--Formularz rejestracji-->
                <div class="col-sm-4 box">
                    
                    <form id="register" name="register"
                          method="post" 
                          action="/Register" onsubmit=" return validRegister();">
                        <h3> Rejestracja </h3>
                        <small> Załóż już teraz konto i korzystaj z naszego serwisu</small>
                        <div class="form-group">
                            
                            <div class="row">
                                <div class="form-group col-xs-6">
                                    <input id="firstName" name="firstName" 
                                       class="form-control"
                                       type="text" placeholder="Imie">
                                </div>
                                <div class="form-group col-xs-6">
                                    <input id="lastName" name="lastName" 
                                       class="form-control"
                                       type="text" placeholder="Nazwisko">
                                </div>
                                
                            </div>
                            
                            <input id="city" name="city" 
                                   class="form-control"
                                   type="text" placeholder="Miejscowość">
                            
                            <input id="nick" name="nick" 
                                   class="form-control"
                                   type="text" placeholder="Login">
                            
                            <input id="mail" name="mail" 
                                   class="form-control"
                                   type="mail" placeholder="E-Mail">
                            
                            <input id="password" name="password"
                                   class="form-control"
                                   type="password" placeholder="Hasło">
                            
                           <!-- Akceptowanie regulaminu-->
                            <p>
                             Klikając przycisk Rejestracja, akceptujesz nasz Regulamin oraz potwierdzasz zapoznanie się z Zasadami dotyczącymi danych, w tym z Zasadami stosowania plików cookie.
                            </p>
                            
                            <input id="regSend" name="regSend" 
                                   class="btn btn-default"
                                   type="submit" value="Rejestruj">
                        </div>
                    </form>
                    
                </div>
                
            </div>
        </div>
        
    </div>
    
    
    <!-- Stopka -->
    <footer>
        <div class="copyright">
            <p>Copyright 2017 by <b>Strolling meeting</b> - All rights reserved.</p>
        </div>
        <div class="created">
            <p>Created by: RepubliC</p>
        </div>

    </footer>
    
</body>
</html>
