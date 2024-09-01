package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforScenario() throws IOException {
		// only run when placeId is null
		StepDefination sd = new StepDefination();

		if (StepDefination.placeId == null) {
			sd.add_place_payload_with("Akshay", "Marathi", "Pune");
			sd.user_calls_with_http_request("addPlaceAPI", "POST");
			sd.verify_place_id_created_maps_to_using("Akshay", "getPlaceAPI");
		}

	}

}
