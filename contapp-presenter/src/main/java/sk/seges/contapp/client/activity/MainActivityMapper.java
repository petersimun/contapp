package sk.seges.contapp.client.activity;

import sk.seges.contapp.client.activity.home.HomeActivity;
import sk.seges.contapp.client.configuration.ClientFactory;
import sk.seges.contapp.client.place.HomePlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.Place;

public class MainActivityMapper extends AbstractActivityMapper {

	public MainActivityMapper(ClientFactory clientFactory) {
		super(clientFactory);
	}

	@Override
	protected Activity getRelevantActivity(Place place) {
		if (place instanceof HomePlace || currentActivity == null) {
			return new HomeActivity(clientFactory);
		}
		
		return null;
	}
}