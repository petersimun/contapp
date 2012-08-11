package sk.seges.contapp.mobile.client.view;

import sk.seges.contapp.client.display.ContactDetailDisplay;
import sk.seges.contapp.client.event.NavigationEvent;
import sk.seges.contapp.client.place.HomePlace;
import sk.seges.contapp.mobile.client.event.TakeCameraPictureEvent;
import sk.seges.contapp.mobile.client.event.TakeCameraPictureResultEvent;
import sk.seges.contapp.mobile.client.event.handler.PhoneGapResultEventHandler;
import sk.seges.contapp.shared.model.Contact;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.MTextBox;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.base.ButtonBase;
import com.googlecode.mgwt.ui.client.widget.buttonbar.ButtonBar;
import com.googlecode.mgwt.ui.client.widget.buttonbar.CameraButton;

public class ContactDetailView extends Composite implements ContactDetailDisplay, PhoneGapResultEventHandler, Editor<Contact> {

	private static DetailDisplayUiBinder uiBinder = GWT.create(DetailDisplayUiBinder.class);

	interface DetailDisplayUiBinder extends UiBinder<Widget, ContactDetailView> {}

	interface Driver extends SimpleBeanEditorDriver<Contact, ContactDetailView> {};

	private Driver driver = GWT.create(Driver.class);

	private final EventBus globalEventBus;
	private HandlerRegistration imageLoadHandler;
	
	ButtonBase cameraButton;

	@UiField
	HeaderButton backButton;

	@UiField
	MTextBox firstName;

	@UiField
	MTextBox lastName;

	@UiField
	MTextBox phone;

	@UiField
	MTextBox email;

	@UiField
	ButtonBar buttonBar;
	
	@UiField
	Image image;
	
	@UiField
	ScrollPanel scrollPanel;
	
	public ContactDetailView(EventBus globalEventBus, PhoneGap phoneGap) {
		this.globalEventBus = globalEventBus;
		
		initWidget(uiBinder.createAndBindUi(this));
		
//		if (phoneGap.isPhoneGapDevice()) {

			this.cameraButton = new CameraButton();
			this.cameraButton.addTapHandler(new TapHandler() {
				
				@Override
				public void onTap(TapEvent event) {
					ContactDetailView.this.globalEventBus.fireEvent(new TakeCameraPictureEvent());
				}
			});
			this.buttonBar.add(cameraButton);

			globalEventBus.addHandler(TakeCameraPictureResultEvent.TYPE, this);
//		}
	}
	
	@UiHandler("backButton")
	protected void onBackButtonPressed(TapEvent event) {
		globalEventBus.fireEvent(new NavigationEvent(new HomePlace()));
	}

	@Override
	public void showContact(Contact contact) {
		driver.initialize(this);
		driver.edit(contact);
	}

	@Override
	public void onTakeCameraPictureResult(TakeCameraPictureResultEvent event) {
		imageLoadHandler = image.addLoadHandler(new LoadHandler() {

			@Override
			public void onLoad(LoadEvent event) {

				imageLoadHandler.removeHandler();
				scrollPanel.refresh();

			}
		});

		image.setUrl(event.getData());
	}
}