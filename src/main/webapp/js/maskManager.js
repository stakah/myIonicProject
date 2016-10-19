/**
 * Tiago Romano
 * Gerenciador de mascaras 
 * Attributo maskName adicionado no input
 * 
 * Atendidos por esse manager:
 *  -- maskMoney
 */

angular.module('maskmanagerjs', [])

app.directive('maskMoney', function() {
	return {
		restrict : 'A',
		scope : {
			field : '='
		},
		replace : true,
		require: 'ngModel',
		link : function(scope, element, attrs, ctrl) {
			$(element).maskMoney();
			ctrl.$parsers.unshift(function(viewValue) {
				return parseFloat(viewValue.replace($(element).data('thousands'),''));
			});
		}
	};
});

