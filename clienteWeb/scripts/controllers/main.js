angular.module("trabalhoSD").controller("mainController", function($scope, $mdDialog,  mainService, serviceShareType) {   

    $scope.type =0;
    $scope.selectType = function(type){
      $scope.type = type;
      console.log("serviceShareType.set: ", type);
      serviceShareType.set(type);
      $scope.messages = null;
      getMessages(type);
    };

    var getMessages = function(id) {
      mainService.get(id, function(data) {
        $scope.messages = data;
        console.log($scope.messages);
      },function(err){
      });
    }

    //Modal para mostrar mensagem random
    var auxtype;
    $scope.openModalRandomMessage = function(type){
      auxtype=type;
      $mdDialog.show({
        locals:{
          dataToPass: type
        }, 
        controller: controllerRandomMessageModal,
        templateUrl: '../views/randomMessage.html',
        clickOutsideToClose: true,
        fullscreen: $scope.customFullscreen
      }).then(function(answer) {
      }, function() {
      });
    };

    function controllerRandomMessageModal($scope, $mdDialog) {

      mainService.getRandom(auxtype, function(data) {
        $scope.randomMessage = data;
        console.log(data);
      }, function(err){
        console.log('error saving message',err);
      });

      $scope.answer = function(answer) {
        $mdDialog.hide(answer);
      };
    }

    //Modal para editar e criar novas mensagens
    var newMessage;
    $scope.openModalCreateMessage = function(messsage){
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

    //Controlador da modalf
    function controllerNewMessageModal($scope, $mdDialog) {

      $scope.type = serviceShareType.get();

      if(newMessage) {
        $scope.message = newMessage;
      }

      $scope.saveMessage = function(message) {
        mainService.edit(message, message._id, function(data) {
          $scope.cancel();
          getMessages($scope.type);
        }, function(err){
          console.log('error saving message',err);
        });
      };

      $scope.createNewMessage = function(message){
        message.type = $scope.type;
        mainService.save(message, function(data){
          $scope.cancel();
          getMessages($scope.type);
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
        getMessages($scope.type);
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
          getMessages($scope.type);
        }, function(err) {
          console.log('error removing messages', err);
        });

      }, function() {
      });
    };

    $scope.search = function(id){
      mainService.getId(id, function(data){
        $scope.message = data;
        console.log(data);
      }, function(err) {
        console.log('error removing messages', err);
      });
    }
});