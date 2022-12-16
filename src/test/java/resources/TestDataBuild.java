package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.LocationForSerialisePojo;
import pojo.serializeGoogleMapsAddPlacePojoClass;

public class TestDataBuild {
	
	public serializeGoogleMapsAddPlacePojoClass addPlacePayLoad(String name,String adress) {
		
		serializeGoogleMapsAddPlacePojoClass obj= new serializeGoogleMapsAddPlacePojoClass();
		obj.setAccuracy(50);
		obj.setName(name);
		obj.setPhone_number("(+91) 983 893 3937");
		obj.setAddress(adress);
		
		List<String> typesObj= new ArrayList<String>();
		typesObj.add("shoe park");
		typesObj.add("shop");
		obj.setTypes(typesObj);
		
		LocationForSerialisePojo l = new LocationForSerialisePojo();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		obj.setLocation(l);
		
		return obj;
	}

}
