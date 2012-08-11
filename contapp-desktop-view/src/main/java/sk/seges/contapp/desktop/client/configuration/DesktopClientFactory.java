package sk.seges.contapp.desktop.client.configuration;

import sk.seges.contapp.client.configuration.ActivityRegistry;
import sk.seges.contapp.client.configuration.ClientFactory;
import sk.seges.contapp.client.configuration.DefaultActivityRegistry;
import sk.seges.contapp.client.configuration.HistoryRegistry;
import sk.seges.contapp.client.display.DisplayFactory;
import sk.seges.contapp.shared.service.ServiceFactory;
import sk.seges.contapp.shared.service.mock.MockServiceFactory;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;

public class DesktopClientFactory implements ClientFactory {

    private final EventBus eventBus = new SimpleEventBus();
    private final PlaceController placeController = new PlaceController(eventBus);

    private final DisplayFactory displayFactory = new DesktopViews();
    private final ServiceFactory serviceFactory = new MockServiceFactory();
    
    private final HistoryRegistry historyRegistry = new DesktopHistoryRegistry(this);
    
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
			activityRegistry = new DefaultActivityRegistry(eventBus);
		}
		return activityRegistry;
	}

	@Override
	public HistoryRegistry getHistoryRegistry() {
		return historyRegistry;
	}
}