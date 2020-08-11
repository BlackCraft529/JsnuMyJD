(function (angular) {
    'use strict';
    angular.module("jd.userinfo",[
        'ngRoute'
    ]).config(['$routeProvider','$qProvider',function($routeProvider,$qProvider) {
        $routeProvider.when('/userinfo',{
            templateUrl: 'html/userinfo.html',
            controller: 'userinfoCtrl'
        });
        $qProvider.errorOnUnhandledRejections(false);
    }]).controller("userinfoCtrl",['$scope','$http',function ($scope,$http) {

    }]);
})(angular)