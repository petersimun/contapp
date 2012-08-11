package sk.seges.contapp.client.activity;

import sk.seges.contapp.client.activity.home.DetailActivity;
import sk.seges.contapp.client.configuration.ClientFactory;
import sk.seges.contapp.client.place.ContactDetailPlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.Place;

public class DetailActivityMapper extends AbstractActivityMapper {

	public DetailActivityMapper(ClientFactory clientFactory) {
		super(clientFactory);
	}

	@Override
	protected Activity getRelevantActivity(Place place) {
		if (place instanceof ContactDetailPlace || currentActivity == null) {
			return new DetailActivity(clientFactory, ((ContactDetailPlace)place).getContact());
		}
		return null;
	}

}
