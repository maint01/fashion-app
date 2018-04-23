(function () {
    'use strict';

    angular
        .module('fashionApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope', '$state', 'BaseService', '$controller'];

    function HomeController($scope, $state, BaseService, $controller) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.register = register;
        vm.searchProducts = searchProducts;

        vm.lstProduct = [];
        vm.isFinded = false;

        angular.extend(vm, $controller('BaseController', {vm: vm}));
        init();

        function init() {
            BaseService.getProducts().$promise.then(
                function (data) {
                    vm.lstProduct = data;
                }, function (error) {
                    console.log(error);
                }
            );
        }

        function searchProducts() {
            vm.isFinded = true;
            BaseService.searchProducts({name: vm.name}).$promise.then(function (data) {
                vm.lstProduct = data;
            }, function () {
                vm.lstProduct = [];
            });
        }

        function register() {
            $state.go('register');
        }

    }
})();
