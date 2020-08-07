(function (angular) {
    'use strict';
    angular.module("jd",[
        'ngRoute',
        'jd.home',
        'jd.login',
        'jd.register'
    ]).config(['$routeProvider',function($routeProvider) {
        $routeProvider.otherwise({redirectTo:'/home'})
    }]);
})(angular)