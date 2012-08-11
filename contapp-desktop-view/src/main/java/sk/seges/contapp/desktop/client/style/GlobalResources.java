package sk.seges.contapp.desktop.client.style;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.ImportedWithPrefix;
import com.google.gwt.resources.client.CssResource.NotStrict;

public class GlobalResources {

	public interface Resources extends ClientBundle {

        @NotStrict
        @Source("common.css")
        Common common();

	}

	@ImportedWithPrefix("contapp-common")
    public interface Common extends CssResource {

            String page();
            String tableHolder();
            String formHolder();
            String floatRight();
    }

	public static Common common() {
        return resources.common();
	}

	private static Resources resources;

    static {
            resources = GWT.create(Resources.class);
            resources.common().ensureInjected();
    }
}