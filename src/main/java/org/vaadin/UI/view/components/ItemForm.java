package org.vaadin.UI.view.components;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToBigDecimalConverter;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.vaadin.UI.Presenter.InventoryPresenter;
import org.vaadin.UI.model.DTOs.ItemDTO;

public class ItemForm extends Div {

    private final InventoryPresenter presenter;
    private VerticalLayout content;
    private TextField itemName;
    private TextField price;
    private TextField quantity;

    private Button save;
    private Button discard;
    private Button cancel;
    private Button delete;

    private Binder<ItemDTO> binder;
    private ItemDTO currentItem;

    private static class PriceConverter extends StringToBigDecimalConverter {

        public PriceConverter() {
            super(BigDecimal.ZERO, "Cannot convert value to a number.");
        }

        @Override
        protected NumberFormat getFormat(Locale locale) {
            final NumberFormat format = super.getFormat(locale);
            if (format instanceof DecimalFormat) {
                format.setMaximumFractionDigits(2);
                format.setMinimumFractionDigits(2);
            }
            return format;
        }
    }

    private static class StockCountConverter extends StringToIntegerConverter {

        public StockCountConverter() {
            super(0, "Could not convert value to " + Integer.class.getName() + ".");
        }

        @Override
        protected NumberFormat getFormat(Locale locale) {
            final DecimalFormat format = new DecimalFormat();
            format.setMaximumFractionDigits(0);
            format.setDecimalSeparatorAlwaysShown(false);
            format.setParseIntegerOnly(true);
            format.setGroupingUsed(false);
            return format;
        }
    }

    public ItemForm(InventoryPresenter presenter) {
        this.presenter = presenter;
        setClassName("product-form");

        content = new VerticalLayout();
        content.setSizeUndefined();
        content.addClassName("product-form-content");
        add(content);

        itemName = new TextField("Product name");
        itemName.setWidth("100%");
        itemName.setRequired(true);
        itemName.setValueChangeMode(ValueChangeMode.EAGER);
        content.add(itemName);

        price = new TextField("Price");
        price.addThemeVariants(TextFieldVariant.LUMO_ALIGN_RIGHT);
        price.setValueChangeMode(ValueChangeMode.EAGER);

        quantity = new TextField("In stock");
        quantity.addThemeVariants(TextFieldVariant.LUMO_ALIGN_RIGHT);
        quantity.setValueChangeMode(ValueChangeMode.EAGER);

        final HorizontalLayout horizontalLayout = new HorizontalLayout(price, quantity);
        horizontalLayout.setWidth("100%");
        horizontalLayout.setFlexGrow(1, price, quantity);
        content.add(horizontalLayout);

        binder = new Binder<>(ItemDTO.class);
        binder.forField(price).withConverter(new PriceConverter()).bind("totalPrice");
        binder.forField(quantity).withConverter(new StockCountConverter()).bind("quantity");
        binder.bindInstanceFields(this);

        binder.addStatusChangeListener(event -> {
            final boolean isValid = !event.hasValidationErrors();
            final boolean hasChanges = binder.hasChanges();
            save.setEnabled(hasChanges && isValid);
            discard.setEnabled(hasChanges);
        });

        save = new Button("Save");
        save.setWidth("100%");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.addClickListener(event -> {
            if (currentItem != null && binder.writeBeanIfValid(currentItem)) {
                presenter.onSavingItem(currentItem);
            }
        });
        save.addClickShortcut(Key.KEY_S, KeyModifier.CONTROL);

        discard = new Button("Discard changes");
        discard.setWidth("100%");
        discard.addClickListener(event -> presenter.onEditingItem(currentItem));

        cancel = new Button("Cancel");
        cancel.setWidth("100%");
        cancel.addClickListener(event -> presenter.onCancleItem());
        cancel.addClickShortcut(Key.ESCAPE);
        getElement().addEventListener("keydown", event -> presenter.onCancleItem()).setFilter("event.key == 'Escape'");

        delete = new Button("Delete");
        delete.setWidth("100%");
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_PRIMARY);
        delete.addClickListener(event -> {
            if (currentItem != null) {
                presenter.onDeleteItem(currentItem);
            }
        });

        content.add(save, discard, delete, cancel);
    }

    public void setItem(ItemDTO item) {
        binder.setBean(item);
    }
}
