package sk.seges.contapp.mobile.client.configuration;

import sk.seges.contapp.client.configuration.ClientFactory;
import sk.seges.contapp.client.display.ContactDetailDisplay;
import sk.seges.contapp.client.display.DisplayFactory;
import sk.seges.contapp.client.display.HomeDisplay;
import sk.seges.contapp.mobile.client.activity.PhoneGapActivity;
import sk.seges.contapp.mobile.client.view.ContactDetailView;
import sk.seges.contapp.mobile.client.view.HomeView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.googlecode.gwtphonegap.client.PhoneGapAvailableEvent;
import com.googlecode.gwtphonegap.client.PhoneGapAvailableHandler;
import com.googlecode.gwtphonegap.client.PhoneGapTimeoutEvent;
import com.googlecode.gwtphonegap.client.PhoneGapTimeoutHandler;
import com.googlecode.mgwt.mvp.client.AnimatableDisplay;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;
import com.googlecode.mgwt.ui.client.MGWTSettings.ViewPort;
import com.googlecode.mgwt.ui.client.MGWTSettings.ViewPort.DENSITY;

public class MobileViews implements DisplayFactory {

	private final ClientFactory clientFactory;
	
    private AnimatableDisplay display;
	private PhoneGap phoneGap;


	public MobileViews(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;	
	}
	
	private PhoneGap getPhoneGap() {
		if (this.phoneGap == null) {
			this.phoneGap = GWT.create(PhoneGap.class);
		}
		
		return this.phoneGap;
	}
	
	@Override
	public HomeDisplay getHomeView() {
		return new HomeView();
	}

	@Override
	public ContactDetailDisplay getContactDetailView() {
		return new ContactDetailView(clientFactory.getEventBus(), getPhoneGap());
	}

	@Override
	public IsWidget getRootView() {
		if (display == null) {
			display = GWT.create(AnimatableDisplay.class);
			RootPanel.get().add(display);
		}

		return display;
	}

	@Override
	public IsWidget getContactDetailHolder() {
		return getRootView();
	}

	@Override
	public void registerViewSettings() {
		ViewPort viewPort = new MGWTSettings.ViewPort();
		viewPort.setTargetDensity(DENSITY.MEDIUM);
		viewPort.setUserScaleAble(false).setMinimumScale(1.0).setMinimumScale(1.0).setMaximumScale(1.0);

		MGWTSettings settings = new MGWTSettings();
		settings.setViewPort(viewPort);
		settings.setIconUrl("logo.png");
		settings.setAddGlosToIcon(true);
		settings.setFullscreen(true);
		settings.setPreventScrolling(true);

		MGWT.applySettings(settings);

		getPhoneGap().addHandler(new PhoneGapAvailableHandler() {

			@Override
			public void onPhoneGapAvailable(PhoneGapAvailableEvent event) {
			}
		});

		getPhoneGap().addHandler(new PhoneGapTimeoutHandler() {

			@Override
			public void onPhoneGapTimeout(PhoneGapTimeoutEvent event) {
				Window.alert("can not load phonegap");

			}
		});

		getPhoneGap().initializePhoneGap();
		
		new PhoneGapActivity(getPhoneGap(), clientFactory.getEventBus()).register();
	}
}