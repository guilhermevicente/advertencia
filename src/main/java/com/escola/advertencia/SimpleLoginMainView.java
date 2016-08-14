package com.escola.advertencia;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class SimpleLoginMainView extends CustomComponent implements View {

	class ButtonListener implements Button.ClickListener {
        String menuitem;
        public ButtonListener(String menuitem) {
            this.menuitem = menuitem;
        }

        @Override
        public void buttonClick(ClickEvent event) {
            // Navigate to a specific state
            getUI().getNavigator().navigateTo(menuitem);
        }
    }
	
    public static final String NAME = "";

    Label text = new Label();

    Button logout = new Button("Logout", new Button.ClickListener() {

        @Override
        public void buttonClick(ClickEvent event) {

            // "Logout" the user
            getSession().setAttribute("user", null);
            
            // Refresh this view, should redirect to login view
            getUI().getNavigator().navigateTo(NAME);
        }
    });
    
    // FIXME Teste
    Button teste = new Button("Teste", new Button.ClickListener() {

        @Override
        public void buttonClick(ClickEvent event) {
        	getUI().getNavigator().navigateTo("categoria");
        }
    });
    // FIXME Teste

    public SimpleLoginMainView() {
    	VerticalLayout menu = new VerticalLayout();
    	
    	menu.addComponent(new Button("Categoria", new ButtonListener("categoria")));
    	menu.addComponent(logout);
    	
        setCompositionRoot(new CssLayout(text, menu));
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // Get the user name from the session
        String username = String.valueOf(getSession().getAttribute("user"));

        // And show the username
        text.setValue("Hello " + username);
    }
}