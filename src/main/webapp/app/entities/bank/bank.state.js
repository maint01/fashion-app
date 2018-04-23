(function() {
    'use strict';

    angular
        .module('fashionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('bank', {
            parent: 'entity',
            url: '/bank?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'fashionApp.bank.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/bank/banks.html',
                    controller: 'BankController',
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
                    $translatePartialLoader.addPart('bank');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('bank-detail', {
            parent: 'bank',
            url: '/bank/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'fashionApp.bank.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/bank/bank-detail.html',
                    controller: 'BankDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('bank');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Bank', function($stateParams, Bank) {
                    return Bank.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'bank',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('bank-detail.edit', {
            parent: 'bank-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/bank/bank-dialog.html',
                    controller: 'BankDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Bank', function(Bank) {
                            return Bank.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('bank.new', {
            parent: 'bank',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/bank/bank-dialog.html',
                    controller: 'BankDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                code: null,
                                description: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('bank', null, { reload: 'bank' });
                }, function() {
                    $state.go('bank');
                });
            }]
        })
        .state('bank.edit', {
            parent: 'bank',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/bank/bank-dialog.html',
                    controller: 'BankDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Bank', function(Bank) {
                            return Bank.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('bank', null, { reload: 'bank' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('bank.delete', {
            parent: 'bank',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/bank/bank-delete-dialog.html',
                    controller: 'BankDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Bank', function(Bank) {
                            return Bank.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('bank', null, { reload: 'bank' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
