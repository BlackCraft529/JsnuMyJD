(function (angular) {
    'use strict';
    angular.module('jd.login',[
        'ngRoute'
    ]).config(['$routeProvider','$qProvider',function($routeProvider,$qProvider) {
        $routeProvider.when('/login',{
            templateUrl: 'html/login.html',
            controller: 'loginCtrl'
        });
        $qProvider.errorOnUnhandledRejections(false);
    }]).controller('loginCtrl', ['$scope','$http', function($scope,$http){
        $scope.user={
            account: '',
            password: ''
        };
        $scope.wantlogin = function(){
            //帐号密码格式判断
            if($scope.user.account===""||$scope.user.password===""){
                alert("帐号密码不能为空！")
                return;
            }
            if($scope.user.account.length<4){
                alert("请起码输入4位用户名，11位手机号或者大于4位的邮箱地址");
                return;
            }
            if($scope.user.password<8){
                alert("密码不能少于8位");
                return;
            }
            //监听帐号密码长度不超过标准
            $scope.$watch('user.account',function(now,old) {
                if(now.length>30)
                    $scope.user.account=old;
            });
            $scope.$watch('user.password',function(now,old) {
                if(now.length>20)
                    $scope.user.password=old;
            });
            //发送登录请求
            // $scope.user_t= {params:{
            //     account: $scope.user.account,
            //     password: $scope.user.password
            // }};
            // console.log($scope.user_t);
            // $http.get('/loginAction',$scope.user_t).then(function (data) {
            //     console.log("请求成功");
            //
            //
            // }),function (err) {
            //     alert("登录失败！请检查帐号密码后再次尝试！");
            // };
            $http({
                url:'/loginAction',//验证表单的接口
                method:'post',
                data:{
                    account: $scope.user.account,
                    password: $scope.user.password
                },
                headers:{'Content-Type':'application/json;charset=UTF-8'}, //将其变为 json 参数形式
            }).success(function(data){
                console.log("请求成功");
                console.log(data);
            });


            //
        }


    }]);
})(angular)