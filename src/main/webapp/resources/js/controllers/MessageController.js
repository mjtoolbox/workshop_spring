/**
 * Created by mijo on 2015-06-23.
 */
angular.module('myApp')
.controller('MessageController', ['$scope', '$http', function ($scope, $http) {
        $http.get('spring/service/messages').success(function (data) {
            $scope.messageEvents = data;
        });
}]);