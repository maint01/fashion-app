(function() {
    'use strict';

    angular
        .module('fashionApp')
        .controller('OrderProductDeleteController',OrderProductDeleteController);

    OrderProductDeleteController.$inject = ['$uibModalInstance', 'entity', 'OrderProduct'];

    function OrderProductDeleteController($uibModalInstance, entity, OrderProduct) {
        var vm = this;

        vm.orderProduct = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            OrderProduct.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
