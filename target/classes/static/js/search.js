(function (angular) {
    'use strict';
    angular.module('jd.search',[
        'ngRoute',
    ]).config(['$routeProvider','$qProvider',function($routeProvider,$qProvider) {
        $routeProvider.when('/search',{
            templateUrl: 'html/search.html',
            controller: 'searchCtrl'
        });
        $qProvider.errorOnUnhandledRejections(false);
    }]).controller('searchCtrl',['$scope','$http',function ($scope,$http) {

    }]);
})(angular);