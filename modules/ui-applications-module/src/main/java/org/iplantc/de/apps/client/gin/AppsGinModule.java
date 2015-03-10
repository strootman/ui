package org.iplantc.de.apps.client.gin;

import org.iplantc.de.apps.client.AppCategoriesView;
import org.iplantc.de.apps.client.AppDetailsView;
import org.iplantc.de.apps.client.AppsGridView;
import org.iplantc.de.apps.client.AppsToolbarView;
import org.iplantc.de.apps.client.AppsView;
import org.iplantc.de.apps.client.SubmitAppForPublicUseView;
import org.iplantc.de.apps.client.gin.factory.AppCategoriesViewFactory;
import org.iplantc.de.apps.client.gin.factory.AppDetailsViewFactory;
import org.iplantc.de.apps.client.gin.factory.AppsGridViewFactory;
import org.iplantc.de.apps.client.gin.factory.AppsToolbarViewFactory;
import org.iplantc.de.apps.client.gin.factory.AppsViewFactory;
import org.iplantc.de.apps.client.presenter.AppsViewPresenterImpl;
import org.iplantc.de.apps.client.presenter.categories.AppCategoriesPresenterImpl;
import org.iplantc.de.apps.client.presenter.details.AppDetailsViewPresenterImpl;
import org.iplantc.de.apps.client.presenter.grid.AppsGridPresenterImpl;
import org.iplantc.de.apps.client.presenter.submit.SubmitAppForPublicPresenter;
import org.iplantc.de.apps.client.presenter.toolBar.AppsToolbarPresenterImpl;
import org.iplantc.de.apps.client.views.AppsViewImpl;
import org.iplantc.de.apps.client.views.categories.AppCategoriesViewImpl;
import org.iplantc.de.apps.client.views.details.AppDetailsViewImpl;
import org.iplantc.de.apps.client.views.details.dialogs.AppDetailsDialog;
import org.iplantc.de.apps.client.views.grid.AppsGridViewImpl;
import org.iplantc.de.apps.client.views.submit.SubmitAppForPublicUseViewImpl;
import org.iplantc.de.apps.client.views.toolBar.AppsViewToolbarImpl;
import org.iplantc.de.client.models.apps.App;
import org.iplantc.de.client.models.apps.AppCategory;
import org.iplantc.de.client.services.AppMetadataServiceFacade;
import org.iplantc.de.client.services.impl.AppMetadataServiceFacadeImpl;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.inject.TypeLiteral;

import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.TreeStore;

/**
 * @author jstroot
 */
public class AppsGinModule extends AbstractGinModule {

    @Override
    protected void configure() {
        bind(new TypeLiteral<TreeStore<AppCategory>>() {})
            .toProvider(AppCategoryTreeStoreProvider.class);
        bind(new TypeLiteral<ListStore<App>>() {})
            .toProvider(AppListStoreProvider.class);

        // KLUDGE Bind AppsView in DEGinModule to get around Gin double-binding with Belphegor
//        bind(AppsView.class).to(AppsViewImpl.class);
        bind(SubmitAppForPublicUseView.class).to(SubmitAppForPublicUseViewImpl.class);
        bind(SubmitAppForPublicUseView.Presenter.class).to(SubmitAppForPublicPresenter.class);
        bind(AppMetadataServiceFacade.class).to(AppMetadataServiceFacadeImpl.class);

        // Main View
        install(new GinFactoryModuleBuilder()
                    .implement(AppsView.class, AppsViewImpl.class)
                    .build(AppsViewFactory.class));
        bind(AppsView.Presenter.class).to(AppsViewPresenterImpl.class);

        // Categories View
        install(new GinFactoryModuleBuilder()
                    .implement(AppCategoriesView.class, AppCategoriesViewImpl.class)
                    .build(AppCategoriesViewFactory.class));
        bind(AppCategoriesView.Presenter.class).to(AppCategoriesPresenterImpl.class);

        // Grid View
        install(new GinFactoryModuleBuilder()
                    .implement(AppsGridView.class, AppsGridViewImpl.class)
                    .build(AppsGridViewFactory.class));
        bind(AppsGridView.Presenter.class).to(AppsGridPresenterImpl.class);

        // Toolbar View
        install(new GinFactoryModuleBuilder()
                    .implement(AppsToolbarView.class, AppsViewToolbarImpl.class)
                    .build(AppsToolbarViewFactory.class));
        bind(AppsToolbarView.Presenter.class).to(AppsToolbarPresenterImpl.class);

        // Details
        install(new GinFactoryModuleBuilder()
                    .implement(AppDetailsView.class, AppDetailsViewImpl.class)
                    .build(AppDetailsViewFactory.class));
        bind(AppDetailsView.Presenter.class).to(AppDetailsViewPresenterImpl.class);
        bind(AppDetailsDialog.class);
    }

}
