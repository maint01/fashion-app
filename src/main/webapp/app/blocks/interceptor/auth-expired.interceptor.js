(function() {
    'use strict';

    angular
        .module('fashionApp')
        .factory('authExpiredInterceptor', authExpiredInterceptor);

    authExpiredInterceptor.$inject = ['$rootScope', '$q', '$injector', '$localStorage', '$sessionStorage', 'APP_MODULE'];

    function authExpiredInterceptor($rootScope, $q, $injector, $localStorage, $sessionStorage, APP_MODULE) {
        var service = {
            responseError: responseError
        };

        return service;

        function responseError(response) {
            if (response.status === 401) {
                if(APP_MODULE === 'admin') {
                    delete $localStorage.authenticationToken;
                    delete $sessionStorage.authenticationToken;
                    var Principal = $injector.get('Principal');
                    if (Principal.isAuthenticated()) {
                        var Auth = $injector.get('Auth');
                        Auth.authorize(true);
                    }
                }else{
                    delete $localStorage.siteToken;
                    delete $sessionStorage.siteToken;
                }
            }
            return $q.reject(response);
        }
    }
})();
