package sk.seges.contapp.desktop.client.view;

import sk.seges.contapp.client.display.ContactDetailDisplay;
import sk.seges.contapp.shared.model.Contact;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ContactDetailView extends AbstractView implements ContactDetailDisplay, Editor<Contact> {

	interface Driver extends SimpleBeanEditorDriver<Contact, ContactDetailView> {};

	private Driver driver = GWT.create(Driver.class);

	interface ContactDetailUiBinder extends UiBinder<Widget, ContactDetailView> {}

	private static ContactDetailUiBinder uiBinder = GWT.create(ContactDetailUiBinder.class);

	@UiField
	Label firstName;

	@UiField
	Label lastName;

	@UiField
	Label phone;

	@UiField
	Label email;

	@Override
	public void showContact(Contact contact) {
		driver.initialize(this);
		driver.edit(contact);
	}

	@Override
	public Widget onInitialize() {
		return uiBinder.createAndBindUi(this);
	}

}