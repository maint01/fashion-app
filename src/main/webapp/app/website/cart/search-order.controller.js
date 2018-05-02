(function () {
    'use strict';

    angular
        .module('fashionApp')
        .controller('SearchCartController', SearchCartController);

    SearchCartController.$inject = ['$scope', '$state', '$stateParams', 'CartService', '$localStorage', '$controller'];

    function SearchCartController($scope, $state, $stateParams, CartService, $localStorage, $controller) {
        var vm = this;

        vm.searchOrder = searchOrder;

        angular.extend(vm, $controller('BaseController', {vm: vm}));

        vm.cart = null;
        vm.isFinded = false;

        function searchOrder(){
            vm.isFinded = true;
            CartService.searchOrder({codeOrder: vm.codeOrder}).$promise.then(function (data) {
                vm.cart = data;
                var totalAmount = 0;
                vm.cart.lstOrderProduct.forEach(function (item) {
                    totalAmount += item.product.currentSale * item.quantity;
                });
                vm.cart.totalAmount = totalAmount;
            }, function (error) {
                if(error.status === 404){
                    vm.cart = null;
                }
            });
        }

    }
})();
