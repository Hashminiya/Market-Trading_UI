package org.vaadin.UI.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = ViewTemplate.class)
public class StartView extends VerticalLayout {

    public StartView() {
        Text label = new Text("This is another view.");
        add(label);
    }
}
