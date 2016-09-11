 'use strict';

angular.module('test1App')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-test1App-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-test1App-params')});
                }
                return response;
            }
        };
    });
