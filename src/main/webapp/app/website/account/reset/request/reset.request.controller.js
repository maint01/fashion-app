(function() {
    'use strict';

    angular
        .module('fashionApp')
        .controller('RequestResetController', RequestResetController);

    RequestResetController.$inject = ['$timeout', 'AccountService'];

    function RequestResetController ($timeout, AccountService) {
        var vm = this;

        vm.error = null;
        vm.errorEmailNotExists = null;
        vm.requestReset = requestReset;
        vm.resetAccount = {};
        vm.success = null;

        $timeout(function (){angular.element('#email').focus();});

        function requestReset () {

            vm.error = null;
            vm.errorEmailNotExists = null;

            AccountService.resetPasswordInit(vm.resetAccount.email).then(function () {
                vm.success = 'OK';
            }).catch(function (response) {
                vm.success = null;
                if (response.status === 400 && response.data === 'email address not registered') {
                    vm.errorEmailNotExists = 'ERROR';
                } else {
                    vm.error = 'ERROR';
                }
            });
        }
    }
})();
