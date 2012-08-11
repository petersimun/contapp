package sk.seges.contapp.client.display;

import sk.seges.contapp.client.event.handler.NavigationEventHandler;
import sk.seges.contapp.client.event.handler.SelectionChangedEventHandler;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

public interface HomeDisplay extends IsWidget, TableDisplay, AcceptsOneWidget {

	<T> HandlerRegistration addSelectionHandler(SelectionChangedEventHandler handler, Class<T> clazz);
	
	public interface Presenter extends SelectionChangedEventHandler, NavigationEventHandler {}

}