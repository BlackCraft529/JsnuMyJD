(function (angular) {
    'use strict';
    var app=angular.module("jd",[
        'ngRoute',
        'jd.home',
        'jd.login',
        'jd.register',
        'jd.userinfo',
        'jd.search',
    ]);
    app.config(['$routeProvider',function($routeProvider) {
        $routeProvider.otherwise({redirectTo:'/home'});
    }]);
    app.run(['$rootScope', '$location', function($rootScope, $location) {
        /* 监听路由的状态变化 */
        $rootScope.$on('$routeChangeStart', function(evt, next, current){
            // console.log('route begin change');//开始变化
        });
        $rootScope.$on('$routeChangeSuccess', function(evt, current, previous) {
            // console.log('route have already changed ：'+$location.path());//变化结束
            //如果登录过后访问登录/注册
            if(($location.path()==='/login'||$location.path()==="/register")&&sessionStorage.getItem("uuid")!=null){
                $location.path("/home");
                return;
            }
            if($location.path()==="/userinfo" && sessionStorage.getItem("uuid")===null){
                $location.path("/login");
                return;
            }
        });
    }]);
    app.controller("indexCtrl",['$scope','$http','$window',function ($scope,$http,$window) {
        $scope.location='';
        $scope.ip='';
        $scope.user={
            uuid:'',
            password:'',
            name:'',
            phone:'',
            avatar:'',
            address: '',
            email: '',
            lastLoginTime:'',
            registerTime:'',
            retailer: '',
            status: false,
        };
        //获取ip
        $http({
            method: "get",
            url:"http://api.ipify.org/?format=json&callback=JSON_CALLBACK",
            headers:{'Content-Type':'application/json;charset=UTF-8'},
        }).then(function(data){
            if(data.data.ip==='')
                $scope.location='江苏';
            else{//发送获得地域请求
                $http({
                    url:'/getLocation',//验证表单的接口
                    method:'post',
                    data: data.data,
                    headers:{'Content-Type':'application/json;charset=UTF-8'}, //将其变为 json 参数形式
                }).then(function successCallback(data) {
                    $scope.location=data.data.location;
                }, function errorCallback(response) {
                    $scope.location="江苏";
                });
            }
        }),function(err){
            console.log("err:"+err);
            $scope.location='江苏';
        };

        if (sessionStorage.getItem("uuid")!==null){
            $scope.user.uuid=sessionStorage.getItem("uuid");
            $scope.user.password=sessionStorage.getItem("password");
            $scope.user.name=sessionStorage.getItem("name");
            $scope.user.phone=sessionStorage.getItem("phone");
            $scope.user.avatar=sessionStorage.getItem("avatar");
            $scope.user.address=sessionStorage.getItem("address");
            $scope.user.email=sessionStorage.getItem("email");
            $scope.user.lastLoginTime=sessionStorage.getItem("lastLoginTime");
            $scope.user.registerTime=sessionStorage.getItem("registerTime");
            $scope.user.retailer=sessionStorage.getItem("retailer");
            $scope.user.status=true;
        }else {
            $scope.user.uuid='';
            $scope.user.password='';
            $scope.user.name='';
            $scope.user.phone='';
            $scope.user.avatar='';
            $scope.user.address='';
            $scope.user.email='';
            $scope.user.lastLoginTime='';
            $scope.user.registerTime='';
            $scope.user.retailer='';
            $scope.user.status=false;
        }
        //注销
        $scope.logout=function () {
            sessionStorage.clear();
            $window.location.reload();
        };
        //
    }]);
})(angular)