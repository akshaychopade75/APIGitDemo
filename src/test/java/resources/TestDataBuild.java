package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayload(String name, String language, String address){
		AddPlace place = new AddPlace();
		place.setAccuracy(50);
		place.setAddress(address);
		place.setLanguage(language);
		place.setPhone_number("(+91) 983 893 3937");
		place.setWebsite("http://google.com");
		place.setName(name);

		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");

		place.setTypes(myList);

		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);

		place.setLocation(l);
		return place;
	}
	
	public String deletePlacePayload(String placeId) {
		
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
	}

}
