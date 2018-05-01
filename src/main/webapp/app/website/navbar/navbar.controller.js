(function() {
    'use strict';

    angular
        .module('fashionApp')
        .controller('NavbarSiteController', NavbarSiteController);

    NavbarSiteController.$inject = ['$scope', '$state', 'toastr', 'AccountService', '$sessionStorage', '$localStorage'];

    function NavbarSiteController ($scope, $state, toastr, AccountService, $sessionStorage, $localStorage) {
        var vm = this;

        vm.isNavbarCollapsed = true;
        vm.isAuthenticated = false;

        vm.logout = logout;

        vm.toggleNavbar = toggleNavbar;
        vm.collapseNavbar = collapseNavbar;

        function toggleNavbar() {
            vm.isNavbarCollapsed = !vm.isNavbarCollapsed;
        }

        function collapseNavbar() {
            vm.isNavbarCollapsed = true;
        }

        $scope.$on('fashionApp.httpError', function () {
            toastr.warning($translate.instant('error.httpError'), $translate.instant('error.warning'));
            $state.go('home');
        });

        $scope.$on('fashionApp.systemIntermittent', function () {
            toastr.warning($translate.instant('error.systemIntermittent'), $translate.instant('error.warning'));
            $state.go('home');
        });

        $scope.$on('fashionApp.systemError', function () {
            toastr.error($translate.instant('error.systemError'), $translate.instant('error.error'));
            $state.go('home');
        });

        $scope.$on('authenticationSuccess', function () {
            getProfile();
        });

        getProfile();
        function getProfile() {
            vm.isAuthenticated = false;
            AccountService.getProfile().$promise.then(function (response) {
                delete response['password'];
                $sessionStorage.user = response;
                vm.account = response;
                vm.isAuthenticated = true;
            }, function (error) {
                vm.isAuthenticated = false;
            });
        }

        function logout() {
            delete $sessionStorage.siteToken;
            delete $localStorage.siteToken;
            vm.isAuthenticated = false;
            $state.go('home');
        }
    }
})();
