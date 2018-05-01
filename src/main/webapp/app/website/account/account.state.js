(function () {
    'use strict';

    angular
        .module('fashionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('register', {
            parent: 'site',
            url: '/register',
            data: {
                authorities: [],
                pageTitle: 'register.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/website/account/register/register.html',
                    controller: 'RegisterController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('register');
                    $translatePartialLoader.addPart('customer');
                    $translatePartialLoader.addPart('site');
                    return $translate.refresh();
                }],
                lazyLoad: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        'app/website/account/register/register.controller.js'
                    ]);
                }]
            }
        }).state('requestReset', {
            parent: 'site',
            url: '/reset/request',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/website/account/reset/request/reset.request.html',
                    controller: 'RequestResetController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('reset');
                    $translatePartialLoader.addPart('site');
                    return $translate.refresh();
                }],
                lazyLoad: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        'app/website/account/reset/request/reset.request.controller.js'
                    ]);
                }]
            }
        }).state('finishReset', {
            parent: 'site',
            url: '/reset/finish?key',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/website/account/reset/finish/reset.finish.html',
                    controller: 'ResetFinishController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('reset');
                    $translatePartialLoader.addPart('site');
                    return $translate.refresh();
                }]
            }
        }).state('login', {
            parent: 'site',
            url: '/login',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/website/account/login/login.html',
                    controller: 'LoginController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('reset');
                    $translatePartialLoader.addPart('site');
                    $translatePartialLoader.addPart('login');
                    return $translate.refresh();
                }],
                previousState: ['$state', function ($state) {
                    return {
                        name: $state.current.name||'home',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    }
                }],
                lazyLoad: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        'app/website/account/login/login.controller.js',
                        'app/website/account/account.service.js'
                    ]);
                }]
            }
        }).state('change-password', {
            parent: 'site',
            url: '/change-password',
            data: {
                authorities: ['ROLE_WEBSITE'],
                pageTitle: 'global.menu.account.password'
            },
            views: {
                'content@': {
                    templateUrl: 'app/website/account/password/change-password.html',
                    controller: 'PasswordController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('site');
                    $translatePartialLoader.addPart('password');
                    return $translate.refresh();
                }],
                previousState: ['$state', function ($state) {
                    return {
                        name: $state.current.name||'home',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    }
                }],
                lazyLoad: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        'app/website/account/password/password.controller.js',
                        'app/website/account/password/password-strength-bar.directive.js',
                        'app/website/account/account.service.js'
                    ]);
                }]
            }
        });
    }
})();
