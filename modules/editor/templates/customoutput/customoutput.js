angular.module('streamPipesApp')
    .directive('customOutput', function ($interval) {
        return {
            restrict : 'E',
            templateUrl : 'modules/editor/templates/customoutput/customoutput.tmpl.html',
            scope : {
                staticProperty : "="
            },
            controller: function($scope, $element) {

            }
        }

    });