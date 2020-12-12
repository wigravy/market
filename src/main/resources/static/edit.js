let app = angular.module('app', []);
let contextPath = 'http://localhost:8189/market'

app.controller('cartScriptCtrl', function ($scope, $http) {
    $scope.create = () => {
        $http({
            method: 'POST',
            url: 'http://localhost:8189/market/api/v1/products',
            headers: {
                'Content-Type': 'application/json'
            },
            data: $scope.Product
        }).then(function success(response) {
           window.location.href = contextPath + '/index.html';
           window.location.reload(true);
        }, function error(response) {
            console.error(response);
        });
    };
});