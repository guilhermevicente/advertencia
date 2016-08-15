package com.escola.advertencia;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.naming.NamingException;

import com.escola.advertencia.bo.UsuarioBO;
import com.escola.advertencia.utils.EJBUtility;
import com.vaadin.data.validator.AbstractValidator;
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

	public static final String NAME = "login";

	private final TextField user;

	private final PasswordField password;

	private final Button loginButton;

	public SimpleLoginView() {
		setSizeFull();

		// Create the user input field
		user = new TextField("Usuário:");
		user.setWidth("300px");
		user.setRequired(true);
		user.setInputPrompt("Nome de usuário");
		// user.addValidator(new EmailValidator("Username must be an email address"));
		user.setInvalidAllowed(false);

		// Create the password input field
		password = new PasswordField("Senha:");
		password.setWidth("300px");
		password.addValidator(new PasswordValidator());
		password.setRequired(true);
		password.setValue("");
		password.setNullRepresentation("");

		// Create login button
		loginButton = new Button("Login", this);

		// Add both to a panel
		VerticalLayout fields = new VerticalLayout(user, password, loginButton);
		fields.setCaption("Insira seu usuário e senha");
		fields.setSpacing(true);
		fields.setMargin(new MarginInfo(true, true, true, false));
		fields.setSizeUndefined();

		// The view root layout
		VerticalLayout viewLayout = new VerticalLayout(fields);
		viewLayout.setSizeFull();
		viewLayout.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
		viewLayout.setStyleName(Reindeer.LAYOUT_BLUE);
		setCompositionRoot(viewLayout);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// focus the username field when user arrives to the login view
		user.focus();
	}

	// Validator for validating the passwords
	private static final class PasswordValidator extends AbstractValidator<String> {

		public PasswordValidator() {
			super("The password provided is not valid");
		}

		@Override
		protected boolean isValidValue(String value) {
			//
			// Password must be at least 8 characters long and contain at least
			// one number
			//
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

		//
		// Validate the fields using the navigator. By using validors for the
		// fields we reduce the amount of queries we have to use to the database
		// for wrongly entered passwords
		//
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

		//
		// Validate username and password with database here. For examples sake
		// I use a dummy username and password.
		//
		
		UsuarioBO usuarioBO = null;
		
		try {
			usuarioBO = (UsuarioBO) EJBUtility.getInitialContext()
					.lookup("java:module/UsuarioBOImpl!com.escola.advertencia.bo.UsuarioBO");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// boolean isValid = username.equals("test@test.com") && password.equals("passw0rd");
		
		boolean isValid = usuarioBO.autenticar(username, password_md5);

		if (isValid) {

			// Store the current user in the service session
			getSession().setAttribute("user", username);

			// Navigate to main view
			getUI().getNavigator().navigateTo(SimpleLoginMainView.NAME);//

		} else {

			// Wrong password clear the password field and refocuses it
			this.password.setValue(null);
			this.password.focus();

		}
	}
}