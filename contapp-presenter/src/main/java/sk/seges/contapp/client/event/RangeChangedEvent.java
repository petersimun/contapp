package sk.seges.contapp.client.event;

import sk.seges.contapp.client.event.handler.ListViewEventHandler;
import sk.seges.sesam.dao.Page;

public class RangeChangedEvent extends AbstractTypeAwareEvent<ListViewEventHandler> {

        private final Page page;
        private final Class<?> clazz;

        public RangeChangedEvent(Class<?> clazz, Page page) {
                this.page = page;
                this.clazz = clazz;
        }

        public Page getPage() {
                return page;
        }

        public Class<?> getClazz() {
                return clazz;
        }

        @Override
        protected void dispatch(ListViewEventHandler handler) {
                handler.onRangeChanged(this);
        }

        @Override
        public com.google.gwt.event.shared.GwtEvent.Type<ListViewEventHandler> getAssociatedType() {
                return getAssociatedType(clazz);
        }
}