package org.vaadin.UI.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.Presenter.PaymentPresenter;


@PageTitle("Payment Form")
@Route("payment")
public class PaymentView extends ViewTemplate {
    private TextField cardNumber;
    private TextField cardholderName;
    private Select<Integer> month;
    private Select<Integer> year;
    private ExpirationDateField expiration;
    private PasswordField cvv;
    private Button submit;
    private Label totalPrice;
    private PaymentPresenter presenter;



    private String CARD_REGEX = "^\\d{16}$";
    private String CVV_REGEX = "^\\d{3}$";
    public PaymentView() {
        presenter = new PaymentPresenter(this);
        VerticalLayout restOfPage = new VerticalLayout();
        restOfPage.setSizeFull();
        restOfPage.setJustifyContentMode(JustifyContentMode.CENTER);
        restOfPage.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        restOfPage.add(createTotalPriceLabel("$0.00"));
        restOfPage.add(createFormLayout());
        restOfPage.add(createButtonLayout());

        submit.addClickListener(e -> {
            Notification.show("Not implemented");
            presenter.onPay(cardNumber.getValue(),expiration.getValue(), cvv.getValue());
        });
        add(restOfPage);
    }

    private Component createTotalPriceLabel(String setTotalPrice) {
        totalPrice = new Label("Total Price: " + setTotalPrice);
        totalPrice.addClassName("total-price-label");
        return totalPrice;
    }

    private Component createFormLayout() {
        cardNumber = new TextField("Credit card number");
        cardNumber.setPlaceholder("1234 5678 9123 4567");
        cardNumber.setPattern(CARD_REGEX);
        cardNumber.setAllowedCharPattern("[\\d ]");
        cardNumber.setRequired(true);
        cardNumber.setErrorMessage("Please enter a valid credit card number");

        cardholderName = new TextField("Cardholder name");

        month = new Select<>();
        month.setPlaceholder("Month");
        month.setItems(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);

        year = new Select<>();
        year.setPlaceholder("Year");
        year.setItems(24, 25, 26, 27, 28, 29, 30, 31, 32);

        expiration = new ExpirationDateField("Expiration date", month, year);

        cvv = new PasswordField("CVV");
        cvv.setPattern(CVV_REGEX);
        cvv.setAllowedCharPattern("[\\d ]");
        cvv.setRequired(true);
        cvv = new PasswordField("CVV");



        FormLayout formLayout = new FormLayout();
        formLayout.add(cardNumber, cardholderName, expiration, cvv);
        formLayout.setMaxWidth("400px");
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1)
        );

        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");

        submit = new Button("Submit");
        submit.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        buttonLayout.add(submit);
        return buttonLayout;
    }

    public void showNotification(String message) {
        Notification.show(message);
    }

    private class ExpirationDateField extends CustomField<String> {
        public ExpirationDateField(String label, Select<Integer> month, Select<Integer> year) {
            setLabel(label);
            HorizontalLayout layout = new HorizontalLayout(month, year);
            layout.setFlexGrow(1.0, month, year);
            month.setWidth("100px");
            year.setWidth("100px");
            add(layout);
        }

        @Override
        protected String generateModelValue() {
            return month.getValue() + "/" + year.getValue();
        }

        @Override
        protected void setPresentationValue(String newPresentationValue) {
            // Do nothing
        }

        public void setTotalPrice(){

        }
    }
}
