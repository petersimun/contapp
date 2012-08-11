package sk.seges.contapp.client.configuration;

import sk.seges.contapp.client.display.DisplayFactory;
import sk.seges.contapp.shared.service.ServiceFactory;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;

public interface ClientFactory {
		
	EventBus getEventBus();
	
	PlaceController getPlaceController();
	
	DisplayFactory getViews();
	
	ServiceFactory getServices();
	
	ActivityRegistry getActivityRegistry();
	
	HistoryRegistry getHistoryRegistry();
}