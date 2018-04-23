(function() {
    'use strict';

    angular
        .module('fashionApp')
        .controller('OrderProductDialogController', OrderProductDialogController);

    OrderProductDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'OrderProduct', 'Product', 'Orders'];

    function OrderProductDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, OrderProduct, Product, Orders) {
        var vm = this;

        vm.orderProduct = entity;
        vm.clear = clear;
        vm.save = save;
        vm.products = Product.query();
        vm.orders = Orders.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.orderProduct.id !== null) {
                OrderProduct.update(vm.orderProduct, onSaveSuccess, onSaveError);
            } else {
                OrderProduct.save(vm.orderProduct, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fashionApp:orderProductUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
