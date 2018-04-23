(function() {
    'use strict';

    angular
        .module('fashionApp')
        .controller('OrdersDialogController', OrdersDialogController);

    OrdersDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Orders', 'Customer', 'CodPayment', 'OrderProduct'];

    function OrdersDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Orders, Customer, CodPayment, OrderProduct) {
        var vm = this;

        vm.orders = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.customers = Customer.query();
        vm.codpayments = CodPayment.query();
        vm.orderproducts = OrderProduct.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.orders.id !== null) {
                Orders.update(vm.orders, onSaveSuccess, onSaveError);
            } else {
                Orders.save(vm.orders, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fashionApp:ordersUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.timeCreated = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
