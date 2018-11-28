(function () {
    'use strict';

    angular
        .module('fashionApp')
        .directive('cusSelect', cusSelect);
    var template = '<select class="{{class}}" ng-options="{{expression}}" ng-model="ngModel"></select>';

    function cusSelect() {
        return {
            restrict: 'A',
            template: template,
            scope: {
                ngModel: '=',
                cusSelect: '='
            }, link: linkFunc
        };
        function linkFunc(scope, element, attrs) {
            scope.class=attrs.class;
            scope.expression=attrs.options.replace('vm.', '');
            scope[attrs.cusSelect.replace('vm.', '')] = scope.cusSelect;
            console.log(scope.cusSelect);
            console.log(element);
            console.log(attrs.cusSelect);
            return attrs;

        }
    }
})();
