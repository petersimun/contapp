package sk.seges.contapp.desktop.client.configuration;

import sk.seges.contapp.client.activity.ContappPlaceHistoryMapper;
import sk.seges.contapp.client.configuration.ClientFactory;
import sk.seges.contapp.client.configuration.HistoryRegistry;
import sk.seges.contapp.client.place.HomePlace;

import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;

public class DesktopHistoryRegistry implements HistoryRegistry {

	private final ClientFactory clientFactory;
	
	public DesktopHistoryRegistry(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}
	
	@Override
	public void registerHistory() {
		ContappPlaceHistoryMapper historyMapper = GWT.create(ContappPlaceHistoryMapper.class);

		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);

		PlaceController placeController = clientFactory.getPlaceController();

		historyHandler.register(placeController, clientFactory.getEventBus(), new HomePlace());
		historyHandler.handleCurrentHistory();
	}

}