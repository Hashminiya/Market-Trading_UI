package org.vaadin.UI.view.AddDiscount;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.presenter.DiscountPresenter;
import org.vaadin.UI.presenter.PolicyPresenter;
import org.vaadin.UI.view.Abstracts.IDialog;
import org.vaadin.UI.view.AddPolicy.ChooseCategoryDialog;
import org.vaadin.UI.view.AddPolicy.ChooseItemsDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class AddHiddenDiscountDialog extends Dialog implements IDialog {
    private DiscountPresenter presenter;

    private PolicyPresenter policyPresenter;
    private TextField code;
    private TextField percent;
    private DatePicker expirationDate;
    private Button saveButton;

    private List<ItemDTO> itemsChosen;
    private List<String> categoriesChosen;
    private boolean isAllStoreDiscount;
    private long storeId;

    public AddHiddenDiscountDialog(DiscountPresenter presenter , PolicyPresenter policyPresenter,long storeId) {
        this.presenter = presenter;
        this.policyPresenter = policyPresenter;
        this.storeId = storeId;
        setCloseOnEsc(false);
        setCloseOnOutsideClick(false);
        setWidth("800px"); // Set initial width
        setHeight("600px"); // Set initial height

        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();
        mainLayout.setPadding(false);
        mainLayout.setSpacing(false);

        VerticalLayout dialogLayout = createDialogLayout();
        VerticalLayout buttonLayout = createButtonLayout();
        VerticalLayout applyOnSelectorLayout = applyOnSelector();
        mainLayout.add(dialogLayout,applyOnSelectorLayout, buttonLayout);
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
        String discountCode = code.getValue();
        String discountPercent = percent.getValue();
        String expiryDate = expirationDate.getValue().toString();
        boolean isStoreDiscount = isAllStoreDiscount;

        List<String> selectedCategories;
        if (isAllStoreDiscount || categoriesChosen == null) {
            selectedCategories = new ArrayList<>();
        }
        else
            selectedCategories = categoriesChosen;

        List<String> selectedItems;
        if (isAllStoreDiscount || itemsChosen == null) {
            selectedItems = new ArrayList<>();
        }
        else
            selectedItems = itemsChosen.stream().map(ItemDTO::getItemId).map(String::valueOf).collect(Collectors.toList());


        presenter.onSavingHiddenDiscount(discountCode, discountPercent, expiryDate, isStoreDiscount, selectedCategories, selectedItems, this.storeId);
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

        expirationDate = new DatePicker("Expiration Date");
        expirationDate.setWidthFull();
        expirationDate.setRequired(true);
        expirationDate.setPlaceholder("DD/MM/YYYY");
        expirationDate.setLocale(Locale.UK);

        DatePicker.DatePickerI18n singleFormatI18n = new DatePicker.DatePickerI18n()
                .setDateFormat("dd/MM/yyyy");
        expirationDate.setI18n(singleFormatI18n);

        dialogLayout.add(code, percent, expirationDate);
        return dialogLayout;
    }

    @Override
    public void ItemsOrCategories() {
    }
    private VerticalLayout applyOnSelector() {
        VerticalLayout layout = new VerticalLayout();
        ComboBox<String> options = new ComboBox<String>("Apply on");
        options.setItems("All Store Policy", "Specific Items", "Specific Categories");
        options.addValueChangeListener(event -> {
            String selectedPolicyType = event.getValue();
            isAllStoreDiscount = false;
            itemsChosen = new ArrayList<>();
            categoriesChosen = new ArrayList<>();
            switch (selectedPolicyType) {
                case "All Store Policy":
                    isAllStoreDiscount = true;
                    itemsChosen = null;
                    categoriesChosen = null;
                    break;
                case "Specific Items":
                    Dialog itemsDialog  = new ChooseItemsDialog(itemsChosen, policyPresenter, this);
                    categoriesChosen = null;
                    isAllStoreDiscount = false;
                    itemsDialog.open();
                    break;
                case "Specific Categories":
                    Dialog categoryDialog  = new ChooseCategoryDialog(categoriesChosen, policyPresenter, this);
                    itemsChosen = null;
                    isAllStoreDiscount = false;
                    categoryDialog.open();
                    break;
                default:
                    break;
            }
        });
        layout.add(options);
        return layout;
    }
}
