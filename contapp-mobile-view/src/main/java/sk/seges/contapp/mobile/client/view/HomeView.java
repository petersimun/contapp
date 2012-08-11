package sk.seges.contapp.mobile.client.view;

import java.util.List;

import sk.seges.contapp.client.display.HomeDisplay;
import sk.seges.contapp.client.event.SelectionChangedEvent;
import sk.seges.contapp.client.event.handler.ListViewEventHandler;
import sk.seges.contapp.client.event.handler.SelectionChangedEventHandler;
import sk.seges.contapp.shared.model.Contact;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.CellList;
import com.googlecode.mgwt.ui.client.widget.celllist.BasicCell;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedEvent;

public class HomeView extends Composite implements HomeDisplay {

	private static HomeViewUiBinder uiBinder = GWT.create(HomeViewUiBinder.class);

	interface HomeViewUiBinder extends UiBinder<Widget, HomeView> {
	}

	private final EventBus localEventBus;
	
	private List<Contact> contacts;
	
	@UiField(provided = true)
	CellList<Contact> cellList;
	
	public HomeView() {
		
		this.localEventBus = new SimpleEventBus();
		
		BasicCell<Contact> cell = new BasicCell<Contact>() {

			@Override
			public String getDisplayString(Contact contact) {
				return contact.getFirstName() + " " + contact.getLastName() + ", " + contact.getPhone();
			}

			@Override
			public boolean canBeSelected(Contact contact) {
				return true;
			}
		};

		cellList = new CellList<Contact>(cell);
		cellList.setRound(true);

		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@Override
	public HandlerRegistration registerListViewHandler(ListViewEventHandler handler, Class<?> clazz) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void setData(List<T> data, Class<T> clazz, int totalSize) {
		if (clazz.equals(Contact.class)) {
			this.contacts = (List<Contact>) data;
			cellList.render(contacts);
		}
	}

	@Override
	public void setWidget(IsWidget w) {
	}

	@UiHandler("cellList")
	protected void onCellSelected(CellSelectedEvent event) {
		if (contacts != null) {
			localEventBus.fireEvent(new SelectionChangedEvent<Contact>(contacts.get(event.getIndex()), Contact.class));
		}
	}

	@Override
	public <T> HandlerRegistration addSelectionHandler(SelectionChangedEventHandler handler, Class<T> clazz) {
		return localEventBus.addHandler(SelectionChangedEvent.getAssociatedType(clazz), handler);
	}
}