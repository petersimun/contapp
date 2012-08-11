package sk.seges.contapp.mobile.client.configuration;

import sk.seges.contapp.client.configuration.ActivityRegistry;
import sk.seges.contapp.client.configuration.ClientFactory;
import sk.seges.contapp.client.configuration.HistoryRegistry;
import sk.seges.contapp.client.display.DisplayFactory;
import sk.seges.contapp.shared.service.ServiceFactory;
import sk.seges.contapp.shared.service.mock.MockServiceFactory;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;

public class MobileClientFactory implements ClientFactory {

    private final EventBus eventBus = new SimpleEventBus();
    private final PlaceController placeController = new PlaceController(eventBus);

    private final DisplayFactory displayFactory = new MobileViews(this);
    private final ServiceFactory serviceFactory = new MockServiceFactory();
    private final HistoryRegistry historyRegistry = new MobileHistoryRegistry(this);
    
    private ActivityRegistry activityRegistry;
    
	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public DisplayFactory getViews() {
		return displayFactory;
	}

	@Override
	public ServiceFactory getServices() {
		return serviceFactory;
	}

	@Override
	public ActivityRegistry getActivityRegistry() {
		if (activityRegistry == null) {
			activityRegistry = new MobileActivityRegistry(getEventBus());
		}
		
		return activityRegistry;
	}

	@Override
	public HistoryRegistry getHistoryRegistry() {
		return historyRegistry;
	}
}