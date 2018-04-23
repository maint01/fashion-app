(function() {
    'use strict';

    angular
        .module('fashionApp')
        .controller('MorePhotoDetailController', MorePhotoDetailController);

    MorePhotoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'MorePhoto', 'Product'];

    function MorePhotoDetailController($scope, $rootScope, $stateParams, previousState, entity, MorePhoto, Product) {
        var vm = this;

        vm.morePhoto = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('fashionApp:morePhotoUpdate', function(event, result) {
            vm.morePhoto = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
