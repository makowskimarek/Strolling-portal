<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Strolling meeting</title>

    <!-- meta data -->
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8"/>
    <meta name="description" content="Strolling meeting - walk together"/>
    <meta name="keywords" content="Strolling, meet, meeting, together"/>
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
                    <button class="dropdown-toggle" id="navFriendRequest" data-toggle="dropdown" aria-haspopup="true"
                            aria-expanded="true">
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
                    <button class="dropdown-toggle" id="navMessage" data-toggle="dropdown" aria-haspopup="true"
                            aria-expanded="true">
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
                    <button class="dropdown-toggle" id="navNotification" data-toggle="dropdown" aria-haspopup="true"
                            aria-expanded="true">
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
                    <button class="dropdown-toggle" id="navMoreOption" data-toggle="dropdown" aria-haspopup="true"
                            aria-expanded="true">
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
                    <!--Avatar-->
                    <div class="row">
                        <a href="/Profile">
                            <img src="http://live-music.pl/pp/images/avatar/RepubliC.jpg"
                                 class="media-object img-responsive center-block"/>
                        </a>
                    </div>
                </div>

                <div class="row">
                    <a href="/Main">
                        <p>
                            <span class="glyphicon glyphicon-home"></span>
                            Aktualności
                        </p>
                    </a>
                </div>

                <div class="row">
                    <a href="/Friends">
                        <p>
                            <span class="glyphicon glyphicon-user"></span>
                            Znajomi
                        </p>
                    </a>
                </div>

                <div class="row">
                    <a href="/Announce">
                        <p>
                            <span class="glyphicon glyphicon-list-alt"></span>
                            Ogłoszenia
                        </p>
                    </a>
                </div>

                <div class="row">
                    <a href="/Meeting">
                        <p>
                            <span class="glyphicon glyphicon-calendar"></span>
                            Spacery
                        </p>
                    </a>
                </div>

            </div>
            <!--Tablica-->
            <div class="col-md-7 box" style="border: 1px solid #000;">

                <!--Navigation account bar-->
                <div class="row box">
                    <ul class="nav nav-tabs nav-justified">
                        <li class="active">
                            <a data-toggle="tab" href="#info">
                                Informacje
                            </a>
                        </li>
                        <li>
                            <a data-toggle="tab" href="#account">
                                Dane konta
                            </a>
                        </li>
                    </ul>
                </div>

                <div class="tab-content">
                    <div id="info" class="tab-pane fade in active">
                        <h3 class="text-center">Zmiana informacji odnośnie konta</h3>

                        <form action="/updateData" method="post" id="AccountDetails">

                            <input type="hidden" class="form-control"
                                   id="AccountDetailsID" name="ID"
                                   placeholder="<%out.println(request.getAttribute("ID"));%>">

                            <div class="form-group">
                                <label for="firstName">Imie:</label>
                                <input type="text" class="form-control"
                                       id="firstName" name="firstName"
                                       placeholder="<%out.println(request.getAttribute("firstName"));%>">
                            </div>

                            <div class="form-group">
                                <label for="lastName">Nazwisko:</label>
                                <input type="text" class="form-control"
                                       id="lastName" name="lastName"
                                       placeholder="<%out.println(request.getAttribute("lastName"));%>">
                            </div>

                            <div class="form-group">
                                <label for="city">Miejscowość:</label>
                                <input type="text" class="form-control"
                                       id="City" name="City"
                                       placeholder="<%out.println(request.getAttribute("City"));%>">
                            </div>

                            <div class="form-group">
                                <label for="description">O mnie:</label>
                                <textarea class="form-control" rows="5" id="description" name="description">Lubię spokojne spacerki, głównie w łonie natury.Nie lubię przebywać w towarzystwie osób palących.</textarea>
                            </div>

                            <button type="submit" class="btn btn-default">Zapisz</button>
                            <button type="button" class="btn btn-default" onClick="location.href='/Profile'">Anuluj</button>

                        </form>

                    </div>

                    <div id="account" class="tab-pane fade">

                        <h3> Zmiana danych konta </h3>

                        <div class="container-fluid">

                            <div class="row row-eq-height">
                                <!--Form Change mail -->
                                <div class="col-md-6" style="padding: 10px">
                                    <form action="/updateMail" method="post" id="AccountMail">

                                        <div class="form-group">
                                            <input type="hidden" class="form-control"
                                                   id="AccountMailID" name="ID"
                                                   placeholder="<%out.println(request.getAttribute("ID"));%>">
                                        </div>

                                        <div class="form-group">
                                            <label for="mail">Mail:</label>
                                            <input type="email" class="form-control"
                                                   id="mail" name="mail"
                                                   placeholder="<%out.println(request.getAttribute("mail"));%>">
                                        </div>

                                        <button type="submit" class="btn btn-default center-block">Zapisz</button>

                                    </form>
                                </div>

                                <!--Form Change password -->
                                <div class="col-md-6" style="padding: 10px">
                                    <form action="/updatePassword" method="post" id="AccountPassword">

                                        <div class="form-group">
                                            <input type="hidden" class="form-control"
                                                   id="AccountPasswordID"  name="ID"
                                                   placeholder="<%out.println(request.getAttribute("ID"));%>">
                                        </div>

                                        <div class="form-group">
                                            <label for="oldPassword">Stare hasło:</label>
                                            <input type="password" class="form-control"
                                                   id="oldPassword" name="oldPassword">
                                        </div>

                                        <div class="form-group">
                                            <label for="newPassword">Nowe hasło:</label>
                                            <input type="password" class="form-control"
                                                   id="newPassword" name="newPassword">
                                        </div>

                                        <div class="form-group">
                                            <label for="confirmPassword">Potwierdź hasło:</label>
                                            <input type="password" class="form-control"
                                                   id="confirmPassword" name="confirmPassword">
                                        </div>

                                        <button type="submit" class="btn btn-default center-block">Zapisz</button>

                                    </form>
                                </div>
                            </div>

                            <div class="row">
                                <button type="button" class="btn btn-default center-block" onClick="location.href='/Profile'">Anuluj</button>
                            </div>

                        </div>

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
