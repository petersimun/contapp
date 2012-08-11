package sk.seges.contapp.shared.service.mock;

import sk.seges.contapp.shared.service.ContactRemoteServiceAsync;
import sk.seges.contapp.shared.service.ServiceFactory;

public class MockServiceFactory implements ServiceFactory {

	private ContactRemoteServiceAsync contactService;
	
	@Override
	public ContactRemoteServiceAsync getContactService() {
		if (contactService == null) {
			contactService = new MockContactServiceAsync();
		}
		
		return contactService;
	}
}