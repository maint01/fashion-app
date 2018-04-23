(function () {
    'use strict';

    angular
        .module('fashionApp')
        .filter('money', money);
    money.$inject = ['$filter'];
    function money($filter) {
        return function (input) {
            var numberFilter = $filter('number');
            var filteredInput = numberFilter(input, 0);
            if(filteredInput !== undefined){
                return filteredInput.toString().replace(/,/g, ".") + ' VNĐ';
            }else{
                return '';
            }
        };
    }
})();
