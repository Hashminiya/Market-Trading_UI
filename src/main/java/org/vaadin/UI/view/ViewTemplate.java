package org.vaadin.UI.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.html.Image;
import org.vaadin.UI.presenter.Interfaces.IPresenter;


abstract class ViewTemplate extends VerticalLayout {

    IPresenter presenter;
    Button loginTopBar;
    Button logoutTopBar;
    Button signUpTopBar;

    public ViewTemplate() {
        HorizontalLayout header = new HorizontalLayout();
        addLogoButton(header);
        addLoginButton(header);
        addSignupButton(header);
        addLogoutButton(header);
        addManageStoresButton(header);
        addCreateStoreButton(header);
        addCartButton(header);
        decorateLayout(header);
        displayUserName(header);
        displayButtons();
        setUp();
        add(header);

    }

    private void displayButtons() {
        if (Credentials.isIsLogedIn()){
            loginTopBar.setVisible(false);
            signUpTopBar.setVisible(false);
            logoutTopBar.setVisible(true);
        }
        else{
            loginTopBar.setVisible(true);
            signUpTopBar.setVisible(false);
            logoutTopBar.setVisible(false);
        }
    }

    private void addCreateStoreButton(HorizontalLayout layout) {
        Button createStoreButton = new Button("Create Store");
        createStoreButton.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate("createStore")));
        layout.add(createStoreButton);
    }

    private void decorateLayout(HorizontalLayout header){
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

    private void addManageStoresButton(HorizontalLayout layout){
        Button signUpTopBar = new Button("Settings");
        signUpTopBar.addClickListener(event -> { getUI().ifPresent(ui -> ui.navigate("settings"));});
        layout.add(signUpTopBar);
    }

    private void  addLogoutButton(HorizontalLayout layout){
        logoutTopBar = new Button("Logout");
        logoutTopBar.setVisible(false);
        logoutTopBar.addClickListener(event -> {
            loginTopBar.setVisible(true);
            logoutTopBar.setVisible(false);
            getUI().ifPresent(ui -> ui.navigate(""));});
        layout.add(logoutTopBar);
    }

    private void addLoginButton(HorizontalLayout layout){
        loginTopBar = new Button("Log-in");
        loginTopBar.addClickListener(event -> { getUI().ifPresent(ui -> ui.navigate("login"));});
        layout.add(loginTopBar);

    }

    private void addSignupButton(HorizontalLayout layout){
        signUpTopBar = new Button("Sign-up");
        signUpTopBar.addClickListener(event -> { getUI().ifPresent(ui -> ui.navigate("sign-up"));});
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

    }
    public void setUp(){
        //presenter.onViewLoaded();
    }
    public void displayUserName(HorizontalLayout layout){
        Text userNameLabel = new Text(Credentials.getUserName());
        layout.add(userNameLabel);
    }

}
