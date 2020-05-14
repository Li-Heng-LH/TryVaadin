package com.vaadin.training.framework.exercises;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Main UI class
 */
@SuppressWarnings("serial")
public class ExercisesUI extends UI {

	private Navigator navigator;
	private VerticalLayout layout;

	@Override
	protected void init(VaadinRequest request) {
		layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSizeFull();
		setContent(layout);

		navigator = new Navigator(this, layout);
		navigator.addView("", new MainView(navigator));
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = ExercisesUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}