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


abstract class ViewTemplate extends VerticalLayout {
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

        add(header);
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

    abstract void addManageStoresButton(HorizontalLayout layout);

    abstract void addLoginButton(HorizontalLayout layout);

    abstract void  addLogoutButton(HorizontalLayout layout);

    abstract  void addSignupButton(HorizontalLayout layout);


}
