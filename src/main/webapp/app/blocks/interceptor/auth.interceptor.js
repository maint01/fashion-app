(function() {
    'use strict';

    angular
        .module('fashionApp')
        .factory('authInterceptor', authInterceptor);

    authInterceptor.$inject = ['$rootScope', '$q', '$location', '$localStorage', '$sessionStorage', 'APP_MODULE'];

    function authInterceptor ($rootScope, $q, $location, $localStorage, $sessionStorage, APP_MODULE) {
        var service = {
            request: request
        };

        return service;

        function request (config) {
            /*jshint camelcase: false */
            config.headers = config.headers || {};
            var token;
            if(APP_MODULE === 'admin'){
                token = $localStorage.authenticationToken || $sessionStorage.authenticationToken;
            }else{
                token= $localStorage.siteToken || $sessionStorage.siteToken;
            }
            if (token) {
                config.headers.Authorization = 'Bearer ' + token;
            }
            return config;
        }
    }
})();
