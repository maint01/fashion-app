(function() {
    'use strict';

    angular
        .module('fashionApp')
        .controller('NavbarSiteController', NavbarSiteController);

    NavbarSiteController.$inject = ['$scope', '$state', 'toastr'];

    function NavbarSiteController ($scope, $state, toastr) {
        var vm = this;

        vm.isNavbarCollapsed = true;
        // vm.isAuthenticated = Principal.isAuthenticated;

        // ProfileService.getProfileInfo().then(function(response) {
        //     vm.inProduction = response.inProduction;
        //     vm.swaggerEnabled = response.swaggerEnabled;
        // });

        // vm.login = login;
        // vm.logout = logout;
        vm.toggleNavbar = toggleNavbar;
        vm.collapseNavbar = collapseNavbar;
        vm.$state = $state;

        // function login() {
        //     collapseNavbar();
        //     LoginService.open();
        // }

        // function logout() {
        //     collapseNavbar();
        //     Auth.logout();
        //     $state.go('home');
        // }

        function toggleNavbar() {
            vm.isNavbarCollapsed = !vm.isNavbarCollapsed;
        }

        function collapseNavbar() {
            vm.isNavbarCollapsed = true;
        }

        $scope.$on('fashionApp.httpError', function(){
           toastr.warning($translate.instant('error.httpError'), $translate.instant('error.warning'));
           $state.go('home');
        });

        $scope.$on('fashionApp.systemIntermittent', function(){
            toastr.warning($translate.instant('error.systemIntermittent'), $translate.instant('error.warning'));
            $state.go('home');
        });

        $scope.$on('fashionApp.systemError', function(){
            toastr.error($translate.instant('error.systemError'), $translate.instant('error.error'));
            $state.go('home');
        });
    }
})();
