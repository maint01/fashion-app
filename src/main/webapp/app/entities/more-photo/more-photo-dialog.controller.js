(function() {
    'use strict';

    angular
        .module('fashionApp')
        .controller('MorePhotoDialogController', MorePhotoDialogController);

    MorePhotoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'MorePhoto', 'Product'];

    function MorePhotoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, MorePhoto, Product) {
        var vm = this;

        vm.morePhoto = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.products = Product.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.morePhoto.id !== null) {
                MorePhoto.update(vm.morePhoto, onSaveSuccess, onSaveError);
            } else {
                MorePhoto.save(vm.morePhoto, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('fashionApp:morePhotoUpdate', result);
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
