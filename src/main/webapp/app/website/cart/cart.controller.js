(function () {
    'use strict';

    angular
        .module('fashionApp')
        .controller('CartController', CartController);

    CartController.$inject = ['$scope', '$state', '$stateParams', 'BaseService', '$localStorage', '$controller'];

    function CartController($scope, $state, $stateParams, BaseService, $localStorage, $controller) {
        var vm = this;

        vm.changedQuantity = changedQuantity;
        vm.remove = remove;

        angular.extend(vm, $controller('BaseController', {vm: vm}));

        vm.cart = $localStorage.cart;

        init();
        function init() {
            if (vm.cart !== undefined && vm.cart.lstProduct !== undefined) {
                var totalPrice = 0;
                for (var i = 0; i < vm.cart.lstProduct.length; i++) {
                    totalPrice += vm.cart.lstProduct[i].currentSale * vm.cart.lstQuantity[i];
                }
                vm.cart.totalPrice = totalPrice;
            }
        }

        function changedQuantity(){
            init();
        }

        function remove(index){
            vm.cart.lstProduct.splice(index, 1);
            vm.cart.lstQuantity.splice(index, 1);
            $localStorage.cart = vm.cart;
            init();
        }

    }
})();
