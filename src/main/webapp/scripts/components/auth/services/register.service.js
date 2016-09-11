'use strict';

angular.module('test1App')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


