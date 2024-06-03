package org.vaadin.UI.view;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.RouterLayout;



public abstract class ViewTemplate extends VerticalLayout {
    public ViewTemplate() {
        HorizontalLayout header = new HorizontalLayout();

        Button logoButton = createLogoButton();

        Button loginButton = new Button("Login");
        Button signUpButton = new Button("Sign Up");
        Button logoutButton = new Button("Logout");
        Button manageStoresButton = createManageStoresButton();
        Button createNewStoreButton = new Button("Create New Store");
        Button systemManagerButton = new Button("System Manager");

        header.add(logoButton, loginButton, signUpButton, logoutButton, manageStoresButton, createNewStoreButton, systemManagerButton);
        header.setWidth("100%");
        header.setHeight("60px");
        header.setSpacing(true);
        header.setAlignItems(Alignment.CENTER);

        add(header);
    }

    private Button createLogoButton(){
        Image logoImage = new Image("icons/logoFinal.png","");
        logoImage.setWidth("100px");
        logoImage.setHeight("60px");
        Button logoButton = new Button(logoImage);
        logoButton.setHeight("60px");
        logoButton.setWidth("100px");

        logoButton.addClickListener(event -> {
            // Navigate to the homepage
            getUI().ifPresent(ui -> ui.navigate(""));
        });
        return logoButton;
    }

    private Button createManageStoresButton(){
        Button manageStoreButton = new Button("Manage Stores");
        manageStoreButton.addClickListener(event -> {
            // Navigate to the homepage
            getUI().ifPresent(ui -> ui.navigate("settings/inventory"));
        });
        return  manageStoreButton;
    }
    public abstract void setUp();
}
