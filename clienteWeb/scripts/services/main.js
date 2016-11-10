'use strict';

angular.module('trabalhoSD')  
  .service('mainService', ['$http',
    function ($http) {
      var ipServer = 'http://localhost:8080/ServidorWeb/webresources/comunicacaoWS';
      return {
        getId: function (id, success, error) {
          $http.get(ipServer + /messages/ + id ).success(success).error(error);
        },
        getRandom: function (type, success, error) {
          $http.get(ipServer + '/messages/radom/' + type ).success(success).error(error);
        },
        get: function (id, success, error) {
          $http.get(ipServer + '/messages/type/' + id ).success(success).error(error);
        },
        edit: function (message, messageId, success, error) { //edit
          $http({
            method: "PUT",
            url: ipServer + '/messages/' + messageId, 
            data: message, 
            cache: false
          }).success(success).error(error);
        },
        save: function (object, success, error) { //post
          $http({
            method: "POST",
            url: ipServer + '/messages', 
            data: object, 
            cache: false
          }).success(success).error(error);
        },
        delete: function (idMessage,success, error) { //delete
          console.log(idMessage);
          $http.delete(ipServer+'/messages/'+idMessage).success(success).error(error);
        }
       };
    }
  ]);

