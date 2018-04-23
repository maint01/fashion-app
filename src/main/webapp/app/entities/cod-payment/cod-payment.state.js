(function() {
    'use strict';

    angular
        .module('fashionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('cod-payment', {
            parent: 'entity',
            url: '/cod-payment?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'fashionApp.codPayment.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/cod-payment/cod-payments.html',
                    controller: 'CodPaymentController',
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
                    $translatePartialLoader.addPart('codPayment');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('cod-payment-detail', {
            parent: 'cod-payment',
            url: '/cod-payment/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'fashionApp.codPayment.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/cod-payment/cod-payment-detail.html',
                    controller: 'CodPaymentDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('codPayment');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'CodPayment', function($stateParams, CodPayment) {
                    return CodPayment.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'cod-payment',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('cod-payment-detail.edit', {
            parent: 'cod-payment-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/cod-payment/cod-payment-dialog.html',
                    controller: 'CodPaymentDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['CodPayment', function(CodPayment) {
                            return CodPayment.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('cod-payment.new', {
            parent: 'cod-payment',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/cod-payment/cod-payment-dialog.html',
                    controller: 'CodPaymentDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                shipDate: null,
                                status: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('cod-payment', null, { reload: 'cod-payment' });
                }, function() {
                    $state.go('cod-payment');
                });
            }]
        })
        .state('cod-payment.edit', {
            parent: 'cod-payment',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/cod-payment/cod-payment-dialog.html',
                    controller: 'CodPaymentDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['CodPayment', function(CodPayment) {
                            return CodPayment.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('cod-payment', null, { reload: 'cod-payment' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('cod-payment.delete', {
            parent: 'cod-payment',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/cod-payment/cod-payment-delete-dialog.html',
                    controller: 'CodPaymentDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['CodPayment', function(CodPayment) {
                            return CodPayment.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('cod-payment', null, { reload: 'cod-payment' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
