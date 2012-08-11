package sk.seges.contapp.mobile.client.event;

import sk.seges.contapp.mobile.client.event.handler.PhoneGapResultEventHandler;

import com.google.gwt.event.shared.GwtEvent;

public class TakeCameraPictureResultEvent extends GwtEvent<PhoneGapResultEventHandler> {

	public static final Type<PhoneGapResultEventHandler> TYPE = new Type<PhoneGapResultEventHandler>();

	private final String data;

	public TakeCameraPictureResultEvent(String data) {
		this.data = data;
	}
	
	public String getData() {
		return data;
	}

	@Override
	protected void dispatch(PhoneGapResultEventHandler handler) {
		handler.onTakeCameraPictureResult(this);
	}

	@Override
	public Type<PhoneGapResultEventHandler> getAssociatedType() {
		return TYPE;
	}
}
