package org.vaadin.UI.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.Util.Credentials;

abstract class ViewTemplate extends VerticalLayout {

    IPresenter presenter;
    Button loginTopBar;
    Button logoutTopBar;

    public ViewTemplate() {

        HorizontalLayout header = new HorizontalLayout();
        addLogoButton(header);
        addLoginButton(header);
        addSignupButton(header);
        addLogoutButton(header);
        addManageStoresButton(header);
        addCreateStoreButton(header);
        addCartButton(header);  // Add the Cart button
        decorateLayout(header);
        setUp();
        add(header);
    }

    private void addCreateStoreButton(HorizontalLayout layout) {
        Button createStoreButton = new Button("Create Store");
        createStoreButton.addClickListener(event -> { getUI().ifPresent(ui -> ui.navigate("createStore")); });
        layout.add(createStoreButton);
    }

    private void decorateLayout(HorizontalLayout header){
        header.setWidth("100%");
        header.setHeight("60px");
        header.setSpacing(true);
        header.setAlignItems(Alignment.CENTER);
    }

    private void addLogoButton(HorizontalLayout layout){
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
        layout.add(logoButton);
    }

    private void addManageStoresButton(HorizontalLayout layout){
        Button signUpTopBar = new Button("Settings");
        signUpTopBar.addClickListener(event -> { getUI().ifPresent(ui -> ui.navigate("settings")); });
        layout.add(signUpTopBar);
    }

    private void addLogoutButton(HorizontalLayout layout){
        logoutTopBar = new Button("Logout");
        loginTopBar.setVisible(false);
        logoutTopBar.addClickListener(event -> {
            loginTopBar.setVisible(true);
            logoutTopBar.setVisible(false);
            getUI().ifPresent(ui -> ui.navigate("")); });
        layout.add(logoutTopBar);
    }

    private void addLoginButton(HorizontalLayout layout){
        loginTopBar = new Button("Log-in");
        loginTopBar.addClickListener(event -> { getUI().ifPresent(ui -> ui.navigate("login")); });
        layout.add(loginTopBar);
    }

    private void addSignupButton(HorizontalLayout layout){
        Button signUpTopBar = new Button("Sign-up");
        signUpTopBar.addClickListener(event -> { getUI().ifPresent(ui -> ui.navigate("sign-up")); });
        layout.add(signUpTopBar);
    }

    private void addCartButton(HorizontalLayout layout) {
        Button cartButton = new Button("Cart");
        cartButton.addClickListener(event -> {
            String token = Credentials.getToken();
            if (token != null && !token.isEmpty()) {
                getUI().ifPresent(ui -> ui.navigate("cart"));
            } else {
                Notification.show("You need to log in to view your cart.");
            }
        });
        layout.add(cartButton);
    }

    public void setUp() {
        // presenter.onViewLoaded();
    }
}
