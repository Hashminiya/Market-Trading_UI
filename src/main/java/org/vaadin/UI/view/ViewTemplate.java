package org.vaadin.UI.view;

import org.vaadin.UI.Util.UserRole;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.router.RouterLayout;


abstract class ViewTemplate extends VerticalLayout implements RouterLayout {


    public ViewTemplate() {

        HorizontalLayout header = new HorizontalLayout();

        addLogoButton(header);
        addLoginButton(header);
        addSignupButton(header);
        addLogoutButton(header);
        addManageStoresButton(header);
        decorateLayout(header);

        add(header);
    }

    private  void decorateLayout(HorizontalLayout header){
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
        Button signUpTopBar = new Button("Manage Stores");
        signUpTopBar.addClickListener(event -> { getUI().ifPresent(ui -> ui.navigate("settings/inventory"));});
        layout.add(signUpTopBar);
    }

    private void  addLogoutButton(HorizontalLayout layout){
        Button signUpTopBar = new Button("Logout");
        signUpTopBar.addClickListener(event -> { getUI().ifPresent(ui -> ui.navigate(""));});
        layout.add(signUpTopBar);
    }

    private void addLoginButton(HorizontalLayout layout){
            Button signUpTopBar = new Button("Log-in");
            signUpTopBar.addClickListener(event -> { getUI().ifPresent(ui -> ui.navigate("login"));});
            layout.add(signUpTopBar);

    }

    private void addSignupButton(HorizontalLayout layout){
            Button signUpTopBar = new Button("Sign-up");
            signUpTopBar.addClickListener(event -> { getUI().ifPresent(ui -> ui.navigate("sign-up"));});
            layout.add(signUpTopBar);

    }

}
