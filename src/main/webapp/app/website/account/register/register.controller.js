(function() {
    'use strict';

    angular
        .module('fashionApp')
        .controller('RegisterController', RegisterController);


    RegisterController.$inject = ['$translate', '$timeout', 'AccountService'];

    function RegisterController ($translate, $timeout, AccountService) {
        var vm = this;

        vm.doNotMatch = null;
        vm.error = null;
        vm.errorUserExists = null;
        vm.register = register;
        vm.registerAccount = {};
        vm.success = null;

        $timeout(function (){angular.element('#field_fullName').focus();});

        function register () {
            if (vm.registerAccount.password !== vm.confirmPassword) {
                vm.doNotMatch = 'ERROR';
            } else {
                vm.doNotMatch = null;
                vm.error = null;
                vm.errorUserExists = null;
                vm.errorEmailExists = null;

                AccountService.register(vm.registerAccount).$promise.then(function () {
                    vm.success = 'OK';
                }).catch(function (response) {
                    vm.success = null;
                    if (response.status === 400 && response.data === 'USERNAME_EXIST') {
                        vm.errorUserExists = 'ERROR';
                    } else if (response.status === 400 && response.data === 'EMAIL_EXIST') {
                        vm.errorEmailExists = 'ERROR';
                    } else {
                        vm.error = 'ERROR';
                    }
                });
            }
        }
    }
})();
