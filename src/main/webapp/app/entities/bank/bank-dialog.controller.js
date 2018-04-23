(function() {
    'use strict';

    angular
        .module('fashionApp')
        .controller('BankDialogController', BankDialogController);

    BankDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Bank', 'PrePayment'];

    function BankDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Bank, PrePayment) {
        var vm = this;

        vm.bank = entity;
        vm.clear = clear;
        vm.save = save;
        vm.prepayments = PrePayment.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.bank.id !== null) {
                Bank.update(vm.bank, onSaveSuccess, onSaveError);
            } else {
                Bank.save(vm.bank, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fashionApp:bankUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
