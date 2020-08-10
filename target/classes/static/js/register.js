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
            password1: '',
            password2: '',
            phone: '',
            mail: '',
            name_status: false,
            password_status: false,
            phone_status: false,
            mail_status: false,
        };
        $scope.user={
            name: '',
            password1: '',
            password2: '',
            phone: '',
            mail: '',
        };
        //用户名监听
        $scope.$watch('user.name',function (now,old) {
            if(now===''){
                $scope.info.name="4-20个字符，可以留空";
                $scope.info.name_status=false;
                return;
            }
            if(now.length<4){
                $scope.info.name="用户名太短";
                $scope.info.name_status=false;
                return;
            }
             if(now.length>20)
                 $scope.user.name=old;
            if(!nameExist(now)){
                $scope.info.name="当前用户名可用";
                $scope.info.name_status=true;
                return;
            }
            else{
                $scope.info.name="当前用户名已被占用";
                $scope.info.name_status=false;
                return;
            }

        });
        //密码监听
        $scope.$watch('user.password1',function (now,old) {
            if(now===""){
                $scope.info.password1="建议使用字母、数字和符号两种及以上的组合，8-20个字符";
                $scope.info.password_status=false;
                return;
            }
            if(now.length<8){
                $scope.info.password1="请起码输入8位字符";
                $scope.info.password_status=false;
                return;
            }
            if(now.length>20)
                $scope.user.password1=old;
            else {
                $scope.info.password1="当前密码可用";
            }

        });
        $scope.$watch('user.password2',function (now,old) {
            if(now===""){
                $scope.info.password2="请再次输入密码";
                $scope.info.password_status=false;
                return;
            }
            if(now.length>20)
                $scope.user.password2.old;
            if($scope.user.password2!==$scope.user.password1){
                $scope.info.password2="两次密码不一致";
                $scope.info.password_status=false;
                return;
            }else {
                $scope.info.password2="二次密码正确";
                $scope.info.password_status=true;
            }
        });
        //手机监听
        $scope.$watch("user.phone",function (now,old) {
            if(now.length==1){
                if(now.charAt(0)<'0'||now.charAt(0)>'9')
                    $scope.user.phone="";
            }
            else if(now.charAt(now.length-1)<'0'||now.charAt(now.length-1)>'9'){
                $scope.user.phone=old;
            }
            if(now=="" || now.length<11){
                $scope.info.phone="请输入11位手机号";
                $scope.info.phone_status=false;
                return;
            }
            if(now.length>11)
                $scope.user.phone=old;
            if(!phoneExist(now)){
                $scope.info.phone="当前手机号可用";
                $scope.info.phone_status=true;
                return;
            }else {
                $scope.info.phone="当前手机号已被占用";
                $scope.info.phone_status=false;
                return;
            }

        });
        //邮箱监听
        $scope.$watch("user.mail",function (now, old) {
            if(now===''){
                $scope.info.mail="请输入正确的邮箱地址";
                $scope.info.mail_status=false;
                return;
            }
            if(now.length <6){
                $scope.info.mail="邮箱地址过短";
                $scope.info.mail_status=false;
                return;
            }
            var count=0;
            for(var i=0;i<now.length;i++){
                if(now.charAt(i)==="@")
                    count++;
            }
            if(count!=1){
                $scope.info.mail="邮箱内请输入@且只能输入一个@";
                $scope.info.mail_status=false;
                return;
            }
            if(!mailExist(now)){
                $scope.info.mail="当前邮箱可用";
                $scope.info.mail_status=true;
                return;
            }else {
                $scope.info.mail="当前邮箱已被占用";
                $scope.info.phone_status=false;
                return;
            }
        });
        //----------------------------------提交----------------------------------
        $scope.registUser=function () {
            console.log(sessionStorage.getItem("password"));
            if(!$scope.info.name_status&& !$scope.info.password_status && !$scope.info.phone_status && !$scope.info.mail_status)
                return;
            console.log("注册了");
            //发送注册请求
            $http({
                url:'/registerUser',//验证表单的接口
                method:'post',
                data:$scope.user,
                headers:{'Content-Type':'application/json;charset=UTF-8'}, //将其变为 json 参数形式
            }).then(function successCallback(data) {
                if (data.data.uuid===null){
                    alert("注册失败！请检查帐号密码后再次尝试！");
                    return;
                }
                alert("注册成功");
                //session
            }, function errorCallback(response) {
                alert("error!\n"+"error message:"+response);
            });
        };
        //使用函数
        function nameExist(name) {
            //发送用户名是否存在请求
            $http({
                url:'/getName',//验证表单的接口
                method:'post',
                data: {
                    'name' :name
                },
                headers:{'Content-Type':'application/json;charset=UTF-8'}, //将其变为 json 参数形式
            }).then(function successCallback(data) {
                  return data.data;
            }, function errorCallback(response) {
                alert("error!\n"+"error message:"+response);
                return true;
            });
        }
        function phoneExist(phone) {
            //发送手机是否存在请求
            $http({
                url:'/getPhone',//验证表单的接口
                method:'post',
                data: {
                    'phone' :phone
                },
                headers:{'Content-Type':'application/json;charset=UTF-8'}, //将其变为 json 参数形式
            }).then(function successCallback(data) {
                return data.data;
            }, function errorCallback(response) {
                alert("error!\n"+"error message:"+response);
                return true;
            });
        }
        function mailExist(mail) {
            //发送手机是否存在请求
            $http({
                url:'/getMail',//验证表单的接口
                method:'post',
                data: {
                    'mail' :mail
                },
                headers:{'Content-Type':'application/json;charset=UTF-8'}, //将其变为 json 参数形式
            }).then(function successCallback(data) {
                return data.data;
            }, function errorCallback(response) {
                alert("error!\n"+"error message:"+response);
            });
        }
        //
    }]);
})(angular)