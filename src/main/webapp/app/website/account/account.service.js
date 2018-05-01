(function () {
    'use strict';

    angular
        .module('fashionApp')
        .factory('AccountService', AccountService);

    AccountService.$inject = ['$resource'];

    function AccountService($resource) {
        return $resource({}, {}, {
            login: {
                method: 'POST',
                url: '/api/site-authenticate'
            },
            register: {
                method: 'POST',
                url: '/api/site/register'
            },
            changePassword: {
                method: 'POST',
                url: '/api/site/change-password'
            },
            getProfile: {
                method: 'GET',
                url: '/api/site/get-profile'
            },
            changeProfile:{
                method: 'POST',
                url: '/api/site/change-profile'
            }
        });

    }
})();
