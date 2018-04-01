var app = angular.module('myApp', []);
app.controller('FormCtrl', function ($scope, $http) {
    $scope.submitForm = function() {
        console.log("posting data....");
        $http.post('/public/user/add', JSON.stringify($scope.formData))
            .then(function(success) {
                if (success) {
                    $scope.message = 'OK';
                } else {
                    $scope.message = 'ERROR';
                }
            }).catch(function (err) {
                console.log(err);
            });
    };
});