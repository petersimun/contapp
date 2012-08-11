package sk.seges.contapp.client.event.handler;

import sk.seges.contapp.client.event.SelectionChangedEvent;

import com.google.gwt.event.shared.EventHandler;

public interface SelectionChangedEventHandler extends EventHandler {

    void onSelectionChanged(SelectionChangedEvent<?> event);

}
