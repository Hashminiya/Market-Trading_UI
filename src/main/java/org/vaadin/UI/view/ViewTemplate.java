package org.vaadin.UI.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.vaadin.UI.Util.Credentials;
import org.vaadin.UI.presenter.Interfaces.IPresenter;

abstract class ViewTemplate extends VerticalLayout {

    IPresenter presenter;
    Button loginTopBar;
    Button logoutTopBar;
    Div userDisplayName;

    public ViewTemplate() {
        HorizontalLayout header = new HorizontalLayout();
        addLogoButton(header);
        addUserDisplayName(header);
        addLoginButton(header);
        addSignupButton(header);
        addLogoutButton(header);
        addManageStoresButton(header);
        addCreateStoreButton(header);
        addCartButton(header);
        decorateLayout(header);
        setUp();
        add(header);

        updateUserDisplayName(Credentials.getUsername());
    }

    private void addCreateStoreButton(HorizontalLayout layout) {
        Button createStoreButton = new Button("Create Store");
        createStoreButton.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate("createStore")));
        layout.add(createStoreButton);
    }

    private void decorateLayout(HorizontalLayout header) {
        header.setWidth("100%");
        header.setHeight("60px");
        header.setSpacing(true);
        header.setAlignItems(Alignment.CENTER);
    }

    private void addLogoButton(HorizontalLayout layout) {
        Image logoImage = new Image("icons/logoFinal.png", "");
        logoImage.setWidth("100px");
        logoImage.setHeight("60px");
        Button logoButton = new Button(logoImage);
        logoButton.setHeight("60px");
        logoButton.setWidth("100px");

        logoButton.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate("")));
        layout.add(logoButton);
    }

    private void addManageStoresButton(HorizontalLayout layout) {
        Button settingsButton = new Button("Settings");
        settingsButton.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate("settings")));
        layout.add(settingsButton);
    }

    private void addLogoutButton(HorizontalLayout layout) {
        logoutTopBar = new Button("Logout");
        logoutTopBar.setVisible(false);
        logoutTopBar.addClickListener(event -> {
            Credentials.clear();
            updateUserDisplayName("guest");
            loginTopBar.setVisible(true);
            logoutTopBar.setVisible(false);
            Notification.show("Logged out successfully");
            getUI().ifPresent(ui -> ui.navigate(""));
        });
        layout.add(logoutTopBar);
    }

    private void addLoginButton(HorizontalLayout layout) {
        loginTopBar = new Button("Log-in");
        loginTopBar.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate("login")));
        layout.add(loginTopBar);
    }

    private void addSignupButton(HorizontalLayout layout) {
        Button signUpTopBar = new Button("Sign-up");
        signUpTopBar.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate("sign-up")));
        layout.add(signUpTopBar);
    }

    private void addCartButton(HorizontalLayout layout) {
        Button cartButton = new Button("Cart");
        cartButton.addClickListener(event -> {
            String token = Credentials.getToken();
            if (token != null && !token.isEmpty()) {
                getUI().ifPresent(ui -> ui.navigate("cart"));
            } else {
                Notification.show("No items found in the cart.");
            }
        });
        layout.add(cartButton);
    }

    private void addUserDisplayName(HorizontalLayout layout) {
        userDisplayName = new Div();
        layout.add(userDisplayName);
    }

    public void updateUserDisplayName(String username) {
        userDisplayName.setText("Logged in as: " + username);
        loginTopBar.setVisible("guest".equals(username));
        logoutTopBar.setVisible(!"guest".equals(username));
    }

    public void setUp() {
        // presenter.onViewLoaded();
    }
}
