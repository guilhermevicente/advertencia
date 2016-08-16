package com.escola.advertencia.utils;

import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.TextField;

public class IntegerField extends TextField implements TextChangeListener {

	private static final long serialVersionUID = -9130963670617118208L;
	
	String lastValue;

	public IntegerField() {
		init();
	}
	
	public IntegerField(String caption) {
		setCaption(caption);
		init();
	}
	
	private void init() {
		setImmediate(true);
		setTextChangeEventMode(TextChangeEventMode.EAGER);
		addTextChangeListener(this);
	}

	@Override
	public void textChange(TextChangeEvent event) {
		String text = event.getText();
		try {
			new Integer(text);
			lastValue = text;
		} catch (NumberFormatException e) {
			setValue(lastValue);
		}
	}
}