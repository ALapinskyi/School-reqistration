//module works with admin pages, tables and user profiles
// 		ui.bootstrap	bootstrap library	
// 		ngStorage		store data in sessionStorage
//		ngTable			
//		ngRoute			provides routing and deeplinking; for navs
//		ngResource		send data by ajax

var table = angular.module('table', [ 'ui.bootstrap', 'ngStorage', 'ngTable', 'ngRoute', 'ngResource']);

table.config(['$httpProvider', function ($httpProvider) {    
	$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
}]);

//ctrl for alert on login-page
table.controller('AlertCtrl',
		function($scope) {
			$scope.alerts = [ {
				type : 'danger',
				msg : 'Invalid login or rassword'
			} ];

			$scope.closeAlert = function(index) {
				$scope.alerts.splice(index, 1);
			};
		});



table.controller('TableCtrl', function($scope, $filter, $q, $http, $sessionStorage, ngTableParams) {
    var data = [];
    
    $scope.addUsers = function(id,name,surname,birthdate,gender,insnumber, role){
    	data.push({
			'id' : id,
			'name' : name,
			'surname' : surname,
			'birthdate' : birthdate,
			'gender' : gender,
			'insnumber' : insnumber,
			'role' : role
		});
    }
    
    $scope.length = 0;
    
    $scope.getLength = function(){
    	$sessionStorage.items = [];
    	$scope.length = data.length;
    }
    
    $scope.isActive = function (viewLocation) { 
        return viewLocation === $location.path();
    };

    $scope.tableParams = new ngTableParams({
        page: 1,            // show first page
        count: 10           // count per page
    }, {
        total: data.length, // length of data
        getData: function($defer, params) {
            // use build-in angular filter
            var orderedData = params.sorting() ?
                    $filter('orderBy')(data, params.orderBy()) :
                    data;
            orderedData = params.filter() ?
                    $filter('filter')(orderedData, params.filter()) :
                    orderedData;

            params.total(orderedData.length); // set total for recalc pagination
            $defer.resolve($scope.users = orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
        }
    });

    var inArray = Array.prototype.indexOf ?
            function (val, arr) {
                return arr.indexOf(val)
            } :
            function (val, arr) {
                var i = arr.length;
                while (i--) {
                    if (arr[i] === val) return i;
                }
                return -1
            };
 

    $scope.checkboxes = { 'checked': false, items: {} };

    // watch for check all checkbox
    $scope.$watch('checkboxes.checked', function(value) {
        angular.forEach($scope.users, function(item) {
            if (angular.isDefined(item.id)) {
                $scope.checkboxes.items[item.id] = value;
            }
        });
    });
    

    // watch for data checkboxes
    $scope.$watch('checkboxes.items', function(values) {
        if (!$scope.users) {
            return;
        }
        var checked = 0, unchecked = 0,
            total = $scope.users.length;
        angular.forEach($scope.users, function(item) {
            checked   +=  ($scope.checkboxes.items[item.id]) || 0;
            unchecked += (!$scope.checkboxes.items[item.id]) || 0;
        });
        if ((unchecked == 0) || (checked == 0)) {
            $scope.checkboxes.checked = (checked == total);
        }
        $scope.findStudents();
        // grayed checkbox
        angular.element(document.getElementById("select_all")).prop("indeterminate", (checked != 0 && unchecked != 0));
    }, true);
    
    
    $scope.checkboxChanged = function(){
    	$scope.findStudents();
    }
    
    $scope.index = 0;
    
    $scope.items = [];
    
    $scope.loadFirst = function(){
    	$scope.items = $sessionStorage.items;
		var data = 'id=' + $sessionStorage.items[$scope.index ].id + '&value=' + $sessionStorage.items[$scope.index ].value;	
		$http.post('/schoolreg/savecompany', data )
		.success(function(data1, status, headers, config) {
			$scope.message = data1;
			$http({
				method : 'GET',
				url : '/schoolreg/getAllProfiles'
			}).success(function(data, status, headers, config) {
				$scope.profiles = data;
			}).error(function(data, status, headers, config) {
				$scope.profiles = data;
			});
		})
		.error(function(data, status, headers, config) {
			alert( "failure message: " + JSON.stringify({data: data}));
			
		});
	};	
	

    $scope.nextUser = function(){
    	$scope.index = $scope.index+1;
    	$scope.loadFirst();
    }
    
    $scope.prevUser = function(){
    	$scope.index = $scope.index-1;
    	$scope.loadFirst();
    }
    
	$scope.lists = [];
	$scope.findStudents = function(){
		$scope.lists = [];
		var max = $scope.maxId();
		for(index = 0; index <= max; index++)
			{
				if($scope.checkboxes.items[index] == true)
					{
						$scope.lists.push({
							'id' : index,
							'value' : $scope.checkboxes.items[index]
						});

					}
			}
			$sessionStorage.items =  $scope.lists;
	}
	
	$scope.maxId = function(){
		var max = 0;
		for(index = 0; index < data.length; index++)
		{
			if(data[index].id > max)
				max = data[index].id;
		}
		return max;
	}
	
	$scope.notNull = function(item){
		if(item!="null")
			return true;
		else
			return false
	}
});


table.config(function($routeProvider, $locationProvider){
	var rootUrl = '';
	$routeProvider
	.when('/school', 
	{
		templateUrl: 'part/school',
		controller: 'TableCtrl'
	})
	.when('/main', 
	{
		templateUrl: 'part/main', 
		controller: 'TableCtrl'
	})
	.when('/wishes', 
	{
		templateUrl: 'part/wishes', 
		controller: 'TableCtrl'
	})
	
	.otherwise({redirectTo:'/address'});
	//$locationProvider.html5Mode(true);
	
});

