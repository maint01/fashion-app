(function () {
    'use strict';

    angular
        .module('fashionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('detail', {
            parent: 'home',
            url: 'product/{id}',
            views: {
                'content@': {
                    templateUrl: 'app/website/product/product-detail.html',
                    controller: 'ProductDetailController',
                    controllerAs: 'vm'
                }
            },
            params: {
                id: null
            },
            resolve: {
                lazyLoad: ['$ocLazyLoad', function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        'app/website/product/product.detail.controller.js'
                    ]);
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('global');
                    $translatePartialLoader.addPart('site');
                }]
            }
        });
    }
})();
