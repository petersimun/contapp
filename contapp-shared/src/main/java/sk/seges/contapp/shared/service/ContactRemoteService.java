package sk.seges.contapp.shared.service;

import java.util.List;

import sk.seges.contapp.shared.model.Contact;
import sk.seges.sesam.dao.Page;
import sk.seges.sesam.dao.PagedResult;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("/cs")
public interface ContactRemoteService extends RemoteService {

	PagedResult<List<Contact>> getContacts(Page page);
}
 