(function () {
    'use strict';

    angular
        .module('fashionApp')
        .factory('errorHandlerInterceptor', errorHandlerInterceptor);

    errorHandlerInterceptor.$inject = ['$q', '$rootScope'];

    function errorHandlerInterceptor($q, $rootScope) {
        var service = {
            responseError: responseError
        };

        return service;

        function responseError(response) {
            if (!(response.status === 401 && (response.data === '' || (response.data.path && response.data.path.indexOf('/api/account') === 0 )))) {
                $rootScope.$emit('fashionApp.httpError', response);
            }
            switch (response.status) {
                case 404:
                case 405:
                    $rootScope.$emit('fashionApp.systemIntermittent', response);
                case 500:
                    $rootScope.$emit('fashionApp.systemError', response);
                    break;
                default:
                    break;
            }
            return $q.reject(response);
        }
    }
})();
