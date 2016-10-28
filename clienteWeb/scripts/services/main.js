'use strict';

angular.module('trabalhoSD')  
  .service('serviceVehicles', ['$http',
    function ($http) {
      //var ipServer = '';
      return {
        query: function (success, error) { 
          $http.get(ipServer).success(success).error(error);
        },
        get: function (success, error) {
          $http.get(ipServer).success(success).error(error);
        },
        edit: function (object, success, error) { //edit
          $http({
            method: "PUT",
            url: ipServer, 
            data: object, 
            cache: false
          }).success(success).error(error);
        },
        save: function (object, success, error) { //post
          $http({
            method: "POST",
            url: ipServer, 
            data: object, 
            cache: false
          }).success(success).error(error);
        },
        delete: function (success, error) { //delete
          $http.delete(ipServer).success(success).error(error);
        }
       };
    }
  ]);

