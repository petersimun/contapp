package sk.seges.contapp.client.display;

import sk.seges.contapp.client.event.handler.NavigationEventHandler;
import sk.seges.contapp.client.event.handler.SelectionChangedEventHandler;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

public interface HomeDisplay extends IsWidget, TableDisplay, AcceptsOneWidget {

	<T> void addSelectionHandler(final SelectionChangedEventHandler handler, final Class<T> clazz, AsyncCallback<HandlerRegistration> result);

	public interface Presenter extends SelectionChangedEventHandler, NavigationEventHandler {}

}