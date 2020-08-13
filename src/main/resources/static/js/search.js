(function (angular) {
    'use strict';
    angular.module('jd.search',[
        'ngRoute',
    ]).config(['$routeProvider','$qProvider',function($routeProvider,$qProvider) {
        $routeProvider.when('/search',{
            templateUrl: 'html/search.html',
            controller: 'searchCtrl'
        });
        $qProvider.errorOnUnhandledRejections(false);
    }]).controller('searchCtrl',['$scope','$http','$routeParams','$location',function ($scope,$http,$routeParams,$location) {
        $scope.search_key=$routeParams.search_key;
        console.log($scope.search_key);
        $scope.goodList=[];
        $scope.result=false;
        //获取数据
        $http({
            url:'/getSomething',//验证表单的接口
            method:'post',
            data:{
                "search_key" : $scope.search_key,
            },
            headers:{'Content-Type':'application/json;charset=UTF-8'}, //将其变为 json 参数形式
        }).then(function successCallback(data) {
            $scope.goodList=data.data.goodsList;
            $scope.result=data.data.result;
            // console.log(data.data);
        }, function errorCallback(response) {
            alert("error!\n"+"error message:"+response);
        });
        //搜索
        $scope.IwantIt=function () {
            if($scope.search_key==="")
                return;
            $location.path("/search").search({'search_key':$scope.search_key});
        };
        //加入购物车
        $scope.putIn=function () {
            var uuid=sessionStorage.getItem("uuid");
            if(uuid===null){
                $location.path('/login');
                return;
            }
            // console.log(this.item.id);// 测试goods_id
            $http({
                url: '/enrollCart',
                method: 'post',
                data:{
                    uuid: uuid,
                    goods_id: this.item.id
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
        }
    }]);
})(angular);