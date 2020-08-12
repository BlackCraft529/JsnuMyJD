(function (angular) {
    'use strict';
    angular.module('jd.home',[
        'ngRoute'
    ]).config(['$routeProvider',function($routeProvider) {
        $routeProvider.when('/home',{
            templateUrl: 'html/home.html',
            controller: 'homeCtrl'
        })
    }]).controller('homeCtrl', ['$scope', function($scope){
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
        }
        $scope.iWantIt=function () {

        };
    }]);
})(angular)