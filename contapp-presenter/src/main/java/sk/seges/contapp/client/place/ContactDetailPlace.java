package sk.seges.contapp.client.place;

import sk.seges.contapp.shared.model.Contact;

import com.google.gwt.place.shared.Place;

public class ContactDetailPlace extends Place {
	
	private final Contact contact;
	
	
	public ContactDetailPlace(Contact contact) {
		this.contact = contact;
	}
	
	public Contact getContact() {
		return contact;
	}
}