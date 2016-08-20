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

	private static final long serialVersionUID = -8212548596849760705L;
	
	Navigator navigator;
	
	@Override
	protected void init(VaadinRequest request) {

		navigator = new Navigator(this, this);
		
		navigator.addView("perfil", new PerfilPresenter().getView());
		navigator.addView("tipoadvertencia", new TipoAdvertenciaPresenter().getView());
		navigator.addView("advertencia", new AdvertenciaPresenter().getView());

		getNavigator().addView(SimpleLoginView.NAME, SimpleLoginView.class);
		getNavigator().addView(MainView.NAME, MainView.class);
		getNavigator().addViewChangeListener(new ViewChangeListener() {

			private static final long serialVersionUID = -4687412248788612075L;

			@Override
			public boolean beforeViewChange(ViewChangeEvent event) {

				boolean isLoggedIn = getSession().getAttribute("user") != null;
				boolean isLoginView = event.getNewView() instanceof SimpleLoginView;

				if (!isLoggedIn && !isLoginView) {
					getNavigator().navigateTo(SimpleLoginView.NAME);
					return false;

				} else if (isLoggedIn && isLoginView) {
					return false;
				}

				return true;
			}

			@Override
			public void afterViewChange(ViewChangeEvent event) {

			}
		});
	}
	
	@SuppressWarnings("serial")
	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = AdvertenciaUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}