package com.vaadin.training.framework.exercises.ex2;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

public class WrappedTextField extends CustomComponent {

	private static final long serialVersionUID = 1L;

	private TextField textField;
	private Label errorMessageLabel;

	public WrappedTextField() {

		// Create an instance of TextField and Label and assign them to the fields declared above
		textField = new TextField();
		errorMessageLabel = new Label();

		// Create a new HorizontalLayout and set it as the composition root of this CustomComponent
		// The composition root MUST be set
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		setCompositionRoot(horizontalLayout);

		// Add the textField and errorMessageLabel to the HorizontalLayout
		horizontalLayout.addComponent(textField);
		horizontalLayout.addComponent(errorMessageLabel);
	}

	public String getText() {
		return textField.getValue();
	}

	public void setErrorMessage(String errorMessage) {
		errorMessageLabel.setValue(errorMessage);
	}
}
