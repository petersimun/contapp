package sk.seges.contapp.client.display;

import sk.seges.contapp.shared.model.Contact;

import com.google.gwt.user.client.ui.IsWidget;

public interface ContactDetailDisplay extends IsWidget {

	void showContact(Contact contact);
	
	public interface Presenter {}
}