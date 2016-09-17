'use strict';

angular.module('test1App').controller('BookDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Book',
        function($scope, $stateParams, $uibModalInstance, entity, Book) {

        $scope.book = entity;
        $scope.load = function(id) {
            Book.get({id : id}, function(result) {
                $scope.book = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('test1App:bookUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.book.id != null) {
                Book.update($scope.book, onSaveSuccess, onSaveError);
            } else {
                Book.save($scope.book, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
