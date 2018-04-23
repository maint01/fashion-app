(function() {
    'use strict';

    angular
        .module('fashionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('order-product', {
            parent: 'entity',
            url: '/order-product?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'fashionApp.orderProduct.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/order-product/order-products.html',
                    controller: 'OrderProductController',
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
                    $translatePartialLoader.addPart('orderProduct');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('order-product-detail', {
            parent: 'order-product',
            url: '/order-product/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'fashionApp.orderProduct.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/order-product/order-product-detail.html',
                    controller: 'OrderProductDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('orderProduct');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'OrderProduct', function($stateParams, OrderProduct) {
                    return OrderProduct.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'order-product',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('order-product-detail.edit', {
            parent: 'order-product-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/order-product/order-product-dialog.html',
                    controller: 'OrderProductDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['OrderProduct', function(OrderProduct) {
                            return OrderProduct.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('order-product.new', {
            parent: 'order-product',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/order-product/order-product-dialog.html',
                    controller: 'OrderProductDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                quantity: null,
                                price: null,
                                sale: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('order-product', null, { reload: 'order-product' });
                }, function() {
                    $state.go('order-product');
                });
            }]
        })
        .state('order-product.edit', {
            parent: 'order-product',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/order-product/order-product-dialog.html',
                    controller: 'OrderProductDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['OrderProduct', function(OrderProduct) {
                            return OrderProduct.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('order-product', null, { reload: 'order-product' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('order-product.delete', {
            parent: 'order-product',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/order-product/order-product-delete-dialog.html',
                    controller: 'OrderProductDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['OrderProduct', function(OrderProduct) {
                            return OrderProduct.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('order-product', null, { reload: 'order-product' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
