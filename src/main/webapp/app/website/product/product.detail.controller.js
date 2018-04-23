(function () {
    'use strict';

    angular
        .module('fashionApp')
        .controller('ProductDetailController', ProductDetailController);

    ProductDetailController.$inject = ['$scope', '$state', '$stateParams', 'BaseService', '$localStorage', '$controller'];

    function ProductDetailController($scope, $state, $stateParams, BaseService, $localStorage, $controller) {
        var vm = this;

        angular.extend(vm, $controller('BaseController', {vm:vm}));

        vm.product = {};

        BaseService.getDetailProduct({id: $stateParams.id}).$promise.then(
            function (entity) {
                vm.product = entity;
            }, function (error) {
                console.log(error);
            });
    }
})();
