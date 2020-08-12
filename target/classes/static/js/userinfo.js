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
        $scope.tempUser={
            password1:'',
            password2:'',
            name:'',
            phone:'',
            email: '',
            avatar:'',
            address: '',
        };
        $scope.editInfo=false;
        if (sessionStorage.getItem("uuid")!==null){
            $scope.user.uuid=sessionStorage.getItem("uuid");
            $scope.user.password=sessionStorage.getItem("password");
            $scope.user.name=sessionStorage.getItem("name");
            $scope.user.phone=sessionStorage.getItem("phone");
            $scope.user.avatar=sessionStorage.getItem("avatar");
            $scope.user.email=sessionStorage.getItem("email");
            $scope.user.address=sessionStorage.getItem("address");
            $scope.user.lastLoginTime=sessionStorage.getItem("lastLoginTime");
            $scope.user.registerTime=sessionStorage.getItem("registerTime");
            $scope.user.retailer=sessionStorage.getItem("retailer");
            $scope.user.status=true;

            $scope.tempUser.name=$scope.user.name;
            $scope.tempUser.phone=$scope.user.phone;
            $scope.tempUser.email=$scope.user.email;
            if($scope.user.address!=="null")
                $scope.tempUser.address=$scope.user.address;
            if($scope.user.avatar!=="未设置")
                $scope.tempUser.avatar=$scope.user.avatar;
        }
        //进入个人信息编辑装填
        $scope.editIt=function () {
            $scope.editInfo=true;
        };
        //保存信息
        $scope.change=function () {

        };
        //取消
        $scope.cancelEdit=function () {
            $scope.tempUser.name=$scope.user.name;
            $scope.tempUser.phone=$scope.user.phone;
            $scope.tempUser.email=$scope.user.email;
            $scope.tempUser.password1='';
            $scope.tempUser.password2='';
            if($scope.user.address==="null")
                $scope.tempUser.address='';
            else $scope.tempUser.address=$scope.user.address;
            if($scope.user.avatar==="未设置")
                $scope.tempUser.avatar='';
            else $scope.tempUser.avatar=$scope.user.avatar;
            $scope.editInfo= false;
        };
        //是否是卖家
        $scope.ifRetailer=function () {
            if($scope.user.retailer==='true')
                return true;
            return false;
        };
        //发布商品
        $scope.releaseGoods=function () {

        };
        //
    }]);
})(angular)