(function () {
    'use strict';

    angular
        .module('fashionApp')
        .controller('ProductDialogController', ProductDialogController);

    ProductDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Product',
        'Category', 'MorePhoto', 'OrderProduct', 'DataUtils'];

    function ProductDialogController($timeout, $scope, $stateParams, $uibModalInstance, entity, Product,
                                     Category, MorePhoto, OrderProduct, DataUtils) {
        var vm = this;

        vm.product = entity;
        vm.product.image = {};
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.changedPhoto = changedPhoto;
        vm.categories = Category.query();
        vm.morephotos = MorePhoto.query();
        vm.orderproducts = OrderProduct.query();

        $timeout(function () {
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear() {
            $uibModalInstance.dismiss('cancel');
        }

        function save() {
            vm.isSaving = true;
            Product.saveProduct(vm.product, onSaveSuccess, onSaveError);
        }

        function onSaveSuccess(result) {
            $scope.$emit('fashionApp:productUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError() {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.timeCreated = false;

        function openCalendar(date) {
            vm.datePickerOpenStatus[date] = true;
        }

        function changedPhoto(file) {
            if (file === null && file.$error === 'pattern') {
                return;
            }
            vm.product.image.fileName = file.name;
            if (file) {
                DataUtils.toBase64(file, function (base64Data) {
                    $scope.$apply(function () {
                        vm.product.image.fileData = base64Data;
                        vm.product.image.contentType = file.type;
                    });
                });
            }
        }
    }
})();
