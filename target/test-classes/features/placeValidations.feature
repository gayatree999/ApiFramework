Feature: Validating Place APIs

Scenario Outline:: Verify if place is being Successfully added using AddPlaceAPI

Given Add Place Payload with "<name>" "<adress>"
When user calls "AddPlaceAPI" with "POST" http request
Then the API call got success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_id created maps to "<name>" using "getPlaceAPI"


Examples:

  |name   		    | adress					|
  |Frontline house  |29, side layout, cohen 09  |
#  |BB house			|bengaluru					|

