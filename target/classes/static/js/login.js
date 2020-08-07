(function (angular) {
    'use strict';
    angular.module('jd.login',[
        'ngRoute'
    ]).config(['$routeProvider',function($routeProvider) {
        $routeProvider.when('/login',{
            templateUrl: 'html/login.html',
            controller: 'loginCtrl'
        })
    }]).controller('loginCtrl', ['$scope','$http', function($scope,$http){
        $scope.user={
            account: '',
            password: ''
        };
        console.log($scope.user);
        $scope.wantlogin = function(){
            if($scope.user.account===""||$scope.user.password===""){
                alert("帐号密码不能为空！")
                return;
            }
            $scope.user_t={
                account: $scope.user.account,
                password: $scope.user.password
            };
            $http.post('/loginAction',$scope.user_t).then(function (data) {
                console.log("请求成功");


            }),function (err) {
                alert("登录失败！请检查帐号密码后再次尝试！");
            };
        }
    }]);
})(angular)