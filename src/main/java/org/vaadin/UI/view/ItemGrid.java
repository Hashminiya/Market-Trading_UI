package org.vaadin.UI.view;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import org.vaadin.UI.model.DTOs.ItemDTO;

import java.text.DecimalFormat;
import java.util.Comparator;

public class ItemGrid extends Grid<ItemDTO> {
    public ItemGrid() {
    setSizeFull();

    addColumn(ItemDTO::getItemName).setHeader("Item name")
                .setFlexGrow(20).setSortable(true).setKey("itemname");

    // Format and add " €" to price
    final DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);

    addColumn(ItemDTO -> decimalFormat.format(ItemDTO.getItemPrice()) + " $")
            .setHeader("Price").setTextAlign(ColumnTextAlign.END)
                .setComparator(Comparator.comparing(ItemDTO::getItemPrice))
            .setFlexGrow(3).setKey("price");

    addColumn(ItemDTO -> ItemDTO.getItemQuantity() == 0 ? "-"
            : Integer.toString(ItemDTO.getItemQuantity()))
            .setHeader("Stock count")
                        .setTextAlign(ColumnTextAlign.END)
                        .setComparator(
            Comparator.comparingInt(ItemDTO::getItemQuantity))
            .setFlexGrow(3).setKey("stock");

    // Show all categories the product is in, separated by commas
//    addColumn(this::formatCategories).setHeader("Category").setFlexGrow(12)
//                .setKey("category");

    // If the browser window size changes, check if all columns fit on
    // screen
    // (e.g. switching from portrait to landscape mode)
        UI.getCurrent().getPage().addBrowserWindowResizeListener(
            e -> setColumnVisibility(e.getWidth()));
}

    private void setColumnVisibility(int width) {
        if (width > 800) {
            getColumnByKey("itemname").setVisible(true);
            getColumnByKey("price").setVisible(true);
            getColumnByKey("stock").setVisible(true);
            //getColumnByKey("category").setVisible(true);
        } else if (width > 550) {
            getColumnByKey("productname").setVisible(true);
            getColumnByKey("price").setVisible(true);
            getColumnByKey("stock").setVisible(false);
            //getColumnByKey("category").setVisible(true);
        } else {
            getColumnByKey("productname").setVisible(true);
            getColumnByKey("price").setVisible(true);
            getColumnByKey("stock").setVisible(false);
            //getColumnByKey("category").setVisible(false);
        }
    }


    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);

        // fetch browser width
        UI.getCurrent().getInternals().setExtendedClientDetails(null);
        UI.getCurrent().getPage().retrieveExtendedClientDetails(e -> {
            setColumnVisibility(e.getBodyClientWidth());
        });
    }

    public ItemDTO getSelectedRow() {
        return asSingleSelect().getValue();
    }

    public void refresh(ItemDTO product) {
        getDataCommunicator().refresh(product);
    }

//    private String formatCategories(ItemDTO product) {
//        if (product.getCategory() == null || product.getCategory().isEmpty()) {
//            return "";
//        }
//        return product.getCategory().stream()
//                .sorted(Comparator.comparing(Category::getId))
//                .map(Category::getName).collect(Collectors.joining(", "));
//    }
}
