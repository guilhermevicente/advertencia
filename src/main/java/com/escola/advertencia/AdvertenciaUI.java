package com.escola.advertencia;

import javax.servlet.annotation.WebServlet;

import com.escola.advertencia.presentation.presenter.AdvertenciaPresenter;
import com.escola.advertencia.presentation.presenter.PerfilPresenter;
import com.escola.advertencia.presentation.presenter.TipoAdvertenciaPresenter;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@Theme("mytheme")
@Widgetset("com.escola.advertencia.MyAppWidgetset")
public class AdvertenciaUI extends UI {

	Navigator navigator;
	
	@Override
	protected void init(VaadinRequest request) {

		navigator = new Navigator(this, this);
		
		navigator.addView("perfil", new PerfilPresenter().getView());
		navigator.addView("tipoadvertencia", new TipoAdvertenciaPresenter().getView());
		navigator.addView("advertencia", new AdvertenciaPresenter().getView());

		getNavigator().addView(SimpleLoginView.NAME, SimpleLoginView.class);
		getNavigator().addView(SimpleLoginMainView.NAME, SimpleLoginMainView.class);
		getNavigator().addViewChangeListener(new ViewChangeListener() {

			@Override
			public boolean beforeViewChange(ViewChangeEvent event) {

				// Check if a user has logged in
				boolean isLoggedIn = getSession().getAttribute("user") != null;
				boolean isLoginView = event.getNewView() instanceof SimpleLoginView;

				if (!isLoggedIn && !isLoginView) {
					// Redirect to login view always if a user has not yet
					// logged in
					getNavigator().navigateTo(SimpleLoginView.NAME);
					return false;

				} else if (isLoggedIn && isLoginView) {
					// If someone tries to access to login view while logged in,
					// then cancel
					return false;
				}

				return true;
			}

			@Override
			public void afterViewChange(ViewChangeEvent event) {

			}
		});
	}
	
	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = AdvertenciaUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}