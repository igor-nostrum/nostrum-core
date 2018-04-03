var app = angular.module('myApp', []);
app.controller('FormCtrl', function ($scope, $http) {
    $scope.submitForm = function() {
        console.log("posting data....");

        var req = {
            method: 'POST',
            url: '/public/user/add',
            headers: {
              'Content-Type': 'application/json'
            },
            data: {
                "user" : $scope.formData
            }
        };

        $http(req)
            .then(function successCallback(response) {
                $scope.message = 'OK';
            }, function errorCallback(response) {
                $scope.message = 'ERROR';
            })
            .catch(function (err) {
                console.log(err);
            });
    };
});