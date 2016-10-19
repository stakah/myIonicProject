(function ($app) {
    angular.module('custom.controllers', []);

    app.controller('LoginController', ['$scope', '$http', '$location', '$rootScope', '$window', '$state', '$translate', 'Notification', function ($scope, $http, $location, $rootScope, $window, $state, $translate, Notification) {

        $scope.message = {};

        $scope.login = function () {

            $scope.message.error = undefined;

            var user = { username: $scope.username.value, password: $scope.password.value };

            $http({
                method: 'POST',
                url: 'auth',
                data: $.param(user),
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
            }).success(handleSuccess).error(handleError);
        }
        
        $rootScope.infiniteReached = function() {
          //
        }

        function handleSuccess(data, status, headers, config) {
            // Store data response on session storage
            // The session storage will be cleaned when the browser window is closed
            if(typeof(Storage) !== "undefined") {
                // save the user data on localStorage
                sessionStorage.setItem("_u",JSON.stringify(data));
            } else {
                // Sorry! No Web Storage support.
                // The home page may not work if it depends
                // on the logged user data
            }
            
            // Redirect to home page
            $state.go("home");
        }

        function handleError(data, status, headers, config) {
            var error = status == 401 ? $translate.instant('Login.view.invalidPassword') : data;
            Notification.error(error);
        }
    }]);

    app.controller('HomeController', ['$scope', '$http', '$rootScope', '$state', '$translate', 'Notification', '$location', function ($scope, $http, $rootScope, $state, $translate, Notification, $location) {
        
        $scope.message = {};
        $scope.dashboard = {};
        $scope.faturamento = [];
        
        $scope.selecionado = {
          valor : 1
        }
        
        // When access home page we have to check
        // if the user is authenticated and the userData
        // was saved on the browser's sessionStorage
        $rootScope.session = (sessionStorage._u) ? JSON.parse(sessionStorage._u) : null;
        if(!$rootScope.session) {
          // If there isn't a user registered on the sessionStorage
          // we must send back to login page
          // TODO - REVISAR login oauth2
          //$state.go("login");
        }
        
        $rootScope.logout = function logout() {

            $http({
                method: 'GET',
                url: 'logout',
            }).then(handleSuccess, handleError)

            $rootScope.session = {};
        }

        function handleSuccess(data) {
            // Before redirect to login page we 
            // have to clean the user data from the 
            // session storage
            if(typeof(Storage) !== "undefined") {
                // save the user data on localStorage
                sessionStorage.removeItem("_u");
            } else {
                // It's not working with sessionStorage
            }
            
            $state.go("login");
        }


        function handleError(error) {
            $rootScope.session.error = error;
        }
        
         $scope.addVendaItem = function(estoque, qtd) {
          var errors = '';
          if (!estoque)
            errors+=$translate.instant('Home.view.Venda.SelectProduct')+'<br/>';
          if (!qtd)
            errors+=$translate.instant('Home.view.Venda.InsertQtd')+'<br/>';
          
          if (errors.length > 0) {
            Notification.error(errors); 
            return;
          }
          
          if (!Venda.active.vendaItens) {
            Venda.active.vendaItens = []
          }
          Venda.active.vendaItens.push( { estoque: estoque, quantidade: qtd  } );
        }
        
        var key = function(obj){
          return obj.id;
        };
        
        $scope.fecharVenda = function() {
          
          var errors = '';
          if (!Venda.active.vendaItens || Venda.active.vendaItens.length === 0) {
            errors+=$translate.instant('Home.view.Venda.AtLeastOneProduct')+'<br/>';
          }
          
          if (Venda.inserting && Venda.active.vendaItens) {
            var stockByProduct = [];
            var totalAddedByProduct = {};
            for (var i=0; i < Venda.active.vendaItens.length; i++ ) {
              var alreadyAdded = false;
              $(stockByProduct).each(function() { 
                if ((this).id === Venda.active.vendaItens[i].estoque.id) {
                  alreadyAdded = true;
                  return;
                }
              });
              if (!alreadyAdded)
                stockByProduct.push(Venda.active.vendaItens[i].estoque);
              
              if (totalAddedByProduct[key(Venda.active.vendaItens[i].estoque)])
                totalAddedByProduct[key(Venda.active.vendaItens[i].estoque)] += Venda.active.vendaItens[i].quantidade;
              else
                totalAddedByProduct[key(Venda.active.vendaItens[i].estoque)] = Venda.active.vendaItens[i].quantidade;
            }
            
            var errorStockExceeded = $translate.instant('Home.view.Venda.StockError')+ '<br/>';
            $(stockByProduct).each(function() {
              if (this.quantidade < totalAddedByProduct[this.id])
                errors+=  errorStockExceeded
                          .replace('{0}', this.produto.marca+' - '+this.produto.descricao)
                          .replace('{1}', this.quantidade);
            });
          }
          
          if (errors.length > 0) {
            Notification.error(errors); 
            return;
          }
          Venda.post();
        }
        
        $scope.removeVendaItem = function(itemVenda) {
          var index = Venda.active.vendaItens.indexOf(itemVenda);
          if (index > -1)
            Venda.active.vendaItens.splice(index,1);
        }
        
        $scope.getTotalVenda = function() {
          var total = 0;
          if (Venda.active && Venda.active.vendaItens ) {
            $(Venda.active.vendaItens).each(function() {  total += (this.estoque.valorVenda * this.quantidade)   });
          }
          return total;
        }
        
        $scope.changePassword = function () {

            var user = { oldPassword: oldPassword.value, newPassword: newPassword.value, newPasswordConfirmation: newPasswordConfirmation.value };

            $http({
                method: 'POST',
                url: 'changePassword',
                data: $.param(user),
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
            }).success(changeSuccess).error(changeError);
            
            function changeSuccess(data, status, headers, config) {
              Notification.info($translate.instant('Home.view.passwordChanged'));
              cleanPasswordFields();
            }
    
            function changeError(data, status, headers, config) {
                var error = status >= 401 ? $translate.instant('Home.view.InvalidPassword') : data;
                Notification.error(error);
            }     
            
            function cleanPasswordFields() {
                oldPassword.value = "";
                newPassword.value = "";
                newPasswordConfirmation.value = "";
                $("#modalPassword").modal("hide");
            }
        }
        
        var closeMenuHandler = function () {
          var element = $(this);
          if(element.closest('.sub-menu').length > 0) {
            element.closest(".navbar-nav").collapse('hide');
          }
        }
          
        $scope.$on('$viewContentLoaded', function(){
          var navMain = $(".navbar-nav");
          
          //Here your view content is fully loaded !!
          navMain.off("click", "a", closeMenuHandler);
          navMain.on("click", "a", closeMenuHandler);
          
          if ($('#morris-bar-chart').length && !$('#morris-bar-chart').data('started')) {
            $('#morris-bar-chart').data('started',true);
            $scope.loadDashboard();
          }
          if($location.url() === '/home/logged/venda?start'){
            var handleStart = $('#btnNewVenda');
          
            var forceIndex = 0;
            var forceStart = setInterval(function() {
              if ($scope.Venda.active.$$hashKey) {
                startNew();
              }
              else {
                forceIndex++;
                if (forceIndex > 10 && $scope.Venda.data.length === 0) {
                  startNew();  
                }
              }
            },100);
            
            var startNew = function() {
              handleStart.trigger('click');
              clearInterval(forceStart);
            }
          }
          else if ($scope.Venda)
            $scope.Venda.cancel();
        });
        
         $scope.loadDashboard = function() {
          
            $http({
                method: 'GET',
                url: 'api/rest/controleEstoque/Custom/DashboardInfo',
            }).then(
              function(info) {
                $scope.dashboard = info.data; 
              }
              , function () { });
              
            $http({
                method: 'GET',
                url: 'api/rest/controleEstoque/Custom/Faturamento',
            }).then(
              function(info) {
                
                $scope.faturamento = info.data;
                
                Morris.Area({
                  element: 'morris-area-chart',
                  data: info.data,
                  xkey: 'dataVendaFormat',
                  ykeys: ['valorVendaDia'],
                  labels: ['Faturamento'],
                  pointSize: 2,
                  hideHover: 'auto',
                  resize: true
                });
                
                Morris.Bar({
                  element: 'morris-bar-chart',
                  data: info.data,
                  xkey: 'dataVendaFormat',
                  ykeys: ['valorCompraDia', 'valorVendaDia'],
                  labels: ['Valor Compra', 'Valor Venda'],
                  hideHover: 'auto',
                  resize: true
                });
              }
              , function () { });
              
            $http({
                method: 'GET',
                url: 'api/rest/controleEstoque/Custom/ProdutoEstoque',
            }).then(
              function(info) {
                Morris.Donut({
                  element: 'morris-donut-chart',
                  data: info.data,
                  resize: true
                });
              }
              , function () { });
        }
    }]);
} (app));
