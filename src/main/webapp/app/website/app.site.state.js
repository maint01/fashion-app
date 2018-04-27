(function() {
    'use strict';

    angular
        .module('fashionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('site', {
            abstract: true,
            views: {
                'navbar@': {
                    templateUrl: 'app/website/navbar/navbar.html',
                    controller: 'NavbarSiteController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('global');
                    $translatePartialLoader.addPart('site');
                    return $translate.refresh();
                }]
            }
        });
    }
})();
