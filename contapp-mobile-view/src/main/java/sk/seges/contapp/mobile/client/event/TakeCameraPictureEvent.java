package sk.seges.contapp.mobile.client.event;

import sk.seges.contapp.mobile.client.event.handler.PhoneGapEventHandler;

import com.google.web.bindery.event.shared.Event;

public class TakeCameraPictureEvent extends Event<PhoneGapEventHandler> {

	public static final Type<PhoneGapEventHandler> TYPE = new Type<PhoneGapEventHandler>();

	public TakeCameraPictureEvent() {
	}
	
	@Override
	protected void dispatch(PhoneGapEventHandler handler) {
		handler.onTakeCameraPicture(this);
	}

	@Override
	public Type<PhoneGapEventHandler> getAssociatedType() {
		return TYPE;
	}

	
}
