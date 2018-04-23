(function() {
    'use strict';

    angular
        .module('fashionApp')
        .controller('PrePaymentDetailController', PrePaymentDetailController);

    PrePaymentDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'PrePayment', 'Bank'];

    function PrePaymentDetailController($scope, $rootScope, $stateParams, previousState, entity, PrePayment, Bank) {
        var vm = this;

        vm.prePayment = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('fashionApp:prePaymentUpdate', function(event, result) {
            vm.prePayment = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
