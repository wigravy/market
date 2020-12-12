let route = angular.module('route', ['ngRoute']);

route.config(function ($routeProvider) {
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