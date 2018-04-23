(function() {
    'use strict';

    angular
        .module('fashionApp')
        .controller('CodPaymentDetailController', CodPaymentDetailController);

    CodPaymentDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'CodPayment', 'Orders'];

    function CodPaymentDetailController($scope, $rootScope, $stateParams, previousState, entity, CodPayment, Orders) {
        var vm = this;

        vm.codPayment = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('fashionApp:codPaymentUpdate', function(event, result) {
            vm.codPayment = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
