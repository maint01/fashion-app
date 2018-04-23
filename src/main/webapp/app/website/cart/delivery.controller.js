(function () {
    'use strict';

    angular
        .module('fashionApp')
        .controller('DeliveryController', DeliveryController);

    DeliveryController.$inject = ['$scope', '$state', '$stateParams', 'CartService', '$localStorage', '$controller'];

    function DeliveryController($scope, $state, $stateParams, CartService, $localStorage, $controller) {
        var vm = this;

        vm.saveCart = saveCart;
        angular.extend(vm, $controller('BaseController', {vm: vm}));

        vm.cart = $localStorage.cart;

        vm.initCart(vm.cart);

        function saveCart(){
            CartService.saveCart(vm.cart).$promise.then(function (response) {
                console.log(response);
                delete $localStorage.cart;
                $state.go('home');
            }, function(error){
                console.log(error);
            });
        }

    }
})();
