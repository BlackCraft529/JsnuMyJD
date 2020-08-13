(function (angular) {
    'use strict';
    angular.module('jd.home',[
        'ngRoute'
    ]).config(['$routeProvider',function($routeProvider) {
        $routeProvider.when('/home',{
            templateUrl: 'html/home.html',
            controller: 'homeCtrl'
        })
    }]).controller('homeCtrl', ['$scope','$location', function($scope,$location){
        $scope.user={
            uuid:'',
            password:'',
            name:'',
            avatar:'',
            status: false,
            search_key: "",
        };
        //装填个人信息
        if (sessionStorage.getItem("uuid")!==null) {
            $scope.user.uuid = sessionStorage.getItem("uuid");
            $scope.user.password = sessionStorage.getItem("password");
            $scope.user.name = sessionStorage.getItem("name");
            $scope.user.avatar = sessionStorage.getItem("avatar");
            $scope.user.address = sessionStorage.getItem("address");
            $scope.user.status = true;
            // console.log($scope.user.avatar);
        }
        $scope.iWantIt=function () {
            console.log($scope.user.search_key);
            if($scope.user.search_key==='')
                return;
            $location.path('/search').search({"search_key":$scope.user.search_key});
        };
        $scope.toCart=function () {
            $location.path("/cart");
        }
        //
    }]);
})(angular);