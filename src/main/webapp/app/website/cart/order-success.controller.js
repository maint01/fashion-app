(function () {
    'use strict';

    angular
        .module('fashionApp')
        .controller('OrderSuccessController', OrderSuccessController);

    OrderSuccessController.$inject = ['$scope', '$state', '$stateParams', 'CartService', '$localStorage', '$controller'];

    function OrderSuccessController($scope, $state, $stateParams, CartService, $localStorage, $controller) {
        var vm = this;

        angular.extend(vm, $controller('BaseController', {vm: vm}));

        vm.codeOrder = $stateParams.codeOrder;

        if (vm.codeOrder === undefined || vm.codeOrder === null) {
            $state.go('home');
        } else {
            CartService.searchOrder({codeOrder: vm.codeOrder}).$promise.then(function (data) {
                vm.cart = data;
                var totalAmount = 0;
                vm.cart.lstOrderProduct.forEach(function (item) {
                    totalAmount += item.product.currentSale + item.quantity;
                });
                vm.cart.totalAmount = totalAmount;
            }, function () {
                $state.go('home');
            });
        }

    }
})();
