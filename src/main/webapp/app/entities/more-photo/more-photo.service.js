(function() {
    'use strict';
    angular
        .module('fashionApp')
        .factory('MorePhoto', MorePhoto);

    MorePhoto.$inject = ['$resource', 'DateUtils'];

    function MorePhoto ($resource, DateUtils) {
        var resourceUrl =  'api/more-photos/:id';

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
            'update': { method:'PUT' }
        });
    }
})();
