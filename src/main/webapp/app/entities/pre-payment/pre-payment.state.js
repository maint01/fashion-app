(function() {
    'use strict';

    angular
        .module('fashionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('pre-payment', {
            parent: 'entity',
            url: '/pre-payment?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'fashionApp.prePayment.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/pre-payment/pre-payments.html',
                    controller: 'PrePaymentController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('prePayment');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('pre-payment-detail', {
            parent: 'pre-payment',
            url: '/pre-payment/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'fashionApp.prePayment.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/pre-payment/pre-payment-detail.html',
                    controller: 'PrePaymentDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('prePayment');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'PrePayment', function($stateParams, PrePayment) {
                    return PrePayment.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'pre-payment',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('pre-payment-detail.edit', {
            parent: 'pre-payment-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pre-payment/pre-payment-dialog.html',
                    controller: 'PrePaymentDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PrePayment', function(PrePayment) {
                            return PrePayment.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('pre-payment.new', {
            parent: 'pre-payment',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pre-payment/pre-payment-dialog.html',
                    controller: 'PrePaymentDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                status: null,
                                timePayment: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('pre-payment', null, { reload: 'pre-payment' });
                }, function() {
                    $state.go('pre-payment');
                });
            }]
        })
        .state('pre-payment.edit', {
            parent: 'pre-payment',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pre-payment/pre-payment-dialog.html',
                    controller: 'PrePaymentDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PrePayment', function(PrePayment) {
                            return PrePayment.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('pre-payment', null, { reload: 'pre-payment' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('pre-payment.delete', {
            parent: 'pre-payment',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pre-payment/pre-payment-delete-dialog.html',
                    controller: 'PrePaymentDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['PrePayment', function(PrePayment) {
                            return PrePayment.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('pre-payment', null, { reload: 'pre-payment' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
