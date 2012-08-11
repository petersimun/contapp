package sk.seges.contapp.client.event.handler;

import sk.seges.contapp.client.event.RangeChangedEvent;

import com.google.gwt.event.shared.EventHandler;

public interface ListViewEventHandler extends EventHandler {

    void onRangeChanged(RangeChangedEvent event);

}
