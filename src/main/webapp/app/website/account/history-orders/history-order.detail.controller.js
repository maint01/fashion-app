(function () {
    'use strict';

    angular
        .module('fashionApp')
        .controller('OrderDetailController', OrderDetailController);

    OrderDetailController.$inject = ['$scope', '$state', '$stateParams', 'AccountService', '$localStorage', '$controller'];

    function OrderDetailController($scope, $state, $stateParams, AccountService, $localStorage, $controller) {
        var vm = this;


        angular.extend(vm, $controller('BaseController', {vm: vm}));

        vm.cart = null;
        vm.codeOrder = $stateParams.codeOrder;
        AccountService.getOrder({codeOrder: vm.codeOrder}).$promise.then(function (data) {
            vm.cart = data;
            var totalAmount = 0;
            vm.cart.lstOrderProduct.forEach(function (item) {
                totalAmount += item.product.currentSale * item.quantity;
            });
            vm.cart.totalAmount = totalAmount;
        }, function (error) {
            if (error.status === 404) {
                vm.cart = null;
            }
        });
    }
})();
