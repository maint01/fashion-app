(function() {
    'use strict';

    angular
        .module('fashionApp')
        .controller('MorePhotoDeleteController',MorePhotoDeleteController);

    MorePhotoDeleteController.$inject = ['$uibModalInstance', 'entity', 'MorePhoto'];

    function MorePhotoDeleteController($uibModalInstance, entity, MorePhoto) {
        var vm = this;

        vm.morePhoto = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            MorePhoto.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
