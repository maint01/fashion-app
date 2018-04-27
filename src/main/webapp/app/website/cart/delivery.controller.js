(function () {
    'use strict';

    angular
        .module('fashionApp')
        .controller('DeliveryController', DeliveryController);

    DeliveryController.$inject = ['$scope', '$state', '$stateParams', 'CartService', '$localStorage', '$controller', 'toastr', '$translate'];

    function DeliveryController($scope, $state, $stateParams, CartService, $localStorage, $controller, toastr, $translate) {
        var vm = this;

        vm.saveCart = saveCart;
        angular.extend(vm, $controller('BaseController', {vm: vm}));

        vm.cart = $localStorage.cart;

        if(vm.cart === undefined || vm.cart === null){
            $state.go('home');
        }
        vm.initCart(vm.cart);

        function saveCart(){
            CartService.saveCart(vm.cart).$promise.then(function (response) {
                delete $localStorage.cart;
                toastr.success($translate.instant('site.delivery.success'));
                $state.go('order-success', {codeOrder: response.codeOrder});
            }, function(){
            });
        }

    }
})();
