(function() {
    'use strict';
    angular
        .module('fashionApp')
        .factory('CodPayment', CodPayment);

    CodPayment.$inject = ['$resource', 'DateUtils'];

    function CodPayment ($resource, DateUtils) {
        var resourceUrl =  'api/cod-payments/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.shipDate = DateUtils.convertLocalDateFromServer(data.shipDate);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.shipDate = DateUtils.convertLocalDateToServer(copy.shipDate);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.shipDate = DateUtils.convertLocalDateToServer(copy.shipDate);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
