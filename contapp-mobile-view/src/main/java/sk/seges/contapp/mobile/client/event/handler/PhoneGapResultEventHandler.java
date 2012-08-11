package sk.seges.contapp.mobile.client.event.handler;

import sk.seges.contapp.mobile.client.event.TakeCameraPictureResultEvent;

import com.google.gwt.event.shared.EventHandler;

public interface PhoneGapResultEventHandler extends EventHandler {

	void onTakeCameraPictureResult(TakeCameraPictureResultEvent event);

}