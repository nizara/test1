'use strict';

angular.module('test1App')
    .controller('BookController', function ($scope, $state, Book) {

        $scope.books = [];
        $scope.loadAll = function() {
            Book.query(function(result) {
               $scope.books = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.book = {
                book_name: null,
                book_author: null,
                id: null
            };
        };
    });
