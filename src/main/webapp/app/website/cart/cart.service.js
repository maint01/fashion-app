(function () {
    'use strict';

    angular
        .module('fashionApp')
        .factory('CartService', CartService);

    CartService.$inject = ['$resource'];

    function CartService($resource) {
        return $resource({}, {}, {
            saveCart: {
                method: 'POST',
                url: '/api/save-cart'
            },
            searchOrder: {
                method: 'GET',
                url: '/api/search-order/:codeOrder'
            }
        });

    }
})();
