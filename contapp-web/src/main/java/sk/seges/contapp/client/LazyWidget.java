package sk.seges.contapp.client;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

abstract class LazyWidget implements AcceptsOneWidget {

	public abstract AcceptsOneWidget asWidget();

	@Override
	public void setWidget(IsWidget w) {
		asWidget().setWidget(w);
	}
}