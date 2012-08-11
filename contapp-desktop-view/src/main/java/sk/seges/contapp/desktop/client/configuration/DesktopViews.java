package sk.seges.contapp.desktop.client.configuration;

import sk.seges.contapp.client.display.ContactDetailDisplay;
import sk.seges.contapp.client.display.DisplayFactory;
import sk.seges.contapp.client.display.HomeDisplay;
import sk.seges.contapp.desktop.client.view.ContactDetailView;
import sk.seges.contapp.desktop.client.view.HomeView;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class DesktopViews implements DisplayFactory {

	private HomeDisplay currentHomeDisplay;
	
	private SimplePanel container;

	@Override
	public HomeDisplay getHomeView() {
		if (currentHomeDisplay == null) {
			currentHomeDisplay = new HomeView();
		}
		return currentHomeDisplay;
	}

	@Override
	public ContactDetailDisplay getContactDetailView() {
		return new ContactDetailView();
	}

	@Override
	public IsWidget getRootView() {
		if (container == null) {
			container = new SimplePanel();
			container.setStyleName("container");
			RootPanel.get().add(container);
		}
		
		return container;
	}

	@Override
	public IsWidget getContactDetailHolder() {
		return getHomeView();
	}

	@Override
	public void registerViewSettings() {
	}
}