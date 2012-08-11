package sk.seges.contapp.client.event;

import sk.seges.contapp.client.event.handler.NavigationEventHandler;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.place.shared.Place;

public class NavigationEvent extends GwtEvent<NavigationEventHandler> {

	public static Type<NavigationEventHandler> TYPE = new Type<NavigationEventHandler>();
	
	private final Place place;
	
	public NavigationEvent(Place place) {
		this.place = place;
	}
	
	@Override
	public Type<NavigationEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(NavigationEventHandler handler) {
		handler.onNavigation(place);
	}
}