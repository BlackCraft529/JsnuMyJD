(function (angular) {
    'use strict';
    angular.module('jd.home',[
        'ngRoute'
    ]).config(['$routeProvider',function($routeProvider) {
        $routeProvider.when('/home',{
            templateUrl: 'html/home.html',
            controller: 'homeCtrl'
        })
    }]).controller('homeCtrl', ['$scope', function($scope){
        console.log("home跳转成功");
    }]);
})(angular)