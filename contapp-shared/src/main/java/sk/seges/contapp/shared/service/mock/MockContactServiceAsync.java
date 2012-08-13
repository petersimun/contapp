package sk.seges.contapp.shared.service.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sk.seges.contapp.shared.model.Contact;
import sk.seges.contapp.shared.service.ContactRemoteServiceAsync;
import sk.seges.sesam.dao.Page;
import sk.seges.sesam.dao.PagedResult;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class MockContactServiceAsync implements ContactRemoteServiceAsync {

	@Override
	public void getContacts(final Page page, final AsyncCallback<PagedResult<List<Contact>>> callback) {
		
		final List<Contact> result = new ArrayList<Contact>();
		result.add(createContact("John", "Doe"));
		result.add(createContact("Kevin", "Mitnick"));
		result.add(createContact("Rocky", "Balboa"));
		
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			
			@Override
			public void execute() {
				callback.onSuccess(new PagedResult<List<Contact>>(page, result, result.size()));
			}
		});
	}
	
	private Contact createContact(String name, String surname) {
		Contact contact = new Contact();
		contact.setAge(30L);
		contact.setCreated(new Date());
		
		String email = "";
		
		for (int i = 0; i < 7; i++) {
			email += Character.toChars(95 + Random.nextInt(25))[0];
		}
		contact.setEmail(email + "@example.com");
		contact.setFirstName(name);
		contact.setLastName(surname);
		
		String phone = "+421 ";
		
		for (int i = 0; i < 10; i++) {
			phone += "" + Random.nextInt(10);
		}
		
		contact.setPhone(phone);
		contact.setPhotoFileName("");
		
		return contact;
	}
}