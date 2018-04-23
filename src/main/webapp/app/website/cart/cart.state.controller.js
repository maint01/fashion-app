(function() {
    'use strict';

    angular
        .module('fashionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('cart', {
            parent: 'home',
            url: 'cart',
            views: {
                'content@': {
                    templateUrl: 'app/website/cart/cart.html',
                    controller: 'CartController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                lazyLoad: ['$ocLazyLoad', function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        'app/website/cart/cart.controller.js'
                    ]);
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('global');
                    $translatePartialLoader.addPart('site');
                }]
            }
        }).state('search-order', {
            parent: 'home',
            url: 'search-order',
            views: {
                'content@': {
                    templateUrl: 'app/website/cart/search-order.html',
                    controller: 'SearchCartController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                lazyLoad: ['$ocLazyLoad', function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        'app/website/cart/search-order.controller.js',
                        'app/website/cart/cart.service.js'
                    ]);
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('global');
                    $translatePartialLoader.addPart('site');
                }]
            }
        }).state('delivery', {
            parent: 'home',
            url: 'delivery',
            views: {
                'content@': {
                    templateUrl: 'app/website/cart/delivery.html',
                    controller: 'DeliveryController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                lazyLoad: ['$ocLazyLoad', function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        'app/website/cart/delivery.controller.js',
                        'app/website/cart/cart.service.js'
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
