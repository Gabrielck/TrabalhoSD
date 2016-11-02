angular.module("trabalhoSD").controller("mainController", function($scope, $mdDialog,  mainService) {   

    $scope.tipo = 0;

    $scope.selectTipo = function(tipo){
      console.log("Tipo " + tipo + " selecionado");
      $scope.tipo = tipo;
      if(tipo == 1)
      {
        //Isso é a simulação de um get
        $scope.messages = {
          "messages": 
          [
            {
              "description": "Mensagem de Motivação 1",
              "type": "Motivação"
            },
            {
              "description": "Mensagem de Motivação 2",
              "type": "Motivação"
            }
          ]
        }
      }
      if(tipo != 1)
      {
        //Isso é a simulação de um get
        $scope.messages = null;
      }
      console.log($scope.messages.messages);
    };

    var getMessages = function() {
      mainService.query(function(data) {
        $scope.messages = data.value;
      },function(err){
      });
    }
    getMessages();

    var newMessage;
    $scope.openModalCreateMessage = function(messsage){
      console.log("teste");
      newMessage = messsage;

      $mdDialog.show({
        locals:{
          dataToPass: messsage
        }, 
        controller: controllerNewMessageModal,
        templateUrl: '../views/editmessage.html',
        clickOutsideToClose: true,
        fullscreen: $scope.customFullscreen
      }).then(function(answer) {
      }, function() {
      });
    };

    function controllerNewMessageModal($scope, $mdDialog) {

      if(newMessage) {
        $scope.message = newDisease;
      }

      $scope.saveMessage = function(message) {
        mainService.edit(message._id, message, function(data) {
          $scope.cancel();
          getMessages();
        }, function(err){
          console.log('error saving message',err);
        });
      };

      $scope.createNewMessage = function(message){
        mainService.save(message, function(data){
          $scope.cancel();
          getMessages();
        }, function(err){
          console.log(err);
        });
      };

      $scope.hide = function() {
        $mdDialog.hide();
      };
      $scope.cancel = function() {
        $mdDialog.cancel();
      };
      $scope.answer = function(answer) {
        getMessages();
        $mdDialog.hide(answer);
      };
    }

    $scope.removeMessage = function(message) {
      var title = 'Deseja remover a mensagem ' + message.description + '?';
      var windowRemoveMessage = $mdDialog.confirm()
        .title(title)
        .textContent('')
        .ariaLabel('Lucky day')
        .ok('Sim')
        .cancel('Não');

      $mdDialog.show(windowRemoveMessage).then(function() {
        mainService.delete(message._id, function(data){
          getMessages();
        }, function(err) {
          console.log('error removing messages', err);
        });

      }, function() {
      });
    };
});