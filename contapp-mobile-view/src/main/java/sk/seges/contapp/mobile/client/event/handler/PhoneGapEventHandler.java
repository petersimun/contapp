package sk.seges.contapp.mobile.client.event.handler;

import sk.seges.contapp.mobile.client.event.TakeCameraPictureEvent;

import com.google.gwt.event.shared.EventHandler;

public interface PhoneGapEventHandler extends EventHandler {

	void onTakeCameraPicture(TakeCameraPictureEvent event);
}
