package org.vaadin.UI.view;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public abstract class ViewTemplate extends VerticalLayout {
    public ViewTemplate() {
        HorizontalLayout header = new HorizontalLayout();
        Button logo = new Button("Logo");
        Button loginButton = new Button("Login");
        Button signUpButton = new Button("Sign Up");
        Button logoutButton = new Button("Logout");
        Button manageStoresButton = new Button("Manage Stores");
        Button createNewStoreButton = new Button("Create New Store");
        Button systemManagerButton = new Button("System Manager");

        header.add(logo, loginButton, signUpButton, logoutButton, manageStoresButton, createNewStoreButton, systemManagerButton);
        header.setWidth("100%");
        header.setSpacing(true);
        header.setAlignItems(Alignment.CENTER);

        add(header);
    }
    public abstract void setUp();
}
