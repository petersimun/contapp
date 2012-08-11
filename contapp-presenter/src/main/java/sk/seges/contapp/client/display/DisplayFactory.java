package sk.seges.contapp.client.display;

import com.google.gwt.user.client.ui.IsWidget;

public interface DisplayFactory {

	void registerViewSettings();
	
	IsWidget getRootView();
	IsWidget getContactDetailHolder();

	HomeDisplay getHomeView();
	ContactDetailDisplay getContactDetailView();
}