let app = angular.module('app', ['ngRoute', 'ngStorage']);
let contextPath = 'http://localhost:8189/market'

app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'homepage.html'
        })
        .when('/products', {
            templateUrl: 'products.html',
            controller: 'productsController'
        })
        .when('/edit', {
            templateUrl: 'edit.html',
            controller: 'cartScriptCtrl'
        })
});


app.controller('productsController', function ($scope, $http, $localStorage) {
    if ($localStorage.currentUser) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.currentUser.token;
    }

    fillTable = function () {
        $http.get(contextPath + '/api/v1/products')
            .then(function (response) {
                $scope.Products = response.data;
            })
    }

    fillTable();
});

app.controller('mainController', function ($scope, $http, $localStorage) {
    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function (response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.currentUser = { username: $scope.user.username, token: response.data.token };
                }
            });
    };

    $scope.tryToLogout = function () {
        delete $localStorage.currentUser;
        $http.defaults.headers.common.Authorization = '';
    };
});

app.controller('addOrEditProductController', function ($scope, $http, $routeParams, $localStorage) {
    const advertsPath = contextPath + '/api/v1/products';

    if ($localStorage.currentUser) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.currentUser.token;
    }

    if ($routeParams.id != null) {
        $http.get(advertsPath + '/' + $routeParams.id).then(function (response) {
            $scope.productFromForm = response.data;
            console.log($scope.productFromForm);
        });
    }

    $scope.createOrUpdateProduct = function() {
        if($scope.productFromForm.id == null) {
            $http.post(contextPath + '/api/v1/products', $scope.productFromForm).then(function (response) {
                console.log(response);
                window.location.href = contextPath + '/index.html#/shop';
                window.location.reload(true);
            });
        } else {
            $http.put(contextPath + '/api/v1/products', $scope.productFromForm).then(function (response) {
                console.log(response);
                window.location.href = contextPath + '/index.html#/shop';
                window.location.reload(true);
            });
        }
    };
});