(function (angular) {
    'use strict';
    angular.module('jd.cart',[
        'ngRoute',
    ]).config(['$routeProvider','$qProvider',function($routeProvider,$qProvider) {
        $routeProvider.when('/cart',{
            templateUrl: 'html/cart.html',
            controller: 'cartCtrl'
        });
        $qProvider.errorOnUnhandledRejections(false);
    }]).controller('cartCtrl',['$scope','$http','$routeParams',function ($scope,$http,$routeParams) {

    }]);
})(angular);