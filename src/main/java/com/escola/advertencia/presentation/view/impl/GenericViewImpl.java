package com.escola.advertencia.presentation.view.impl;

import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

public class GenericViewImpl extends VerticalLayout {
	public GenericViewImpl() {
		
		Button voltar = new Button("Voltar");
		voltar.addClickListener(click -> { getUI().getNavigator().navigateTo("");});
		
		this.addComponent(voltar);
	}
}