(function() {
    'use strict';
    angular
        .module('fashionApp')
        .factory('Product', Product);

    Product.$inject = ['$resource', 'DateUtils'];

    function Product ($resource, DateUtils) {
        var resourceUrl =  'api/products/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.timeCreated = DateUtils.convertDateTimeFromServer(data.timeCreated);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' },
            saveProduct: {
                method: 'POST',
                url: 'api/save-product'
            }
        });
    }
})();
