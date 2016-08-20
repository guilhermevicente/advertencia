package com.escola.advertencia;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class MainView extends CustomComponent implements View {

	private static final long serialVersionUID = 1254133243683901868L;

	class ButtonListener implements Button.ClickListener {

		private static final long serialVersionUID = 113449381873827815L;
		
		String menuitem;
        public ButtonListener(String menuitem) {
            this.menuitem = menuitem;
        }

        @Override
        public void buttonClick(ClickEvent event) {
            getUI().getNavigator().navigateTo(menuitem);
        }
    }
	
    public static final String NAME = "";

    Label text = new Label();

    

    public MainView() {
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

			private static final long serialVersionUID = -6545986834433887480L;

			@Override
            public void buttonClick(ClickEvent event) {

                getSession().setAttribute("user", null);
                
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
        String username = String.valueOf(getSession().getAttribute("user"));

        text.setValue("Olá " + username);
    }
}