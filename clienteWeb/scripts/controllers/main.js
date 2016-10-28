angular.module("trabalhoSD").controller("mainController", function($scope,  mainService) {   

    //teste
    $scope.usuarios = [
      {nome: "José", email: "jose@mail.com.br"},
      {nome: "Maria", email: "maria@mail.com.br"},
      {nome: "Ana", email: "ana@mail.com.br"},
    ];

    $scope.adicionarUsuario = function(usuario) {
      $scope.usuarios.push(usuario);
      
      delete $scope.usuario;
    };


    $scope.getUsuariosServer = function() {
      mainService.get().then(function(res){            
          $scope.usuarios = res.data;     
      });
    };

    //$scope.getUsuariosServer();

    $scope.adicionarUsuarioServer = function(usuario) {
      mainService.post(usuario).then(function(res){
          $scope.getUsuarios();
          delete $scope.usuario;
      });
    };
});