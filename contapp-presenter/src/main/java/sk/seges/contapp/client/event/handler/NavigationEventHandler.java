package sk.seges.contapp.client.event.handler;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.place.shared.Place;

public interface NavigationEventHandler extends EventHandler {

	void onNavigation(Place place);
}
