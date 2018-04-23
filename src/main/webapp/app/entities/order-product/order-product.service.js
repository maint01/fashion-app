(function() {
    'use strict';
    angular
        .module('fashionApp')
        .factory('OrderProduct', OrderProduct);

    OrderProduct.$inject = ['$resource'];

    function OrderProduct ($resource) {
        var resourceUrl =  'api/order-products/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
