package sk.seges.contapp.desktop.client;

import sk.seges.contapp.client.activity.ContappPlaceHistoryMapper;
import sk.seges.contapp.client.activity.MainActivityMapper;
import sk.seges.contapp.client.configuration.ClientFactory;
import sk.seges.contapp.client.place.HomePlace;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class DesktopEntryPoint implements EntryPoint {

	private Place defaultPlace = new HomePlace();
	private SimplePanel container = new SimplePanel();

	@Override
	public void onModuleLoad() {

		final ClientFactory clientFactory = GWT.create(ClientFactory.class);

		container.setStyleName("container");

		EventBus eventBus = clientFactory.getEventBus();
		PlaceController placeController = clientFactory.getPlaceController();

		ActivityMapper activityMapper = new MainActivityMapper(clientFactory);
		ActivityManager activityManager = new ActivityManager(activityMapper,eventBus);
		activityManager.setDisplay(container);

		ContappPlaceHistoryMapper historyMapper = GWT.create(ContappPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);

		historyHandler.register(placeController, eventBus, defaultPlace);

		RootPanel.get().add(container);
		historyHandler.handleCurrentHistory();

	}
}