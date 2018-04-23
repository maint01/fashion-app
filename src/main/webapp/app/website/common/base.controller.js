(function () {
    'use strict';

    angular
        .module('fashionApp')
        .controller('BaseController', BaseController);

    BaseController.$inject = ['$state', 'BaseService', '$localStorage'];

    function BaseController($state, BaseService, $localStorage) {
        var vm = this;

        vm.addToCart = addToCart;
        vm.indexOfCart = indexOfCart;
        vm.initCart = initCart;
        vm.test = 1;

        function indexOfCart(lstProduct, product) {
            for (var i = 0; i < lstProduct.length; i++) {
                if (lstProduct[i].id === product.id) {
                    return i;
                }
            }
            return -1;
        }

        function addToCart(product) {
            var cart = ($localStorage.cart !== undefined && $localStorage.cart !== null)
                ? $localStorage.cart : {};
            if (cart.lstProduct !== undefined) {
                var index = vm.indexOfCart(cart.lstProduct, product);
                if(index !== -1){
                    cart.lstQuantity[index]++;
                }else{
                    cart.lstProduct.push(product);
                    cart.lstQuantity.push(1);
                }
            } else {
                cart.lstProduct = [];
                cart.lstQuantity= [];
                cart.lstProduct.push(product);
                cart.lstQuantity.push(1);
            }
            $localStorage.cart = cart;
            $state.go('cart');
        }

        function initCart(cart) {
            if (cart !== undefined && cart.lstProduct !== undefined) {
                var totalPrice = 0;
                for (var i = 0; i < cart.lstProduct.length; i++) {
                    totalPrice += cart.lstProduct[i].currentSale * cart.lstQuantity[i];
                }
                cart.totalPrice = totalPrice;
            }
        }
    }
})();