(function (angular) {
    'use strict';
    angular.module("jd",[
        'ngRoute',
        'jd.home',
        'jd.login',
        'jd.register'
    ]).config(['$routeProvider',function($routeProvider) {
        $routeProvider.otherwise({redirectTo:'/home'});
    }]).controller("indexCtrl",['$scope','$http',function ($scope,$http) {
        $scope.location='';
        //发送地址请求
        $http({
            url:'/getLocation',//验证表单的接口
            method:'post',
            headers:{'Content-Type':'application/json;charset=UTF-8'}, //将其变为 json 参数形式
        }).then(function successCallback(data) {
                $scope.location=data.data.location;
            //session
        }, function errorCallback(response) {
            $scope.location="江苏";
        });


        //
    }]);
})(angular)