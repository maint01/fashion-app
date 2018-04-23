(function() {
    'use strict';

    angular
        .module('fashionApp')
        .controller('CodPaymentDialogController', CodPaymentDialogController);

    CodPaymentDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'CodPayment', 'Orders'];

    function CodPaymentDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, CodPayment, Orders) {
        var vm = this;

        vm.codPayment = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.orders = Orders.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.codPayment.id !== null) {
                CodPayment.update(vm.codPayment, onSaveSuccess, onSaveError);
            } else {
                CodPayment.save(vm.codPayment, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fashionApp:codPaymentUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.shipDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
