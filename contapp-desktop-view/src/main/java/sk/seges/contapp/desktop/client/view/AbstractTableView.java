package sk.seges.contapp.desktop.client.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sk.seges.acris.widget.client.celltable.AbstractFilterableTable;
import sk.seges.contapp.client.display.TableDisplay;
import sk.seges.contapp.client.event.RangeChangedEvent;
import sk.seges.contapp.client.event.handler.ListViewEventHandler;
import sk.seges.sesam.dao.Page;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;

public abstract class AbstractTableView extends AbstractView implements TableDisplay {

        private Map<Class<?>, AbstractFilterableTable<?>> tables = new HashMap<Class<?>, AbstractFilterableTable<?>>();

        protected <T> AbstractFilterableTable<T> registerDataTable(final AbstractFilterableTable<T> table, final Class<T> clazz) {
                AsyncDataProvider<T> asyncDataProvider = new AsyncDataProvider<T>() {

                        @Override
                        protected void onRangeChanged(HasData<T> display) {
                                AbstractTableView.this.fireEvent(new RangeChangedEvent(clazz, table.getPage()));
                        }};

                tables.put(clazz, table);
                table.setDataProvider(asyncDataProvider);
            

                return table;
        }

        @SuppressWarnings("unchecked")
        protected <T> AbstractFilterableTable<T> getDataTable(Class<T> clazz) {
                return (AbstractFilterableTable<T>) tables.get(clazz);
        }

        public HandlerRegistration registerListViewHandler(ListViewEventHandler handler, Class<?> clazz) {
            return this.addHandler(handler, RangeChangedEvent.getAssociatedType(clazz));
        }

        public <T> void setData(List<T> data, Class<T> clazz, int totalSize) {
                AbstractFilterableTable<T> dataTable = getDataTable(clazz);
                dataTable.setRowCount(totalSize);
                dataTable.setRowData(dataTable.getPageStart(), data);
        }

        public <T> Page getPage(Class<T> clazz) {
                return getDataTable(clazz).getPage();
        }
}