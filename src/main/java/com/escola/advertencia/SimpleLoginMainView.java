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

    

    public SimpleLoginMainView() {
    	VerticalLayout menu = new VerticalLayout();
    	
    	menu.setMargin(true);
    	menu.setSpacing(true);
    	menu.setWidth("100%");
    	
    	menu.addComponent(text);
    	
    	Button advertenciaBtn = new Button("Advertência", new ButtonListener("advertencia"));
    	advertenciaBtn.setWidth("100%");
    	menu.addComponent(advertenciaBtn);
    	
    	Button alunoBtn = new Button("Aluno", new ButtonListener("aluno"));
    	alunoBtn.setEnabled(false);
    	alunoBtn.setWidth("100%");
		menu.addComponent(alunoBtn);
		
    	Button funcionarioBtn = new Button("Funcionário", new ButtonListener("funcionario"));
    	funcionarioBtn.setEnabled(false);
    	funcionarioBtn.setWidth("100%");
		menu.addComponent(funcionarioBtn);
				
    	Button tipoAdvertenciaBtn = new Button("Tipo de Advertência", new ButtonListener("tipoadvertencia"));
    	tipoAdvertenciaBtn.setWidth("100%");
		menu.addComponent(tipoAdvertenciaBtn);
		
    	Button escolaBtn = new Button("Escola", new ButtonListener("escola"));
    	escolaBtn.setEnabled(false);
    	escolaBtn.setWidth("100%");
		menu.addComponent(escolaBtn);
		
    	Button responsavelBtn = new Button("Responsável", new ButtonListener("responsavel"));
    	responsavelBtn.setEnabled(false);
    	responsavelBtn.setWidth("100%");
		menu.addComponent(responsavelBtn);
		
    	Button usuarioBtn = new Button("Usuário", new ButtonListener("usuario"));
    	usuarioBtn.setEnabled(false);
    	usuarioBtn.setWidth("100%");
		menu.addComponent(usuarioBtn);
		
    	Button perfilBtn = new Button("Perfil", new ButtonListener("perfil"));
    	perfilBtn.setWidth("100%");
		menu.addComponent(perfilBtn);
		
    	Button bairroBtn = new Button("Bairro", new ButtonListener("bairro"));
    	bairroBtn.setEnabled(false);
    	bairroBtn.setWidth("100%");
		menu.addComponent(bairroBtn);
		
    	Button cidadeBtn = new Button("Cidade", new ButtonListener("cidade"));
    	cidadeBtn.setEnabled(false);
    	cidadeBtn.setWidth("100%");
    	menu.addComponent(cidadeBtn);
    	
    	Button estadoBtn = new Button("Estado", new ButtonListener("estado"));
    	estadoBtn.setEnabled(false);
    	estadoBtn.setWidth("100%");
		menu.addComponent(estadoBtn);
    	
    	Button logout = new Button("Logout", new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {

                // "Logout" the user
                getSession().setAttribute("user", null);
                
                // Refresh this view, should redirect to login view
                getUI().getNavigator().navigateTo(NAME);
            }
        });
    	logout.setWidth("100%");
    	logout.addStyleName("danger");
    	
    	menu.addComponent(logout);
    	
        setCompositionRoot(menu);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // Get the user name from the session
        String username = String.valueOf(getSession().getAttribute("user"));

        // And show the username
        text.setValue("Olá " + username);
    }
}