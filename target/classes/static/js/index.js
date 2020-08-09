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
        $scope.ip='';
        //获取ip
        $http({
            method: "get",
            url:"http://api.ipify.org/?format=json&callback=JSON_CALLBACK",
            headers:{'Content-Type':'application/json;charset=UTF-8'},
        }).then(function(data){
            if(data.data.ip==='')
                $scope.location='江苏';
            else{//发送地址请求
                console.log(data.data);
                $http({
                    url:'/getLocation',//验证表单的接口
                    method:'post',
                    data: data.data,
                    headers:{'Content-Type':'application/json;charset=UTF-8'}, //将其变为 json 参数形式
                }).then(function successCallback(data) {
                    $scope.location=data.data.location;
                    console.log(data.data);
                }, function errorCallback(response) {
                    $scope.location="江苏";
                });
            }
        }),function(err){
            console.log("err:"+err);
            $scope.location='江苏';
        };





        //
    }]);
})(angular)