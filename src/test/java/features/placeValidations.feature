Feature: Validating Place API's

@AddPlace @Regression
Scenario Outline: Verify if place is being sucessfully added using AddPlaceAPI
Given Add Place Payload with "<name>" "<language>" "<address>"
When user calls "addPlaceAPI" with "post" http request
Then the api call got sucess with status code 200
And "status" in responce body is "OK"
And "scope" in responce body is "APP"
And Verify PlaceId created maps to "<name>" using "getPlaceAPI"

Examples:
|name  | language|address     |
|AHouse|English  |World Center|
#|BHouse|Spanish  |Sea Center  |


@DeletePlace @Regression
Scenario: Verify if Delete Plce functionality is working
Given Delete Place Payload
When user calls "deletePlaceAPI" with "post" http request
Then the api call got sucess with status code 200
And "status" in responce body is "OK"