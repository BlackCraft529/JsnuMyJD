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
    }]).controller('cartCtrl',['$scope','$http','$location',function ($scope,$http,$location) {
        $scope.user={
            search_key:'',
        };
        //搜索
        $scope.iWantIt=function () {
            if($scope.user.search_key==='')
                return;
            $location.path('/search').search({"search_key":$scope.user.search_key});
        };
        //选择一些内容
        $scope.select=function ($event) {
            console.log(this);
            console.log($event);
            console.log($event.target.checked);
        }
        //
    }]);
})(angular);