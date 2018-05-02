(function () {
    'use strict';

    angular
        .module('fashionApp')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$rootScope', '$state', '$timeout', 'AccountService', 'previousState', '$localStorage', '$sessionStorage'];

    function LoginController($rootScope, $state, $timeout, AccountService, previousState, $localStorage, $sessionStorage) {
        var vm = this;

        vm.authenticationError = false;
        vm.cancel = cancel;
        vm.credentials = {};
        vm.login = login;
        vm.password = null;
        vm.register = register;
        vm.rememberMe = false;
        vm.requestResetPassword = requestResetPassword;
        vm.username = null;

        $timeout(function () {
            angular.element('#username').focus();
        });

        function cancel() {
            vm.credentials = {
                username: null,
                password: null,
                rememberMe: true
            };
            vm.authenticationError = false;
        }

        function login(event) {
            event.preventDefault();
            AccountService.login({
                username: vm.username,
                password: vm.password,
                rememberMe: vm.rememberMe
            }).$promise.then(function (data) {
                var jwt = data.id_token;
                if (angular.isDefined(jwt)) {
                    if (vm.rememberMe) {
                        $localStorage.siteToken = jwt;
                    } else {
                        $sessionStorage.siteToken = jwt;
                    }
                }
                vm.authenticationError = false;
                $rootScope.$broadcast('authenticationSuccess');
                if (previousState.name === 'register' || previousState.name === 'finishReset'
                    || previousState.name === 'requestReset' || previousState.name === 'change-password') {
                    $state.go('home');
                } else {
                    $state.go(previousState.name, previousState.params);
                }
            }).catch(function () {
                vm.authenticationError = true;
            });
        }

        function register() {
            $state.go('register');
        }

        function requestResetPassword() {
            $state.go('requestReset');
        }
    }
})();
