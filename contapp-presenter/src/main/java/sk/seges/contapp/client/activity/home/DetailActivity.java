package sk.seges.contapp.client.activity.home;

import sk.seges.contapp.client.activity.ContappActivity;
import sk.seges.contapp.client.configuration.ClientFactory;
import sk.seges.contapp.client.display.ContactDetailDisplay;
import sk.seges.contapp.shared.model.Contact;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class DetailActivity extends ContappActivity<ContactDetailDisplay> implements ContactDetailDisplay.Presenter {

	private final Contact contact;

	public DetailActivity(ClientFactory clientFactory, Contact contact) {
		super(clientFactory);
		this.contact = contact;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		setView(clientFactory.getViews().getContactDetailView());
		panel.setWidget(getView().asWidget());
		getView().showContact(contact);
	}
}