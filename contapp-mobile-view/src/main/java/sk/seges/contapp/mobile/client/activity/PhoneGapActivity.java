package sk.seges.contapp.mobile.client.activity;

import sk.seges.contapp.mobile.client.event.TakeCameraPictureEvent;
import sk.seges.contapp.mobile.client.event.TakeCameraPictureResultEvent;
import sk.seges.contapp.mobile.client.event.handler.PhoneGapEventHandler;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.googlecode.gwtphonegap.client.camera.PictureCallback;
import com.googlecode.gwtphonegap.client.camera.PictureOptions;

public class PhoneGapActivity implements PhoneGapEventHandler {

	private final PhoneGap phoneGap;
	private final EventBus eventBus;
	
	public PhoneGapActivity(PhoneGap phoneGap, EventBus eventBus) {
		this.phoneGap = phoneGap;
		this.eventBus = eventBus;
	}
	
	public void register() {
		eventBus.addHandler(TakeCameraPictureEvent.TYPE, this);
	}
	
	public void onTakeCameraPicture(TakeCameraPictureEvent event) {
		PictureOptions options = new PictureOptions(25);
		options.setDestinationType(PictureOptions.DESTINATION_TYPE_DATA_URL);
		options.setSourceType(PictureOptions.PICTURE_SOURCE_TYPE_CAMERA);

		phoneGap.getCamera().getPicture(options, new PictureCallback() {

			@Override
			public void onSuccess(String data) {
				eventBus.fireEvent(new TakeCameraPictureResultEvent("data:image/jpeg;base64," + data));
			}

			@Override
			public void onFailure(String message) {
				Window.alert("failure");
			}
		});
	}
}