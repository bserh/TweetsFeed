var App = angular.module('App', ['ngRoute', 'ngResource']);

App.config(function ($routeProvider, $httpProvider) {
    //specify concrete route in system
    $routeProvider.when('/',
        {
            templateUrl: '/index.html',
            controller: 'TweetsController',
            controllerAs: 'controller'
        }
    ).otherwise({
        redirectTo: '/'
    });

    //to simplify that project it uses simple http provider instead of the 'restangular' module
    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    $httpProvider.defaults.headers.common["Access-Control-Allow-Origin"] = '*';
});