package sk.seges.contapp.shared.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

import javax.annotation.Generated;

import sk.seges.contapp.shared.model.Contact;
import sk.seges.sesam.dao.Page;
import sk.seges.sesam.dao.PagedResult;

@RemoteServiceRelativePath("/cs")
@Generated(value = "sk.seges.acris.pap.service.AsyncServiceProcessor")
public interface ContactRemoteServiceAsync {

	void getContacts(Page page, AsyncCallback<PagedResult<List<Contact>>> callback) ;

}