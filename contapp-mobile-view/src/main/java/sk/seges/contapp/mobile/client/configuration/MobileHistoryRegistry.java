package sk.seges.contapp.mobile.client.configuration;

import sk.seges.contapp.client.activity.ContappPlaceHistoryMapper;
import sk.seges.contapp.client.configuration.ClientFactory;
import sk.seges.contapp.client.configuration.HistoryRegistry;
import sk.seges.contapp.client.place.HomePlace;
import sk.seges.contapp.mobile.client.AppHistoryObserver;

import com.google.gwt.core.client.GWT;
import com.googlecode.mgwt.mvp.client.history.MGWTPlaceHistoryHandler;

public class MobileHistoryRegistry implements HistoryRegistry {

	private final ClientFactory clientFactory;
	
	public MobileHistoryRegistry(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}
	
	@Override
	public void registerHistory() {
		ContappPlaceHistoryMapper historyMapper = GWT.create(ContappPlaceHistoryMapper.class);

		MGWTPlaceHistoryHandler mgwtHhistoryHandler = new MGWTPlaceHistoryHandler(historyMapper, new AppHistoryObserver());

		mgwtHhistoryHandler.register(clientFactory.getPlaceController(), clientFactory.getEventBus(), new HomePlace());
		mgwtHhistoryHandler.handleCurrentHistory();
	}

}
