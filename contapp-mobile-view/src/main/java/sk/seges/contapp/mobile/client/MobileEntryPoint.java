package sk.seges.contapp.mobile.client;

import sk.seges.contapp.client.activity.ContappPlaceHistoryMapper;
import sk.seges.contapp.client.activity.MainActivityMapper;
import sk.seges.contapp.client.configuration.ClientFactory;
import sk.seges.contapp.client.place.HomePlace;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.mgwt.mvp.client.history.MGWTPlaceHistoryHandler;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;
import com.googlecode.mgwt.ui.client.MGWTSettings.ViewPort;
import com.googlecode.mgwt.ui.client.MGWTSettings.ViewPort.DENSITY;

public class MobileEntryPoint implements EntryPoint {

	@Override
	public void onModuleLoad() {
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

		ContappPlaceHistoryMapper historyMapper = GWT.create(ContappPlaceHistoryMapper.class);

		ClientFactory clientFactory = GWT.create(ClientFactory.class);

		clientFactory.getActivityRegistry().registerActivityMapper(new MainActivityMapper(clientFactory), clientFactory.getViews().getRootView());

		RootPanel.get().add(clientFactory.getViews().getRootView());

		MGWTPlaceHistoryHandler historyHandler = new MGWTPlaceHistoryHandler(historyMapper, new AppHistoryObserver());

		historyHandler.register(clientFactory.getPlaceController(), clientFactory.getEventBus(), new HomePlace());
		historyHandler.handleCurrentHistory();

	}
}