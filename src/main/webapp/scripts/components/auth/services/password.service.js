'use strict';

angular.module('test1App')
    .factory('Password', function ($resource) {
        return $resource('api/account/change_password', {}, {
        });
    });

angular.module('test1App')
    .factory('PasswordResetInit', function ($resource) {
        return $resource('api/account/reset_password/init', {}, {
        })
    });

angular.module('test1App')
    .factory('PasswordResetFinish', function ($resource) {
        return $resource('api/account/reset_password/finish', {}, {
        })
    });
