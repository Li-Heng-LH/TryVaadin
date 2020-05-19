package me.liheng;

import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class MainView extends VerticalLayout {
    private CustomerService service = CustomerService.getInstance();
    private Grid<Customer> grid = new Grid<>(Customer.class);
    private TextField filterText = new TextField();

    public MainView() {
        grid.setColumns("firstName", "lastName", "status");
        updateList();

        filterText.setPlaceholder("Filter by name...");
        filterText.setValueChangeMode(ValueChangeMode.EAGER);
        filterText.addValueChangeListener(e -> updateList());

        addComponents(filterText, grid);
    }

    public void updateList() {
        grid.setItems(service.findAll(filterText.getValue()));
    }

}
