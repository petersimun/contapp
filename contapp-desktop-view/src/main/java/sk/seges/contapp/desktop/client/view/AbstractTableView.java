package sk.seges.contapp.desktop.client.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sk.seges.acris.widget.client.celltable.AbstractFilterableTable;
import sk.seges.contapp.client.callback.DefaultAsyncCallback;
import sk.seges.contapp.client.display.TableDisplay;
import sk.seges.contapp.client.event.RangeChangedEvent;
import sk.seges.contapp.client.event.handler.ListViewEventHandler;
import sk.seges.sesam.dao.Page;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;

public abstract class AbstractTableView extends AbstractView implements TableDisplay {

	private Map<Class<?>, AbstractFilterableTable<?>> tables = new HashMap<Class<?>, AbstractFilterableTable<?>>();
	private Map<Class<?>, List<AsyncCallback<AbstractFilterableTable<?>>>> callbacks = new HashMap<Class<?>, List<AsyncCallback<AbstractFilterableTable<?>>>>();

	protected <T> AbstractFilterableTable<T> registerDataTable(final AbstractFilterableTable<T> table, final Class<T> clazz) {
		AsyncDataProvider<T> asyncDataProvider = new AsyncDataProvider<T>() {

			@Override
			protected void onRangeChanged(HasData<T> display) {
				AbstractTableView.this.fireEvent(new RangeChangedEvent(clazz, table.getPage()));
			}
		};

		tables.put(clazz, table);
		table.setDataProvider(asyncDataProvider);

		Window.alert("Pridavam" + clazz.getName());
		
		if (callbacks.get(clazz) != null) {
			Scheduler.get().scheduleDeferred(new ScheduledCommand() {
				
				@Override
				public void execute() {
					List<AsyncCallback<AbstractFilterableTable<?>>> asyncCallbacks = callbacks.get(clazz);
					Window.alert("Callbackujem" + clazz.getName());
					callbacks.remove(clazz);
					for (AsyncCallback<AbstractFilterableTable<?>> callback: asyncCallbacks) {
						callback.onSuccess(table);
					}
				}
			});
		}
		return table;
	}

	@SuppressWarnings("unchecked")
	protected <T> void getDataTable(Class<T> clazz, AsyncCallback<AbstractFilterableTable<T>> callback) {
		AbstractFilterableTable<T> table = (AbstractFilterableTable<T>) tables.get(clazz);

		Window.alert("Zistujem" + clazz.getName());

		if (table != null) {
			callback.onSuccess(table);
		} else {
			AsyncCallback<?> c = (AsyncCallback<AbstractFilterableTable<T>>)callback;
			
			List<AsyncCallback<AbstractFilterableTable<?>>> existingCallbacks  = callbacks.get(clazz);
			
			if (existingCallbacks == null) {
				existingCallbacks = new ArrayList<AsyncCallback<AbstractFilterableTable<?>>>();
			}
			
			existingCallbacks.add((AsyncCallback<AbstractFilterableTable<?>>) c);
			callbacks.put(clazz, existingCallbacks);
		}
	}

	public HandlerRegistration registerListViewHandler(ListViewEventHandler handler, Class<?> clazz) {
		return this.addHandler(handler, RangeChangedEvent.getAssociatedType(clazz));
	}

	public <T> void setData(final List<T> data, Class<T> clazz, final int totalSize) {
		getDataTable(clazz, new DefaultAsyncCallback<AbstractFilterableTable<T>>() {

			@Override
			public void onSuccess(AbstractFilterableTable<T> dataTable) {
				dataTable.setRowCount(totalSize);
				dataTable.setRowData(dataTable.getPageStart(), data);
			}			
		});
	}

	public <T> void getPage(Class<T> clazz, final DefaultAsyncCallback<Page> callback) {
		getDataTable(clazz, new DefaultAsyncCallback<AbstractFilterableTable<T>>() {

			@Override
			public void onSuccess(AbstractFilterableTable<T> c) {
				callback.onSuccess(c.getPage());
			}
			
		});
	}
}