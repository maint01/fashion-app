(function () {
    'use strict';

    angular
        .module('fashionApp')
        .controller('PopupController', HomeController);

    HomeController.$inject = ['$scope','$rootScope', '$state', 'BaseService', '$controller', '$http', '$uibModalInstance'];

    function HomeController($scope, $rootScope, $state, BaseService, $controller, $http, $uibModalInstance) {
        var vm = this;

        $rootScope.dsfdsfds = null; // this is loaded async

        $http.get("http://localhost:8080/api/getPdf", {
            responseType: 'arraybuffer'
        }).then(function (response) {
            // window.PDFJS.webViewerLoad();
            $rootScope.dsfdsfds = new Uint8Array(response.data);
            console.log($rootScope.dsfdsfds);
        });
        vm.closePopup = function () {
            $uibModalInstance.close();
            $state.go('^');
        }

    }
})();
