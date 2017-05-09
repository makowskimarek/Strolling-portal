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

</head>
<body>
    
    <div class="container-fluid">
    
        <!--Pasek nawigacji-->
        <nav id="mainNav" 
             class="navbar navbar-default"
             style="background:#009000">
            
            <div class="container-fluid">
            
                <div class="navbar-header">
                    <a class="navbar-brand" href="/Main" style="color: #fff">Strolling meeting</a>
                </div>
                
               <!-- Ikony powiadomień-->
                <div class="navbar-button navbar-right container-fluid" style="margin-top: 1em">
                    
                    <!--Friend's request-->
                    <div class="btn-group" role="group">
                        <button class="dropdown-toggle" id="navFriendRequest" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                            <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                            <span class="badge">1</span>
                        </button>
                        
                        <ul class="dropdown-menu" aria-labelledby="navFriendRequestValue">
                            <li>
                                <div class="media drop">
                                  <div class="media-left">

                                      <img class="media-object avatar" 
                                           src="resources/images/czat_avatar.png">

                                  </div>
                                  <div class="media-body">
                                    <p class="media-heading">
                                        <span class="person">Justyna Kamińska</span>
                                    </p>
                                      
                                    <div class="btn-group">
                                        <button class="btn btn-success">
                                            Akceptuj
                                        </button>
                                        <button class="btn btn-danger">
                                            Odrzuć
                                        </button>
                                    </div>
                                    
                                  </div>
                                </div>
                            </li>

                        </ul>
                    </div>
                    
                    <!--Message-->
                    <div class="btn-group" role="group">
                        <button class="dropdown-toggle" id="navMessage" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                            <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
                            <span class="badge">2</span>
                        </button>
                        
                        <ul class="dropdown-menu" aria-labelledby="navMessageValue">
                            <li>
                                <div class="media drop">
                                  <div class="media-left">

                                      <img class="media-object avatar" 
                                           src="resources/images/czat_avatar.png">

                                  </div>
                                  <div class="media-body">
                                    <p class="media-heading">
                                        <span class="person">Jan Kowalski</span>
                                    </p>
                                        Dzięki za spacer
                                    
                                  </div>
                                </div>
                            </li>
                            
                            <li>
                                <div class="media drop">
                                  <div class="media-left">

                                      <img class="media-object avatar" 
                                           src="resources/images/czat_avatar.png">

                                  </div>
                                  <div class="media-body">
                                    <p class="media-heading">
                                        <span class="person">Adam Nowak</span>
                                    </p>
                                        Jak coś to zapraszam
                                    
                                  </div>
                                </div>
                            </li>
                            
                        </ul>
                    </div>
                        
                    <!--Notifications-->
                    <div class="btn-group" role="group">
                        <button class="dropdown-toggle" id="navNotification" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                            <span class="glyphicon glyphicon-globe" aria-hidden="true"></span>
                            <span class="badge">1</span>
                        </button>
                        
                        <ul class="dropdown-menu" aria-labelledby="navNotificationValue">
                            <li>
                                <div class="media drop">
                                  <div class="media-left">

                                      <img class="media-object avatar" 
                                           src="resources/images/czat_avatar.png">

                                  </div>
                                  <div class="media-body">
                                    <p class="media-heading">
                                        <span class="person">Jan Kowalski</span>
                                    </p>
                                        Stworzył ogłoszenie <span class="event"> Stare Miasto - Zwiedzanie </span>
                                    
                                  </div>
                                </div>
                            </li>

                        </ul>
                    </div>
                    
                    <!--More Option-->
                    <div class="btn-group" role="group">
                        <button class="dropdown-toggle" id="navMoreOption" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                            <span class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span>
                        </button>
                        
                        <ul class="dropdown-menu" aria-labelledby="navMoreOptionValue">
                            <li>
                                <a href="/Profile">Mój profil</a> 
                            </li>
                            <li>
                                <a href="/Profile-edit">Ustawenia</a>
                            </li>
                            <li>
                                <a href="/Logout">Wyloguj</a>
                            </li>
                        </ul>
                    </div>
                    
                </div>
                
                
            </div>
            
        </nav>
        
        <!--Ciało strony-->
        <div class="container-fluid">
            <div class="row">
                
                <!--Nawigacja-->
                <div class="col-md-2 box text-center">
                    
                    <div class="row">
                        Założyciel
                    </div>
                    
                    <div class="row">
                        <!--Avatar-->
                        <div class="row">
                            <img src="resources/images/czat_avatar.png" 
                                 class="media-object img-responsive center-block"/>
                        </div>
                    </div>
                    
                    <div class="row">
                        <b>Adam Nowak</b>
                    </div>
                    
                    <div class="row">
                        <br>
                        <b>Dane ogłoszenia</b><br>
                        Data utworzenia ogłoszenia: 9.05.2017<br>
                        Data wydarzenia: 18.05.2017<br>
                        Miejsce: Warszawa, Stare Miasto<br>
                    </div>
                    
                    <div class="row">
                        <button class="btn btn-primary">
                            Uczestnicz
                        </button>
                    </div>
                    
                </div>
                
                <!--Tablica-->
                <div class="col-md-7 box center-block">
                    
                    <div class="media">
                        <div class="media-left">
                            <img src="resources/images/event.jpg" class="media-object img-responsive" style="max-width: 25em">
                        </div>
                        
                        <div class="media-body">
                            <p class="media-header" style="font-weight: bold"> Stare Miasto - Zwiedzanie </p>
                            
                            Data wydarzenia: 15.05.2017<br>
                            Godzina: 15:20<br>
                            <br>
                            Więcej szczegółów w pożniejszym czasie
                            
                        </div>
                        
                    </div>
                    
                </div>
                <!--Czat-->
                <div class="col-md-2 box visibile-md visible-lg">
                    
                    <!--Osoba #1 -->
                    
                    <div class="media">
                      <div class="media-left">

                          <img class="media-object" 
                               src="resources/images/czat_avatar.png"
                               width="45">

                      </div>
                      <div class="media-body">
                        <p class="media-heading">Jan Kowalski</p>
                            <p class="chatOnline">Dostępny</p>
                      </div>
                    </div>
                    
                    <!--Osoba #2 -->
                    
                    <div class="media">
                      <div class="media-left">

                          <img class="media-object" 
                               src="resources/images/czat_avatar.png"
                               width="45">

                      </div>
                      <div class="media-body">
                        <p class="media-heading">Adam Nowak</p>
                            <p class="chatOffline">Niedostępny</p>
                      </div>
                    </div>
                
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
    
    <script type="text/javascript">
        $(function () {
          $('[data-toggle="popover"]').popover()
        })
    </script>
    
</body>
</html>
