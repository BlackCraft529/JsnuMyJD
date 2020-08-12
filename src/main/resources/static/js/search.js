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
    }]).controller('searchCtrl',['$scope','$http','$routeParams',function ($scope,$http,$routeParams) {
        $scope.search_key=$routeParams.search_key;
        console.log($scope.search_key);
        $http({
            url:'/getSomething',//验证表单的接口
            method:'post',
            data:{
                "search_key" : $scope.search_key,
            },
            headers:{'Content-Type':'application/json;charset=UTF-8'}, //将其变为 json 参数形式
        }).then(function successCallback(data) {
            console.log(data);
        }, function errorCallback(response) {
            alert("error!\n"+"error message:"+response);
        });
    }]);
})(angular);