(function () {
    'use strict';

    angular
        .module('fashionApp')
        .factory('BaseService', BaseService);

    BaseService.$inject = ['$resource'];

    function BaseService($resource) {
        return $resource({}, {}, {
            getProducts: {
                method: 'GET',
                url: '/api/get-product',
                isArray: true
            },
            getDetailProduct: {
                method: 'GET',
                url: '/api/products/:id'
            },
            searchProducts: {
                method: 'GET',
                url: '/api/get-by-name',
                isArray: true
            }
        });

    }
})();
