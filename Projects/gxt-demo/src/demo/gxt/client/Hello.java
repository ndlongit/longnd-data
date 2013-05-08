package demo.gxt.client;

/*
 * Ext GWT - Ext for GWT Copyright(c) 2007-2009, Ext JS, LLC. licensing@extjs.com http://extjs.com/license
 */
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.HtmlEditor;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.RootPanel;

public class Hello implements EntryPoint {
	public void onModuleLoad() {
		RootPanel.get().add(new AdvancedFormsExample());
	}
}

class AdvancedFormsExample extends LayoutContainer {

	private VerticalPanel vp;

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		vp = new VerticalPanel();
		vp.setSpacing(10);
		createColumnForm();
		add(vp);
	}

	private void createColumnForm() {
		FormData formData = new FormData("100%");
		FormPanel panel = new FormPanel();
		panel.setFrame(true);
		//panel.setIcon(Resources.ICONS.form());
		panel.setHeading("FormPanel");
		panel.setSize(600, -1);
		panel.setLabelAlign(LabelAlign.TOP);
		panel.setButtonAlign(HorizontalAlignment.CENTER);

		LayoutContainer main = new LayoutContainer();
		main.setLayout(new ColumnLayout());

		LayoutContainer left = new LayoutContainer();
		left.setStyleAttribute("paddingRight", "10px");
		FormLayout layout = new FormLayout();
		layout.setLabelAlign(LabelAlign.TOP);
		left.setLayout(layout);

		TextField<String> first = new TextField<String>();
		first.setFieldLabel("First Name");
		left.add(first, formData);

		TextField<String> company = new TextField<String>();
		company.setFieldLabel("Company");
		left.add(company, formData);

		DateField birthday = new DateField();
		birthday.setFieldLabel("Birthday");
		left.add(birthday, formData);

		LayoutContainer right = new LayoutContainer();
		right.setStyleAttribute("paddingLeft", "10px");
		layout = new FormLayout();
		layout.setLabelAlign(LabelAlign.TOP);
		right.setLayout(layout);

		TextField<String> last = new TextField<String>();
		last.setFieldLabel("Last");
		right.add(last, formData);

		TextField<String> email = new TextField<String>();
		email.setFieldLabel("Email");
		right.add(email, formData);

		Radio radio1 = new Radio();
		radio1.setBoxLabel("Yes");

		Radio radio2 = new Radio();
		radio2.setBoxLabel("No");

		RadioGroup group = new RadioGroup();
		group.setFieldLabel("Ext GWT User");
		group.add(radio1);
		group.add(radio2);
		right.add(group);

		main.add(left, new ColumnData(.5));
		main.add(right, new ColumnData(.5));

		panel.add(main, new FormData("100%"));

		HtmlEditor a = new HtmlEditor();
		a.setFieldLabel("Comment");
		a.setHeight(200);
		panel.add(a, new FormData("100%"));

		panel.addButton(new Button("Cancel"));
		panel.addButton(new Button("Submit"));

		vp.add(panel);
	}

}
