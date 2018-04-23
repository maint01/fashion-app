(function() {
    'use strict';

    angular
        .module('fashionApp')
        .controller('OrdersDetailController', OrdersDetailController);

    OrdersDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Orders', 'Customer', 'CodPayment', 'OrderProduct'];

    function OrdersDetailController($scope, $rootScope, $stateParams, previousState, entity, Orders, Customer, CodPayment, OrderProduct) {
        var vm = this;

        vm.orders = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('fashionApp:ordersUpdate', function(event, result) {
            vm.orders = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
