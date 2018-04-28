(function () {
    'use strict';

    angular
        .module('fashionApp')
        .filter('statusOrder', money);
    money.$inject = ['$translate'];
    function money($translate) {
        return function (val) {
            if(val === undefined || val === null){
                return '';
            }
            switch (val){
                case 1:
                    val = $translate.instant('site.order.status.new');
                    break;
                case 2:
                    val = $translate.instant('site.order.status.cancel');
                    break;
                case 3:
                    val = $translate.instant('site.order.status.delivery');
                    break;
                case 4:
                    val = $translate.instant('site.order.status.closed');
                    break;
            }
            return val;
        };
    }
})();
