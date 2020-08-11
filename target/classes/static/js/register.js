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
    }]).controller('registerCtrl', ['$scope','$http','$window', function($scope,$http,$window){
        $scope.info={
            name: '',
            password1: '',
            password2: '',
            phone: '',
            mail: '',
            name_status: false,
            password_status: false,
            phone_status: false,
            email_status: false,
        };
        $scope.user={
            name: '',
            password1: '',
            password2: '',
            phone: '',
            email: '',
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
            }else {
                $scope.info.name="用户名格式正确";
            }
             if(now.length>20)
                 $scope.user.name=old;


        });
        $scope.judgeName=function(){
            if($scope.user.name.length<4)
                return;
            //发送姓名是否存在请求
            $http({
                url:'/getName',//验证表单的接口
                method:'post',
                data: {
                    'name' :$scope.user.name
                },
                headers:{'Content-Type':'application/json;charset=UTF-8'}, //将其变为 json 参数形式
            }).then(function successCallback(data) {
                if(!data.data){
                    $scope.info.name="当前用户名可用";
                    $scope.info.name_status=true;
                }
                else {
                    $scope.info.name="当前用户名已被占用";
                    $scope.info.name_status=false;
                    return;
                }
            }, function errorCallback(response) {
                alert("error!\n"+"error message:"+response);
                $scope.info.name="出现连接错误";
                $scope.info.name_status=false;
            });

        };
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
            if(now.length<8){
                $scope.info.password2="请起码输入8位字符";
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
            if(now.length==11)
                $scope.info.phone="手机号格式正确";
        });
        $scope.judgePhone=function(){
            if($scope.user.phone.length<11)
                return;
            //发送手机是否存在请求
            $http({
                url:'/getPhone',//验证表单的接口
                method:'post',
                data: {
                    'phone' :$scope.user.phone
                },
                headers:{'Content-Type':'application/json;charset=UTF-8'}, //将其变为 json 参数形式
            }).then(function successCallback(data) {
                if(!data.data){
                    $scope.info.phone="当前手机号可用";
                    $scope.info.phone_status=true;
                }
                else {
                    $scope.info.phone="当前手机号已被占用";
                    $scope.info.phone_status=false;
                }
            }, function errorCallback(response) {
                alert("error!\n"+"error message:"+response);
                $scope.info.phone="出现连接错误";
                $scope.info.phone_status=false;
            });
        };
        //邮箱监听
        $scope.$watch("user.email",function (now, old) {
            if(now===''){
                $scope.info.email="请输入正确的邮箱地址";
                $scope.info.email_status=false;
                return;
            }
            if(now.length<6){
                $scope.info.email="邮箱地址过短";
                $scope.info.email_status=false;
                return;
            }
            if(!mailFormat(now)){
                $scope.info.email="邮箱内只能输入一个@且后面必须跟随内容";
                $scope.info.email_status=false;
                return;
            }else {
                $scope.info.email="邮箱格式正确";
            }
        });

        $scope.judgeMail=function() {
            if ($scope.user.email.length < 6)
                return;
            if(!mailFormat($scope.user.email))
                return;
            //发送邮箱是否存在请求
            $http({
                url: '/getMail',//验证表单的接口
                method: 'post',
                data: {
                    'mail': $scope.user.email
                },
                headers: {'Content-Type': 'application/json;charset=UTF-8'}, //将其变为 json 参数形式
            }).then(function successCallback(data) {
                if (!data.data) {
                    $scope.info.email = "当前邮箱可用";
                    $scope.info.email_status = true;
                } else {
                    $scope.info.email = "当前邮箱已被占用";
                    $scope.info.email_status = false;
                }
            }, function errorCallback(response) {
                alert("error!\n" + "error message:" + response);
                $scope.info.email = "出现连接错误";
                $scope.info.email_status = false;
            });
        }
        //----------------------------------提交----------------------------------
        $scope.registUser=function () {
            if(!$scope.info.name_status&& !$scope.info.password_status && !$scope.info.phone_status && !$scope.info.email_status)
                return;
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

                //session
                sessionStorage.setItem("uuid", data.data.uuid);
                sessionStorage.setItem("password", data.data.password);
                sessionStorage.setItem("name", data.data.name);
                sessionStorage.setItem("phone", data.data.phone);
                sessionStorage.setItem("avatar", data.data.avatar);
                sessionStorage.setItem("email", data.data.email);
                sessionStorage.setItem("lastLoginTime", data.data.lastLoginTime);
                sessionStorage.setItem("registerTime", data.data.registerTime);
                sessionStorage.setItem("retailer", data.data.retailer);
                alert("恭喜您注册成功！页面即将跳转，如果没有跳转请手动刷新！");
                $window.location.reload();
            }, function errorCallback(response) {
                alert("error!\n"+"error message:"+response);
            });
        };
        //使用函数
        var mailFormat=function (now) {
            var count=0;
            for(var i=0;i<now.length;i++){
                if(now.charAt(i)==="@")
                    count++;
            }
            if(count!=1)
                return false;
            if(now.charAt(now.length-1)==="@")
                return false;
            return true;
        }

        //
    }]);
})(angular)