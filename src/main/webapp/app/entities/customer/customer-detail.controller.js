(function() {
    'use strict';

    angular
        .module('fashionApp')
        .controller('CustomerDetailController', CustomerDetailController);

    CustomerDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Customer', 'Orders'];

    function CustomerDetailController($scope, $rootScope, $stateParams, previousState, entity, Customer, Orders) {
        var vm = this;

        vm.customer = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('fashionApp:customerUpdate', function(event, result) {
            vm.customer = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
