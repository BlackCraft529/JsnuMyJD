(function (angular) {
    'use strict';
    angular.module('jd.register',[
        'ngRoute'
    ]).config(['$routeProvider','$qProvider',function($routeProvider,$qProvider) {
        $routeProvider.when('/register',{
            templateUrl: 'html/register.html',
            controller: 'registerCtrl'
        });
        $qProvider.errorOnUnhandledRejections(false);
    }]).controller('registerCtrl', ['$scope','$http', function($scope,$http){
        $scope.info={
            name: '',
            password: '',
            phone: '',
            mail: '',
            status: false
        };
        $scope.user={
            name: '',
            password: '',
            phone: '',
            mail: ''
        };
        //用户名监听
        $scope.$watch('user.name',function (now,old) {
            if(now===''){
                $scope.info.name="4-20个字符，可以留空";
                $scope.info.status=true;
                return;
            }
            if(now.length<4){
                $scope.info.name="用户名太短";
                $scope.info.status=false;
                return;
            }
             if(now.length>20){
                 $scope.user.name=old;
                 $scope.info.status=false;
                 return;
             }
            if(nameExist(now,$http)){
                $scope.info.name="当前用户名可用";
                $scope.info.status=true;
                return;
            }
            else{
                $scope.info.name="当前用户名已被占用";
                $scope.info.status=false;
                return;
            }

            function nameExist(name,$http) {
                console.log(name);
                $scope.info_n={params:{
                  name: name
                }};
                $http.post('/getName',$scope.info_n).then(function (data) {
                    console.log("请求成功");
                    return true;
                }),function (err) {
                    return false;
                };
            }
        });
        //密码监听
        //手机监听
        //邮箱监听
        //提交

        $scope.registUser=function () {
            if(!$scope.info.status)
                return;
            $http.post('/registerUser',$scope.user).then(function (data) {
                console.log("注册成功");
            }),then(function (err) {
                console.log("注册失败");
            });
        };
        //
    }]);
})(angular)