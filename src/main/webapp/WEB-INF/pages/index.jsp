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
    
    <!-- Site icon -->
    <!--<link rel="shortcut icon" type="image/png" href="sources/favicon.png"/>-->
    
    <!-- Bootstrap -->
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
    
    <!--Own CSS-->
    <link href="resources/styles/strolling.css" rel="stylesheet">
    
    <!-- Own scripts -->


</head>
<body>
    
    <div class="container-fluid" style="background: url('resources/images/bg.jpg')">
    
        <!--Pasek nawigacji-->
        <nav id="mainNav" 
             class="navbar navbar-default"
             style="background:#009000">
            
            <div class="container-fluid">
            
                <div class="navbar-header">
                    <a class="navbar-brand" href="#" style="color: #fff">Strolling meeting</a>
                </div>
                
                <!--Logowanie-->
                <form name='f' id="login" method="post"
                      action="/login"
                      class="navbar-form navbar-right" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" name="username" placeholder="Login">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="password" placeholder="Hasło">
                    </div>
                    <button name="submit" type="submit" class="btn btn-default">Zaloguj</button>
                    <input name="_csrf" type="hidden" value="6829b1ae-0a14-4920-aac4-5abbd7eeb9ee" />
                </form>
                
            </div>
            
        </nav>
        
        <!--Ciało strony-->
        <div class="container-fluid">
            <div class="row">
                <!--Opis-->
                <div class="col-sm-8">
                
                </div>
                
                <!--Formularz rejestracji-->
                <div class="col-sm-4">
                    
                    <form id="register" method="post" 
                          action="/Register">
                        <h3> Rejestracja </h3>
                        <small> Załóż już teraz konto i korzystaj z naszego serwisu</small>
                        <div class="form-group">
                            
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
