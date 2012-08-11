package sk.seges.contapp.client;

import sk.seges.contapp.client.activity.MainActivityMapper;
import sk.seges.contapp.client.configuration.ClientFactory;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class ContappEntryPoint implements EntryPoint {

	@Override
	public void onModuleLoad() {

		final ClientFactory clientFactory = GWT.create(ClientFactory.class);

		clientFactory.getViews().registerViewSettings();
		clientFactory.getActivityRegistry().registerActivityMapper(new MainActivityMapper(clientFactory), 
				clientFactory.getViews().getRootView());
		clientFactory.getHistoryRegistry().registerHistory();

	}
}