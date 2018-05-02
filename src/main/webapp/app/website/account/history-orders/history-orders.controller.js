(function() {
    'use strict';

    angular
        .module('fashionApp')
        .controller('HistoryOrdersController', HistoryOrdersController);

    HistoryOrdersController.$inject = ['$state', 'AccountService', 'ParseLinks', 'toastr', 'paginationConstants', 'pagingParams'];

    function HistoryOrdersController($state, AccountService, ParseLinks, toastr, paginationConstants, pagingParams) {

        var vm = this;

        vm.loadPage = loadPage;
        vm.predicate = pagingParams.predicate;
        vm.reverse = pagingParams.ascending;
        vm.transition = transition;
        vm.itemsPerPage = paginationConstants.itemsPerPage;

        loadAll();

        function loadAll () {
            AccountService.loadHistoryOrders(
                {
                    page: pagingParams.page - 1,
                    size: vm.itemsPerPage,
                    sort: sort()
                }, onSuccess, onError);
            function sort() {
                var result = [vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc')];
                if (vm.predicate !== 'id') {
                    result.push('id');
                }
                return result;
            }
            function onSuccess(data, headers) {
                vm.totalItems = headers('X-Total-Count') || data.length;
                vm.queryCount = vm.totalItems;
                vm.orders = data;
                vm.page = pagingParams.page;
            }
            function onError(error) {
                toastr.error(error.data.message);
            }
        }

        function loadPage(page) {
            vm.page = page;
            vm.transition();
        }

        function transition() {
            $state.transitionTo($state.$current, {
                page: vm.page,
                sort: vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc'),
                search: vm.currentSearch
            });
        }
    }
})();
