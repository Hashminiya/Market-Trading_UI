package org.vaadin.UI.view;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.html.Image;
import org.springframework.stereotype.Component;
import org.vaadin.UI.presenter.LogoutPresenter;
import org.vaadin.UI.presenter.ViewTemplatePresenter;
import org.vaadin.UI.Util.Credentials;
import org.vaadin.UI.Util.Messages;
import org.vaadin.UI.presenter.Interfaces.IPresenter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

@Component
public abstract class ViewTemplate extends VerticalLayout implements PropertyChangeListener{
    private UI ui;
    IPresenter presenter;
    LogoutPresenter logoutPresenter;
    Button loginTopBar;
    Button logoutTopBar;
    Button signUpTopBar;
    Button notificationTopBar;

    public ViewTemplate() {
        init();
        presenter = new ViewTemplatePresenter(this);
        presenter.onViewLoaded();
        Messages.getInstance().addPropertyChangeListener(this);

    }

    private void init() {
        removeAll();
        HorizontalLayout header = new HorizontalLayout();
        addLogoButton(header);
        addLoginButton(header);
        addSignupButton(header);
        addLogoutButton(header);
        addManageStoresButton(header);
        addCreateStoreButton(header);
        addCartButton(header);
        addNotificationButton(header);
        decorateLayout(header);
        displayUserName(header);
        displayButtons();
        setUp();
        add(header);
        logoutPresenter = new LogoutPresenter(this);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        ui = attachEvent.getUI();
        Messages.getInstance().addPropertyChangeListener(this);
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        super.onDetach(detachEvent);
        Messages.getInstance().removePropertyChangeListener(this);
        ui = null;
    }

    private void displayButtons() {
        if (Credentials.isIsLogedIn()){
            loginTopBar.setVisible(false);
            signUpTopBar.setVisible(false);
            logoutTopBar.setVisible(true);
        }
        else{
            loginTopBar.setVisible(true);
            signUpTopBar.setVisible(true);
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
        Button settingsTopBar = new Button("Settings");
        settingsTopBar.addClickListener(event -> { getUI().ifPresent(ui -> ui.navigate("settings"));});
        layout.add(settingsTopBar);
    }

    private void  addLogoutButton(HorizontalLayout layout){
        logoutTopBar = new Button("Logout");
        logoutTopBar.setVisible(false);
        logoutTopBar.addClickListener(event -> {
            logoutPresenter.onLogOut(this::succesfullLogout);
            });
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

    private void addNotificationButton(HorizontalLayout layout) {
        notificationTopBar = new Button();
        notificationTopBar.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        notificationTopBar.addClickListener(event -> {
            Messages.getInstance().seen();
            getUI().ifPresent(ui -> ui.navigate("notification"));
        });
        layout.add(notificationTopBar);
        notificationTopBar.setIcon(new Icon(VaadinIcon.BELL));
        // Initial update of the button
        updateNotificationButton();

    }

    private void updateNotificationButton() {
        if (ui != null && ui.isAttached()) {
            ui.access(() -> {
                // Remove existing badge if present
                notificationTopBar.getElement().getChildren()
                        .filter(child -> child.getClassList().contains("badge"))
                        .forEach(child -> notificationTopBar.getElement().removeChild(child));



                if (Messages.getInstance().isNewMessage()) {
                    Span badge = new Span("!");
                    badge.getElement().getThemeList().addAll(
                            Arrays.asList("badge", "error", "primary", "small", "pill"));
                    badge.getStyle()
                            .set("position", "absolute")
                            .set("transform", "translate(-40%, -85%)");
                    notificationTopBar.getElement().appendChild(badge.getElement());
                }
            });
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("newMessage".equals(evt.getPropertyName()) && ui != null && ui.isAttached()) {
            updateNotificationButton();
        }
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
    public void setUp(){
        //presenter.onViewLoaded();
    }
    public void displayUserName(HorizontalLayout layout){
        Text userNameLabel = new Text(Credentials.getUserName());
        layout.add(userNameLabel);
    }
    private void succesfullLogout() {
        init();
        getUI().ifPresent(ui -> ui.navigate(""));
    }
}
