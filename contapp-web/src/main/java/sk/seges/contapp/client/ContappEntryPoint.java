package sk.seges.contapp.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import sk.seges.contapp.client.activity.MainActivityMapper;
import sk.seges.contapp.client.configuration.ClientFactory;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;

public class ContappEntryPoint implements EntryPoint {

	private Logger log = Logger.getLogger(getClass().getName());

	@Override
	public void onModuleLoad() {

		final ClientFactory clientFactory = GWT.create(ClientFactory.class);

		GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {

			@Override
			public void onUncaughtException(Throwable e) {
				Window.alert("uncaught: " + e.getLocalizedMessage());
				Window.alert(e.getMessage());
				log.log(Level.SEVERE, "uncaught exception", e);
			}
		});

		clientFactory.getViews().registerViewSettings();
		clientFactory.getActivityRegistry().registerActivityMapper(new MainActivityMapper(clientFactory), 
				clientFactory.getViews().getRootView());
		clientFactory.getHistoryRegistry().registerHistory();

	}
}