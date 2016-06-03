App.controller('TweetsController', ['$scope', '$http', function ($scope, $http) {
    const baseURL = "http://localhost:8181/rest/timeline";

    //view-model with defaults
    $scope.userScreenName = $scope.userScreenName || "SBavykin";
    $scope.consumerKey = $scope.consumerKey || "T2mcl0ZfdSLVocQPGK37OVhWG";
    $scope.consumerSecret = $scope.consumerSecret || "3CdqWh66XImdbpTSzYSKqFPHoFW2MvMOYeQY6HHiC1JVBL07De";
    $scope.count = $scope.count || 20;

    $scope.getUserTimeLine = function () {
        $scope.errorState = false;
        $scope.tweets = [];

        if (isEmpty($scope.userScreenName) || isEmpty($scope.consumerKey) || isEmpty($scope.consumerSecret)) {
            $scope.errorState = true;
            return false;
        }

        let requestURL = baseURL + '/' + $scope.userScreenName;
        sendRequestForTweets(requestURL, {
            'consumerKey': $scope.consumerKey,
            'consumerSecret': $scope.consumerSecret,
            'count': $scope.count
        });
    };

    //private context
    var sendRequestForTweets = function (requestURL, params) {
        $http({
            url: requestURL,
            method: "GET",
            params: params
        }).then(function (response) {
            $scope.tweets = response.data;
        }, function (error) {
            $scope.errorState = true;
        });
    };

    var isEmpty = function (str) {
        return (!str || 0 === str.length);
    };
}]);