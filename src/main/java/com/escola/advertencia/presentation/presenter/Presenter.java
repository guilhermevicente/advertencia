package com.escola.advertencia.presentation.presenter;

import javax.ejb.Local;

import com.vaadin.navigator.View;

@Local
public interface Presenter {
	
	public View getView(); 

}
