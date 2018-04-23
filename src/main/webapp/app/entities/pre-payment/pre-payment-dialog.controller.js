(function() {
    'use strict';

    angular
        .module('fashionApp')
        .controller('PrePaymentDialogController', PrePaymentDialogController);

    PrePaymentDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'PrePayment', 'Bank'];

    function PrePaymentDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, PrePayment, Bank) {
        var vm = this;

        vm.prePayment = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.banks = Bank.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.prePayment.id !== null) {
                PrePayment.update(vm.prePayment, onSaveSuccess, onSaveError);
            } else {
                PrePayment.save(vm.prePayment, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fashionApp:prePaymentUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.timePayment = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
