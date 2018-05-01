(function () {
    'use strict';

    angular
        .module('fashionApp')
        .controller('PasswordController', PasswordController);

    PasswordController.$inject = ['AccountService', '$controller', 'toastr', '$translate'];

    function PasswordController(AccountService, $controller, toastr, $translate) {
        var vm = this;

        vm.changePassword = changePassword;

        angular.extend(vm, $controller('BaseController', {vm: vm}));

        vm.account = vm.getCurrentAccount();

        function changePassword() {
            AccountService.changePassword(vm.password).$promise.then(function () {
                toastr.success($translate.instant('password.messages.success'),
                    $translate.instant('global.alert.success'));
            }).catch(function () {
                toastr.error($translate.instant('password.messages.error'),
                    $translate.instant('global.alert.error'));
            });
        }
    }
})();
