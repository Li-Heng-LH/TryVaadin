package com.vaadin.training.framework.exercises.ex2;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

public class CreatingCustomComponent extends CustomComponent implements View {

	private static final long serialVersionUID = 1L;
	private WrappedTextField wrappedTextField = new WrappedTextField();

	public CreatingCustomComponent() {
		VerticalLayout content = new VerticalLayout();
		content.addComponent(wrappedTextField);
		content.addComponent(new Button("Validate", event -> {
			if (wrappedTextField.getText().isEmpty()) {
				wrappedTextField.setErrorMessage("This field is required");
			} else {
				wrappedTextField.setErrorMessage(null);
			}
		}));
		setCompositionRoot(content);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// Navigator method, ignore for now
	}
}