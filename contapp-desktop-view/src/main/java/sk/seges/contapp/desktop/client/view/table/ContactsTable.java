package sk.seges.contapp.desktop.client.view.table;

import java.util.Date;

import sk.seges.acris.widget.client.celltable.AbstractFilterableTable;
import sk.seges.contapp.shared.model.Contact;

import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.view.client.ProvidesKey;

public class ContactsTable extends AbstractFilterableTable<Contact> {

	static class ContactsListKeyProvider implements ProvidesKey<Contact> {

		@Override
		public Object getKey(Contact item) {
			return item.getEmail();
		}
	}

	public ContactsTable(final EventBus eventBus) {
		super(new ContactsListKeyProvider(), Contact.class);
		initialize();
		initializeColumns();
	}

	protected void initializeColumns() {
		Column<Contact, String> column = new Column<Contact, String>(
				new TextCell()) {
			@Override
			public String getValue(Contact object) {

				return object.getFirstName() + " " + object.getLastName();
			}
		};

		addTextColumn(column, 30, "Name", "firstName");
		getColumnSortList().push(column);

		addTextColumn(new Column<Contact, Number>(new NumberCell()) {
            @Override
            public Number getValue(Contact object) {
                    return object.getAge();
            }
		}, 10, "Ejdz", "age");
		
		addTextColumn(new Column<Contact, String>(new TextCell()) {
            @Override
            public String getValue(Contact object) {
                    return object.getEmail();
            }
		}, 20, "I-mejl", "email");

		addTextColumn(new Column<Contact, String>(new TextCell()) {
            @Override
            public String getValue(Contact object) {
                    return object.getPhone();
            }
		}, 20, "Foun", "phone");

		addDateColumn(new Column<Contact, Date>(new RenderableDateCell<Contact>(this)) {
            @Override
            public Date getValue(Contact object) {
                    return object.getCreated();
            }
		}, 15, "Vytvorene", "creationDate");

		addCheckboxColumn(50);
	}

}
