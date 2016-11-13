<?php
  header("link: <../font-awesome/css/font-awesome.min.css>; rel=preload; as=stylesheet", false);
  header("link: <../public/css/bootstrap.min.css>; rel=preload; as=stylesheet", false);
  header("link: <../public/css/angular-material.min.css>; rel=preload; as=stylesheet", false);
  header("link: <../public/css/menuLateral.css>; rel=preload; as=stylesheet", false);
  header("link: <../public/js/angular.min.js>; rel=preload; as=script", false);
  header("link: <../public/js/angular-animate.min.js>; rel=preload; as=script", false);
  header("link: <../public/js/angular-aria.min.js>; rel=preload; as=script", false);
  header("link: <../public/js/angular-messages.min.js>; rel=preload; as=script", false);
  header("link: <../public/js/angular-material.min.js>; rel=preload; as=script", false);
  header("link: <../scripts/app.js>; rel=preload; as=script", false);
  header("link: <../scripts/controllers/main.js>; rel=preload; as=script", false);
  header("link: <../scripts/services/main.js>; rel=preload; as=script", false);
  header("link: <../scripts/services/shareType.js>; rel=preload; as=script", false);
?>

<!DOCTYPE html>
<html>
  <head>

    <link rel="stylesheet" href="../font-awesome/css/font-awesome.min.css">

  	<link rel="stylesheet" href="../public/css/bootstrap.min.css">

  	<link rel="stylesheet" href="../public/css/angular-material.min.css">

    <link rel="stylesheet" href="../public/css/menuLateral.css">

  	<script src="../public/js/angular.min.js"></script>
    <script src="../public/js/angular-animate.min.js"></script>
    <script src="../public/js/angular-aria.min.js"></script>
    <script src="../public/js/angular-messages.min.js"></script>
    <!-- Angular Material Library -->
    <script src="../public/js/angular-material.min.js"></script>

  	<script type="text/javascript" src="../scripts/app.js"></script>

  	<script src="../scripts/controllers/main.js"></script>
  	<script src="../scripts/services/main.js"></script>
    <script src="../scripts/services/shareType.js"></script>

  	<title></title>
  </head>
  <body ng-app="trabalhoSD" ng-controller="mainController" style="padding-right: 50px;">
    <!-- MENU LATERAL-->
    <nav class="navbar navbar-default sidebar" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-sidebar-navbar-collapse-1">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>      
        </div>
        <!-- glyphicon glyphicon-grain -->
        <div class="collapse navbar-collapse" id="bs-sidebar-navbar-collapse-1">
          <ul class="nav navbar-nav">
            <li class="active"><h4 style="text-align: center">Selecione um Tipo</h4><span style="font-size:16px;" class="pull-right hidden-xs showopacity "></span></li>
            <li ng-click="selectType(1)"><a href="#">Motivação<span style="font-size:16px;" class="pull-right hidden-xs showopacity fa fa-handshake-o"></span></a></li>
            <li ng-click="selectType(2)"><a href="#">Felicitações<span style="font-size:16px;" class="pull-right hidden-xs showopacity fa fa-thumbs-o-up"></span></a></li>
            <li ng-click="selectType(3)"><a href="#">Saudade<span style="font-size:16px;" class="pull-right hidden-xs showopacity fa fa-map-marker"></span></a></li>
            <li ng-click="selectType(4)"><a href="#">Superação de Problemas<span style="font-size:16px;" class="pull-right hidden-xs showopacity fa fa-bolt"></span></a></li>
            <li ng-click="selectType(5)"><a href="#">Amor<span style="font-size:16px;" class="pull-right hidden-xs showopacity fa fa-heart"></span></a></li>
            <li ng-click="selectType(6)"><a href="#">Experiências de Vida<span style="font-size:16px;" class="pull-right hidden-xs showopacity fa fa-lightbulb-o"></span></a></li>
            <li ng-click="selectType(7)"><a href="#">Caráter<span style="font-size:16px;" class="pull-right hidden-xs showopacity fa fa-smile-o"></span></a></li>
            <li ng-click="selectType(8)"><a href="#">Top (as mais requisitadas)<span style="font-size:16px;" class="pull-right hidden-xs showopacity fa fa-fire"></span></a></li>
            <li ng-click="selectType(10)"><a href="#">Buscar Pelo ID<span style="font-size:16px;" class="pull-right hidden-xs showopacity fa fa-search"></span></a></li>
          </ul>
        </div>
      </div>
    </nav>
    <!-- MENU LATERAL-->
    <br>
    <h2 style="text-align: center;">Bem-Vindo a Base de Belas Mensagens</h2>
    <hr>
    <div style="display:flex">

      <!-- Conteúdo -->
      <div style="padding-left: 50px; width:100%;" ng-show="type != 0">
        <h1 style="text-align: center;" ng-show="type == 1">Mensagens de Motivação</h1>
        <h1 style="text-align: center;" ng-show="type == 2">Mensagens de Felicitações</h1>
        <h1 style="text-align: center;" ng-show="type == 3">Mensagens de Saudade</h1>
        <h1 style="text-align: center;" ng-show="type == 4">Mensagens de Superação de Problemas</h1>
        <h1 style="text-align: center;" ng-show="type == 5">Mensagens de Amor</h1>
        <h1 style="text-align: center;" ng-show="type == 6">Mensagens de Experiências de Vida</h1>
        <h1 style="text-align: center;" ng-show="type == 7">Mensagens de Caráter</h1>
        <h1 style="text-align: center;" ng-show="type == 8">Mensagens Top (as mais requisitadas)</h1>
        <h1 style="text-align: center;" ng-show="type == 9">Frase Aleatória</h1>
        <h1 style="text-align: center;" ng-show="type == 10">Busca por ID</h1>
        <md-button class="md-primary md-raised" ng-show="type != 10" ng-click="openModalCreateMessage()">
          Nova Mensagem
        </md-button>
        <i ng-click="openModalRandomMessage(type)" ng-show="type != 10" class="fa fa-random" aria-hidden="true"></i>
        <div class="md-dialog-content" ng-show="type == 10" style="display:flex">
          <div>
            <md-input-container  class="md-block">
              <label>ID</label>
              <input ng-model="message.id">
            </md-input-container>
          </div>
          <div ng-show="type == 10">
            <md-button class="md-primary md-raised"  ng-click="search(message.id)">
              Buscar
            </md-button>
            <h2>Mensagem: {{message.description}}</h2>
          </div>
        </div>

        <div class="row" ng-show="type != 10">
          <table class="table table-striped">
            <thead>
              <tr>
                <td><b>Mensagem</b></td>
                <td><b>Tipo</b></td>
                <td align="center"><b>Editar</b></td>
                <td align="center"><b>Excluir</b></td>
              </tr>
            </thead>

            <tbody>
              <tr ng-repeat="message in messages">
                <td>{{message.description}}</td>
                <td>{{message.type}}</td>
                <td align="center">
                  <span class="fa fa-pencil-square-o" aria-hidden="true" ng-click="openModalCreateMessage(message)"></span>
                </td>
                <td align="center">
                  <span class="fa fa-trash" aria-hidden="true" ng-click="removeMessage(message)"></span>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>

    </div>

  </body>
</html>