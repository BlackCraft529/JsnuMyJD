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
            cartList: '',
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
        $scope.goodsInfo={
            name:'',
            price:'',
            desc:'',
            Sell_amount:'',
            cate:'',
            image:'',
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
            //用户名判断
            if($scope.tempUser.name.length<4||$scope.tempUser.name.length>20){
                alert("用户名必须4-20个字符，可以留空");
                return;
            }
            $http({
                url:'/getName',//验证表单的接口
                method:'post',
                data: {
                    'name' :$scope.tempUser.name.length
                },
                headers:{'Content-Type':'application/json;charset=UTF-8'}, //将其变为 json 参数形式
            }).then(function successCallback(data) {
                if(data.data){
                    alert("当前用户名已被占用");
                    return;
                }
            }, function errorCallback(response) {
                alert("error!\n"+"error message:"+response);
                return
            });
            //密码判断
            if($scope.tempUser.password1!==""&&($scope.tempUser.password1.length<8||$scope.tempUser.password1.length>20)){
                alert("密码必须8-20个字符，可以留空，留空则不修改");
                return;
            }
            if($scope.tempUser.password1!==$scope.tempUser.password2){
                alert("两次密码不一致，请核实后重新输入");
                return;
            }
            //手机号判断
            if($scope.tempUser.phone.length===''){
                alert("手机号不允许为空");
                return;
            }
            if($scope.tempUser.phone.length<11){
                alert("手机号需要11位");
                return;
            }
            for(var i=0;i<11;i++){
                if($scope.tempUser.phone.charAt(i)<'0'||$scope.tempUser.phone.charAt(i)>'9'){
                    alert("手机号只允许输入数字");
                    return;
                }
            }
            $http({
                url:'/getPhone',//验证表单的接口
                method:'post',
                data: {
                    'phone' :$scope.tempUser.phone
                },
                headers:{'Content-Type':'application/json;charset=UTF-8'}, //将其变为 json 参数形式
            }).then(function successCallback(data) {
                if(data.data){
                    alert("当前手机号已被占用");
                    return;
                }
            }, function errorCallback(response) {
                alert("error!\n"+"error message:"+response);
                return;
            });
            //邮箱判断
            if($scope.tempUser.email===""){
                alert("邮箱不允许为空");
                return;
            }
            if($scope.tempUser.email.length<6 ||$scope.tempUser.email.length>50){
                alert("密码必须6-50个字符");
                return;
            }
            var count=0;
            for(var i=0;i<$scope.tempUser.email.length;i++){
                if($scope.tempUser.email.charAt(i)==="@")
                    count++;
            }
            if(count!=1){
                alert("邮箱中不允许出现多个'@'符号")
                return ;
            }
            if($scope.tempUser.email.charAt($scope.tempUser.email.length-1)==="@"){
                alert("请在@后输入内容");
                return ;
            }
            $http({
                url: '/getMail',//验证表单的接口
                method: 'post',
                data: {
                    'mail': $scope.tempUser.email
                },
                headers: {'Content-Type': 'application/json;charset=UTF-8'}, //将其变为 json 参数形式
            }).then(function successCallback(data) {
                console(data);
                if (!data.data) {
                    alert("当前邮箱已被占用");
                    return;
                }
            }, function errorCallback(response) {
                alert("error!\n" + "error message:" + response);
                return;
            });
            //发送修改请求
            $http({
                url: '/editInfomation',
                method: 'post',
                data: {
                    uuid: $scope.user.uuid,
                    name: $scope.tempUser.name,
                    password: $scope.tempUser.password1,
                    phone: $scope.tempUser.phone,
                    email:  $scope.tempUser.email,
                    avatar: $scope.tempUser.avatar,
                    address: $scope.tempUser.address,
                },
                headers :{'Content-Type': 'application/json;charset=UTF-8'},
            }).then(function successCallBack(data) {
                if(data.data){
                    alert("修改成功！请重新登录！");
                    sessionStorage.clear();
                    $window.location.reload();
                }else {
                    alert("修改失败，请重试！")
                }
            }),function errorCallBack(err) {
                alert("error!\n"+"error message:"+err);
            };
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
            //商品名
            if($scope.goodsInfo.name.length<3){
                alert("商品名称起码要有3位")
                return;
            }
            //商品价格
            if($scope.goodsInfo.price===""){
                alert("请输入商品价格");
                return;
            }
            var count=0;
            for(var i=0;i<$scope.goodsInfo.price.length;i++){
                if($scope.goodsInfo.price.charAt(i)==='.'){
                    count++;
                    if(count>1){
                        alert("只能输入一个小数点");
                        return;
                    }
                }else if($scope.goodsInfo.price.charAt(i)<'0'||$scope.goodsInfo.price.charAt(i)>'9'){
                    alert("商品价格请勿输入数字以及小数点之外的内容！")
                    return;
                }
            }
            //商品分类

            //商品数量
            //商品图片链接
            //商品描述

        };
        //清空商品内容
        $scope.clearGoodsInfo=function () {
            $scope.goodsInfo.name='';
            $scope.goodsInfo.price='';
            $scope.goodsInfo.desc='';
            $scope.goodsInfo.Sell_amount='';
            $scope.goodsInfo.cate='';
            $scope.goodsInfo.image='';

            $http({
                url: '/getCartList',
                method: 'post',
                data: {
                    "uuid" : $scope.user.uuid,
                },
                headers :{'Content-Type': 'application/json;charset=UTF-8'},
            }).then(function successCallBack(data) {
               console.log(data.data);
                console.log(12131);
            }),function errorCallBack(err) {
                alert("error!\n"+"error message:"+err);
            }
        };
        //
    }]);
})(angular)