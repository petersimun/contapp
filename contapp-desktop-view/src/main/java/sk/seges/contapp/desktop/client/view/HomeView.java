package sk.seges.contapp.desktop.client.view;

import sk.seges.acris.widget.client.celltable.AbstractFilterableTable;
import sk.seges.contapp.client.display.HomeDisplay;
import sk.seges.contapp.client.event.SelectionChangedEvent;
import sk.seges.contapp.client.event.handler.SelectionChangedEventHandler;
import sk.seges.contapp.desktop.client.view.table.ContactsTable;
import sk.seges.contapp.shared.model.Contact;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;

public class HomeView extends AbstractTableView implements HomeDisplay {

	private static OrderListUiBinder uiBinder = GWT
			.create(OrderListUiBinder.class);

	interface OrderListUiBinder extends UiBinder<Widget, HomeView> {
	}

	@UiField(provided = true)
	AbstractFilterableTable<Contact> cellTable;

	@UiField(provided = true)
	SimplePager pager;

	@UiField
	FlowPanel detail;
	
	private EventBus eventBus;

	public HomeView() {
		eventBus = new SimpleEventBus();
	}

	@Override
	public Widget onInitialize() {
		
		 cellTable = registerDataTable(new ContactsTable(eventBus), Contact.class);
         pager = cellTable.getPager();

         return uiBinder.createAndBindUi(this);
	}
	
	@Override
	public void setWidget(IsWidget w) {
//		super.setWidget(w);
		detail.clear();
		if (w != null) {
			detail.add(w);
		}
	}
	
	@Override
	public <T> HandlerRegistration addSelectionHandler(final SelectionChangedEventHandler handler, final Class<T> clazz) {
		return getDataTable(clazz).getSelectionModel().addSelectionChangeHandler(new SelectionChangeEvent.Handler() {

			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				T selectedItem = getDataTable(clazz).getSelectedItem();
				handler.onSelectionChanged(new SelectionChangedEvent<T>(selectedItem, clazz));
			}
		});
	}

}