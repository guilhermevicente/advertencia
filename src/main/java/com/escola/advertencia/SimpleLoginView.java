package com.escola.advertencia;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.naming.NamingException;

import com.escola.advertencia.bo.UsuarioBO;
import com.escola.advertencia.utils.EJBUtility;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

public class SimpleLoginView extends CustomComponent implements View, Button.ClickListener {

	private static final long serialVersionUID = 53539130097835875L;

	public static final String NAME = "login";

	private final TextField user;

	private final PasswordField password;

	private final Button loginButton;

	public SimpleLoginView() {
		setSizeFull();

		user = new TextField("Usuário:");
		user.setWidth("300px");
		user.setRequired(true);
		user.setInputPrompt("Nome de usuário");
		user.setInvalidAllowed(false);

		password = new PasswordField("Senha:");
		password.setWidth("300px");
		password.addValidator(new PasswordValidator());
		password.setRequired(true);
		password.setValue("");
		password.setNullRepresentation("");

		loginButton = new Button("Login", this);
		loginButton.setClickShortcut(KeyCode.ENTER);

		VerticalLayout fields = new VerticalLayout(user, password, loginButton);
		fields.setCaption("Insira seu usuário e senha");
		fields.setSpacing(true);
		fields.setMargin(new MarginInfo(true, true, true, false));
		fields.setSizeUndefined();

		VerticalLayout viewLayout = new VerticalLayout(fields);
		viewLayout.setSizeFull();
		viewLayout.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
		viewLayout.setStyleName(Reindeer.LAYOUT_BLUE);
		setCompositionRoot(viewLayout);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		user.focus();
	}

	private static final class PasswordValidator extends AbstractValidator<String> {

		private static final long serialVersionUID = -4904975914234918967L;

		public PasswordValidator() {
			super("A senha não é válida");
		}

		@Override
		protected boolean isValidValue(String value) {
			if (value != null && (value.length() < 8 || !value.matches(".*\\d.*"))) {
				return false;
			}
			return true;
		}

		@Override
		public Class<String> getType() {
			return String.class;
		}
	}

	@Override
	public void buttonClick(ClickEvent event) {

		if (!user.isValid() || !password.isValid()) {
			return;
		}

		String username = user.getValue();
		String password = this.password.getValue();
		String password_md5 = "";
		
		try {
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
			byte messageDigest[] = algorithm.digest(password.getBytes("UTF-8"));
			StringBuilder hexString = new StringBuilder();
			
			for (byte b : messageDigest) {
			  hexString.append(String.format("%02X", 0xFF & b));
			}
			
			password_md5 = hexString.toString();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		UsuarioBO usuarioBO = null;
		
		try {
			usuarioBO = (UsuarioBO) EJBUtility.getInitialContext()
					.lookup("java:module/UsuarioBOImpl!com.escola.advertencia.bo.UsuarioBO");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		boolean isValid = usuarioBO.autenticar(username, password_md5);

		if (isValid) {

			getSession().setAttribute("user", username);

			getUI().getNavigator().navigateTo(MainView.NAME);//

		} else {

			this.password.setValue(null);
			this.password.focus();

		}
	}
}