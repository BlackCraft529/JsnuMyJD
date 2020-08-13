(function (angular) {
    'use strict';
    angular.module('jd.cart',[
        'ngRoute',
    ]).config(['$routeProvider','$qProvider',function($routeProvider,$qProvider) {
        $routeProvider.when('/cart',{
            templateUrl: 'html/cart.html',
            controller: 'cartCtrl'
        });
        $qProvider.errorOnUnhandledRejections(false);
    }]).controller('cartCtrl',['$scope','$http','$location','$window',function ($scope,$http,$location,$window) {
        $scope.user={
            uuid:'',
            search_key:'',
            cartList: 0,
            selectMoney: 0,
            selectNum: 0,
        };
        $scope.cart=[];
        $scope.selectList=[];
        if(sessionStorage.getItem("uuid")!=null){
            $scope.user.uuid=sessionStorage.getItem("uuid");
        }
        //获取购物车
        $http({
            url: '/getCartList',
            method: 'post',
            data: {
                "uuid" : $scope.user.uuid,
            },
            headers :{'Content-Type': 'application/json;charset=UTF-8'},
        }).then(function successCallBack(data) {
            $scope.cart=data.data.shopCartGoods;
            for(var i=0;i<data.data.shopCartGoods.length;i++){
                $scope.user.cartList+=data.data.shopCartGoods[i].shopCartAmount;
            }
            console.log( $scope.cart);
            console.log( $scope.cart[1].goods.image);
        }),function errorCallBack(err) {
            alert("error!\n" + "error message:" + err);
        };
        //搜索
        $scope.iWantIt=function () {
            if($scope.user.search_key==='')
                return;
            $location.path('/search').search({"search_key":$scope.user.search_key});
        };
        //一键全选
        $scope.selectAll=function($event){
            //清空
            $scope.selectList.splice(0,$scope.selectList.length);
            $scope.user.selectNum=0;
            $scope.user.selectMoney=0;
            if($event.target.checked){
                for(var i=0;i<$scope.cart.length;i++){
                    $scope.selectList.push($scope.cart[i].goods.id);
                    $scope.user.selectNum+=$scope.cart[i].shopCartAmount;
                    $scope.user.selectMoney+=$scope.cart[i].shopCartAmount * $scope.cart[i].goods.sellPrice;
                }
            }
        };
        //选择一些内容
        $scope.select=function ($event) {
            // console.log($event.target.checked);
            if($event.target.checked){
                $scope.selectList.push(this.item.goods.id);
                $scope.user.selectMoney+=this.item.shopCartAmount * this.item.goods.sellPrice;
                $scope.user.selectNum+=this.item.shopCartAmount;
            }else {
                // console.log( $scope.selectList.splice(this.item.goods.id,1));
                $scope.selectList.splice(this.item.goods.id,1);
                $scope.user.selectMoney-=this.item.shopCartAmount * this.item.goods.sellPrice;
                $scope.user.selectNum-=this.item.shopCartAmount;
            }
        };
        //删除某个内容
        $scope.delOne=function () {
            console.log(this);
            $http({
                url: '/delSome',
                method: 'post',
                data: {
                    "uuid" : $scope.user.uuid,
                    "goods_id[]": this.item.goods.id,
                },
                headers :{'Content-Type': 'application/json;charset=UTF-8'},
            }).then(function successCallBack(data) {
               if(data.data){
                   $window.location.reload();
               }
               else {
                   alert("删除失败，请重试！");
               }
            }),function errorCallBack(err) {
                alert("error!\n" + "error message:" + err);
            };
        };
        //删除某些内容
        $scope.delSome=function () {
            console.log(this);
            if($scope.selectList.length===0)
                return;
            $http({
                url: '/delSome',
                method: 'post',
                data: {
                    "uuid" : $scope.user.uuid,
                    "goods_id": $scope.selectList,
                },
                headers :{'Content-Type': 'application/json;charset=UTF-8'},
            }).then(function successCallBack(data) {
                if(data.data){
                    $window.location.reload();
                }
                else {
                    alert("删除失败，请重试！");
                }
            }),function errorCallBack(err) {
                alert("error!\n" + "error message:" + err);
            };
        };
        //清空购物车
        $scope.delAll=function () {
            if($scope.cart.length===0){
                alert("当前购物车是空的！");
                return;
            }
            $http({
                url: '/delAll',
                method: 'post',
                data: {
                    "uuid" : $scope.user.uuid,
                },
                headers :{'Content-Type': 'application/json;charset=UTF-8'},
            }).then(function successCallBack(data) {
                if(data.data){
                    $window.location.reload();
                }
                else {
                    alert("删除失败，请重试！");
                }
            }),function errorCallBack(err) {
                alert("error!\n" + "error message:" + err);
            };
        };
        //支付选中内容
        $scope.buyIt=function () {
            if($scope.selectList.length===0){
                alert("当前未选择任何商品");
                return;
            }
            $http({
                url: '/uuid',
                method: 'post',
                data: {
                    "uuid" : $scope.user.uuid,
                    "good_id":$scope.selectList,
                },
                headers :{'Content-Type': 'application/json;charset=UTF-8'},
            }).then(function successCallBack(data) {
                if(data.data){
                    alert("购买成功！");
                    $window.location.reload();
                }
                else {
                    alert("删除失败，请重试！");
                }
            }),function errorCallBack(err) {
                alert("error!\n" + "error message:" + err);
            };
        };
        //
    }]);
})(angular);