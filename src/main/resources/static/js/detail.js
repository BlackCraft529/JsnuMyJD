(function (angular) {
    'use strict';
    angular.module('jd.detail',[
        'ngRoute'
    ]).config(['$routeProvider','$qProvider',function($routeProvider,$qProvider) {
        $routeProvider.when('/detail',{
            templateUrl: 'html/detail.html',
            controller: 'detailCtrl'
        });
        $qProvider.errorOnUnhandledRejections(false);
    }]).controller('detailCtrl', ['$scope','$http','$routeParams','$location', function($scope,$http,$routeParams,$location){
        $scope.detail_key=$routeParams.detail_key;
        $scope.user={
            uuid:'',
            cartList:0,
            search_key:'',
        };
        $scope.goods={
            name:'',
            des:'',
            price:'',
            sell_price:'',
            publisher:'',
            image:'',
            cate:'',
        };
        if($scope.detail_key===''||typeof($scope.detail_key)==='undefined' ){
            $location.path('/search');
            return;
        }else {
            $scope.user.uuid=sessionStorage.getItem("uuid");
            if($scope.user.uuid!==''&&typeof ($scope.user.uuid)!=='undefined'){
                //购物车数量获取
                $http({
                    url: '/getCartList',
                    method: 'post',
                    data: {
                        "uuid" : $scope.user.uuid,
                    },
                    headers :{'Content-Type': 'application/json;charset=UTF-8'},
                }).then(function successCallBack(data) {
                    $scope.user.cartList=0;
                    if(data.data!==null){
                        for(var i=0;i<data.data.shopCartGoods.length;i++){
                            $scope.user.cartList+=data.data.shopCartGoods[i].shopCartAmount;
                        }
                    }

                }),function errorCallBack(err) {
                    alert("error!\n" + "error message:" + err);
                };
            }
            //获取商品信息
            $http({
                url: '/goods_id',
                method: 'post',
                data: {
                    "goods_id" : $scope.detail_key,
                },
                headers :{'Content-Type': 'application/json;charset=UTF-8'},
            }).then(function successCallBack(data) {
                console.log(data.data);

            }),function errorCallBack(err) {
                alert("error!\n" + "error message:" + err);
            };

        }
        //搜索
        $scope.iWantIt=function () {
            if($scope.user.search_key==='')
                return;
            $location.path('/search').search({"search_key":$scope.user.search_key});
        };
        //去购物车页面
        $scope.toCart=function () {
            $location.path('/cart');
        };
        //加入购物车
        $scope.putIn=function () {
            if($scope.user.uuid===''||typeof ($scope.user.uuid)==='undefined'){
                $location.path('/login');
                return;
            }
            // console.log(this.item.id);// 测试goods_id
            $http({
                url: '/enrollCart',
                method: 'post',
                data:{
                    uuid: $scope.user.uuid,
                    goods_id: $scope.detail_key,
                },
                headers:{'Content-Type':'application/json;charset=UTF-8'},
            }).then(function successCallback(data) {
                // console.log(data);
                if(data.data)
                    alert("加入购物车成功！");
                else alert("加入购物车失败！");
            }, function errorCallback(response) {
                alert("error!\n"+"error message:"+response);
            });
        };
        //直接购买
        $scope.buyIt=function () {
            $http({
                url: '/pay',
                method: 'post',
                data: {
                    "uuid" : $scope.user.uuid,
                    "goods_id":$scope.detail_key,
                },
                headers :{'Content-Type': 'application/json;charset=UTF-8'},
            }).then(function successCallBack(data) {
                if(data.data){
                    alert("购买成功！");
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