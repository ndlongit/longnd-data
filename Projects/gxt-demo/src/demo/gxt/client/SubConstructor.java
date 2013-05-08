package demo.gxt.client;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.google.gwt.user.client.ui.SimplePanel;

//import com.google.gwt.user.client.ui.Image;

public class SubConstructor extends LayoutContainer {

	private static Images images = GWT.create(Images.class);

	public SubConstructor() {
		setLayout(new RowLayout(Orientation.VERTICAL));

		FieldSet fldstNewFieldset = new FieldSet();
		add(fldstNewFieldset);
		fldstNewFieldset.setHeading("New FieldSet");

		FieldSet fldstNewFieldset_1 = new FieldSet();
		fldstNewFieldset_1.setLayout(new RowLayout(Orientation.VERTICAL));
		add(fldstNewFieldset_1);
		fldstNewFieldset_1.setHeading("New FieldSet");
		LayoutContainer c = new LayoutContainer();
		c.setLayout(new HBoxLayout());

		Label lblNewLabel = new Label("");
		c.add(lblNewLabel);
		HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 5, 0, 0));
		flex.setFlex(1);
		c.add(new Text(), flex);
		Hyperlink hprlnkNewHyperlink = new Hyperlink("New hyperlink", false, "newHistoryToken");
		c.add(hprlnkNewHyperlink);

		Hyperlink hprlnkNewHyperlink_1 = new Hyperlink("New hyperlink", false, "newHistoryToken");
		c.add(hprlnkNewHyperlink_1);

		Hyperlink hprlnkNewHyperlink_2 = new Hyperlink("New hyperlink", false, "newHistoryToken");
		c.add(hprlnkNewHyperlink_2);
		fldstNewFieldset_1.add(c);
		
		Label lblNewLabel_1 = new Label("New label");
		lblNewLabel_1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		fldstNewFieldset_1.add(lblNewLabel_1);
	}

}
