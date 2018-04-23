(function() {
    'use strict';

    angular
        .module('fashionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('more-photo', {
            parent: 'entity',
            url: '/more-photo?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'fashionApp.morePhoto.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/more-photo/more-photos.html',
                    controller: 'MorePhotoController',
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
                    $translatePartialLoader.addPart('morePhoto');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('more-photo-detail', {
            parent: 'more-photo',
            url: '/more-photo/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'fashionApp.morePhoto.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/more-photo/more-photo-detail.html',
                    controller: 'MorePhotoDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('morePhoto');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'MorePhoto', function($stateParams, MorePhoto) {
                    return MorePhoto.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'more-photo',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('more-photo-detail.edit', {
            parent: 'more-photo-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/more-photo/more-photo-dialog.html',
                    controller: 'MorePhotoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['MorePhoto', function(MorePhoto) {
                            return MorePhoto.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('more-photo.new', {
            parent: 'more-photo',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/more-photo/more-photo-dialog.html',
                    controller: 'MorePhotoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                url: null,
                                timeCreated: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('more-photo', null, { reload: 'more-photo' });
                }, function() {
                    $state.go('more-photo');
                });
            }]
        })
        .state('more-photo.edit', {
            parent: 'more-photo',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/more-photo/more-photo-dialog.html',
                    controller: 'MorePhotoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['MorePhoto', function(MorePhoto) {
                            return MorePhoto.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('more-photo', null, { reload: 'more-photo' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('more-photo.delete', {
            parent: 'more-photo',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/more-photo/more-photo-delete-dialog.html',
                    controller: 'MorePhotoDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['MorePhoto', function(MorePhoto) {
                            return MorePhoto.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('more-photo', null, { reload: 'more-photo' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
