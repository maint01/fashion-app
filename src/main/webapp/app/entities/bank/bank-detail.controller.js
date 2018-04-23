(function() {
    'use strict';

    angular
        .module('fashionApp')
        .controller('BankDetailController', BankDetailController);

    BankDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Bank', 'PrePayment'];

    function BankDetailController($scope, $rootScope, $stateParams, previousState, entity, Bank, PrePayment) {
        var vm = this;

        vm.bank = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('fashionApp:bankUpdate', function(event, result) {
            vm.bank = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
