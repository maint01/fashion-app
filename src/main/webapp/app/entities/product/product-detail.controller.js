(function() {
    'use strict';

    angular
        .module('fashionApp')
        .controller('ProductDetailController', ProductDetailController);

    ProductDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Product', 'Category', 'MorePhoto', 'OrderProduct'];

    function ProductDetailController($scope, $rootScope, $stateParams, previousState, entity, Product, Category, MorePhoto, OrderProduct) {
        var vm = this;

        vm.product = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('fashionApp:productUpdate', function(event, result) {
            vm.product = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
