(function() {
    'use strict';
    angular
        .module('fashionApp')
        .factory('PrePayment', PrePayment);

    PrePayment.$inject = ['$resource', 'DateUtils'];

    function PrePayment ($resource, DateUtils) {
        var resourceUrl =  'api/pre-payments/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.timePayment = DateUtils.convertDateTimeFromServer(data.timePayment);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
