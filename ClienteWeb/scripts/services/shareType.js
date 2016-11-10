'use strict';
 
angular.module('trabalhoSD')
  .factory('serviceShareType',  function() {
    var type;
    return  {
      get: function(){
        return type;
      },
      set: function(aux){
        type = aux;
      }
    }
  });