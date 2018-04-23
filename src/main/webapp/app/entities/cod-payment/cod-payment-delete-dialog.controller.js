(function() {
    'use strict';

    angular
        .module('fashionApp')
        .controller('CodPaymentDeleteController',CodPaymentDeleteController);

    CodPaymentDeleteController.$inject = ['$uibModalInstance', 'entity', 'CodPayment'];

    function CodPaymentDeleteController($uibModalInstance, entity, CodPayment) {
        var vm = this;

        vm.codPayment = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            CodPayment.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
