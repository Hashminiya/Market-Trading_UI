package org.vaadin.UI.view.AddDiscount;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToDoubleConverter;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import org.vaadin.UI.model.DTOs.Discounts.DiscountDTO;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.model.DTOs.Policies.PolicyDTO;
import org.vaadin.UI.presenter.DiscountPresenter;
import org.vaadin.UI.presenter.InventoryPresenter;
import org.vaadin.UI.presenter.PolicyPresenter;
import org.vaadin.UI.view.Abstracts.IDialog;
import org.vaadin.UI.view.AddPolicy.AddPolicyDialog;

import java.util.List;

public class AddHiddenDiscountDialog extends Dialog implements IDialog {
    private DiscountPresenter presenter;
    private TextField code;
    private TextField expirationDate;
    private TextField percent;
    private Button saveButton;
    private Button updateButton;
    public AddHiddenDiscountDialog (DiscountPresenter presenter){
        this.presenter = presenter;

        setCloseOnEsc(false);
        setCloseOnOutsideClick(false);

        setWidth("800px");  // Set initial width
        setHeight("600px"); // Set initial height

        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();
        mainLayout.setPadding(false);
        mainLayout.setSpacing(false);

        VerticalLayout dialogLayout = createDialogLayout();
        VerticalLayout buttonLayout = createButtonLayout();

        mainLayout.add(dialogLayout, buttonLayout);
        mainLayout.setFlexGrow(1, dialogLayout);
        mainLayout.setFlexGrow(0, buttonLayout);

        add(mainLayout);
    }
    private VerticalLayout createButtonLayout() {
        VerticalLayout buttonLayout = new VerticalLayout();
        buttonLayout.setPadding(true);
        buttonLayout.setSpacing(true);
        buttonLayout.setAlignItems(FlexComponent.Alignment.STRETCH);

        saveButton = new Button("Save", e -> save());
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        saveButton.setWidthFull();

        buttonLayout.add(saveButton);

        return buttonLayout;
    }
    private void save() {
        presenter.onSavingHiddenDiscount();
        close();
    }

    private VerticalLayout createDialogLayout() {
        VerticalLayout dialogLayout = new VerticalLayout();
        dialogLayout.setPadding(true);
        dialogLayout.setSpacing(true);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);

        code = new TextField("Discount code");
        code.setWidthFull();
        code.setRequired(true);

        percent = new TextField("Discount Percent");
        percent.setWidthFull();
        percent.setRequired(true);

        expirationDate = new TextField("Expiration Date");
        expirationDate.setWidthFull();
        expirationDate.setRequired(true);

        dialogLayout.add(code,percent,expirationDate);


        return dialogLayout;
    }
    @Override
    public void ItemsOrCategories() {

    }
}
