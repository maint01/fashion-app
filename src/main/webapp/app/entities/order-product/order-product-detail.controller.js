(function() {
    'use strict';

    angular
        .module('fashionApp')
        .controller('OrderProductDetailController', OrderProductDetailController);

    OrderProductDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'OrderProduct', 'Product', 'Orders'];

    function OrderProductDetailController($scope, $rootScope, $stateParams, previousState, entity, OrderProduct, Product, Orders) {
        var vm = this;

        vm.orderProduct = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('fashionApp:orderProductUpdate', function(event, result) {
            vm.orderProduct = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
