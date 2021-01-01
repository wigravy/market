angular.module('app').controller('createOrderController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/market';

    fillTable = function () {
        $http.get(contextPath + '/api/v1/cart')
            .then(function (response) {
                $scope.CartList = response.data;
            });
    };

    fillTable();

    $scope.confirmOrder = function () {
        $http({
            url: contextPath + '/api/v1/order/confirm',
            method: 'POST',
            params: {address: $scope.order.address}
        }).then(function (response) {
            $location.path('/order_created')
        })
    }

});