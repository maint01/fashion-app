(function() {
    'use strict';

    angular
        .module('fashionApp')
        .controller('PrePaymentDeleteController',PrePaymentDeleteController);

    PrePaymentDeleteController.$inject = ['$uibModalInstance', 'entity', 'PrePayment'];

    function PrePaymentDeleteController($uibModalInstance, entity, PrePayment) {
        var vm = this;

        vm.prePayment = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            PrePayment.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
