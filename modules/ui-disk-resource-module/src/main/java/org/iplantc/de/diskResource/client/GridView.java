package org.iplantc.de.diskResource.client;

import org.iplantc.de.client.models.HasId;
import org.iplantc.de.client.models.diskResources.DiskResource;
import org.iplantc.de.client.models.diskResources.Folder;
import org.iplantc.de.diskResource.client.events.*;
import org.iplantc.de.diskResource.client.events.selection.CopyMetadataSelected;
import org.iplantc.de.diskResource.client.events.selection.EditInfoTypeSelected;
import org.iplantc.de.diskResource.client.events.selection.ManageCommentsSelected;
import org.iplantc.de.diskResource.client.events.selection.ManageMetadataSelected;
import org.iplantc.de.diskResource.client.events.selection.ManageSharingSelected;
import org.iplantc.de.diskResource.client.events.selection.ResetInfoTypeSelected;
import org.iplantc.de.diskResource.client.events.selection.ShareByDataLinkSelected;
import org.iplantc.de.diskResource.client.presenters.grid.proxy.FolderContentsLoadConfig;
import org.iplantc.de.diskResource.client.events.search.SubmitDiskResourceQueryEvent;
import org.iplantc.de.diskResource.client.views.grid.DiskResourceColumnModel;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.safehtml.client.HasSafeHtml;
import com.google.gwt.user.client.ui.IsWidget;

import com.sencha.gxt.data.shared.event.StoreUpdateEvent;
import com.sencha.gxt.data.shared.loader.BeforeLoadEvent;
import com.sencha.gxt.data.shared.loader.DataProxy;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.container.HasLayout;
import com.sencha.gxt.widget.core.client.grid.LiveGridCheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import java.util.List;

/**
 * Created by jstroot on 1/26/15.
 * @author jstroot
 */
public interface GridView extends IsWidget,
                                  HasLayout,
                                  DiskResourcePathSelectedEvent.HasDiskResourcePathSelectedEventHandlers,
                                  FolderSelectionEvent.FolderSelectionEventHandler,
                                  DiskResourceNameSelectedEvent.HasDiskResourceNameSelectedEventHandlers,
                                  DiskResourceSelectionChangedEvent.HasDiskResourceSelectionChangedEventHandlers,
                                  SubmitDiskResourceQueryEvent.SubmitDiskResourceQueryEventHandler {
    interface Appearance {

        String actionsColumnLabel();

        int actionsColumnWidth();

        String createdDateColumnLabel();

        int createdDateColumnWidth();

        String dataDragDropStatusText(int totalSelectionCount);

        String lastModifiedColumnLabel();

        int lastModifiedColumnWidth();

        int liveGridViewRowHeight();

        int liveToolItemWidth();

        String nameColumnLabel();

        int nameColumnWidth();

        String pathColumnLabel();

        int pathColumnWidth();

        String pathFieldLabel();

        int pathFieldLabelWidth();

        String pathFieldEmptyText();

        String permissionErrorMessage();

        int selectionStatusItemWidth();

        void setPagingToolBarStyle(ToolBar pagingToolBar);

        String sizeColumnLabel();

        int sizeColumnWidth();

        String gridViewEmptyText();

    }

    interface FolderContentsRpcProxy extends DataProxy<FolderContentsLoadConfig, PagingLoadResult<DiskResource>> {
        void setHasSafeHtml(HasSafeHtml centerHeader);
    }

    interface Presenter extends DiskResourceNameSelectedEvent.DiskResourceNameSelectedEventHandler,
                                ManageSharingSelected.ManageSharingSelectedEventHandler,
                                ManageMetadataSelected.ManageMetadataSelectedEventHandler,
                       CopyMetadataSelected.CopyMetadataSelectedEventHandler,
                                ShareByDataLinkSelected.ShareByDataLinkSelectedEventHandler,
                                RequestDiskResourceFavoriteEvent.RequestDiskResourceFavoriteEventHandler,
                                ManageCommentsSelected.ManageCommentsSelectedEventHandler,
                                FolderSelectionEvent.FolderSelectionEventHandler,
                                SubmitDiskResourceQueryEvent.SubmitDiskResourceQueryEventHandler,
                                StoreUpdateEvent.HasStoreUpdateHandlers<DiskResource>,
                                EditInfoTypeSelected.EditInfoTypeSelectedEventHandler,
                                ResetInfoTypeSelected.ResetInfoTypeSelectedHandler {

        interface Appearance {

            String comments();

            String commentsDialogHeight();

            String commentsDialogWidth();

            String copy();

            String copyPasteInstructions();

            String createDataLinksError();

            String favoritesError(String message);

            String markFavoriteError();

            String metadata();

            String metadataDialogHeight();

            String metadataDialogWidth();

            String metadataFormInvalid();

            String metadataHelp();

            String removeFavoriteError();

            String retrieveStatFailed();

            String searchDataResultsHeader(String searchText, int total, double executionTime_ms);

            String searchFailure();

            String shareLinkDialogHeight();

            int shareLinkDialogTextBoxWidth();

            String shareLinkDialogWidth();

            String shareFailure();

            String shareByLinkFailure();

            String metadataOverwriteWarning(String drName);

            String metadataManageFailure();

            String commentsManageFailure();

            String copyMetadata();

            String copyMetadataSuccess();

            String copyMetadataFailure();

        }

        void deSelectDiskResources();

        void doMoveDiskResources(Folder targetFolder, List<DiskResource> resources);

        Element findGridRow(Element eventTargetElement);

        int findGridRowIndex(Element targetRow);

        List<DiskResource> getAllDiskResources();

        List<DiskResource> getSelectedDiskResources();

        Folder getSelectedUploadFolder();

        GridView getView();

        boolean isSelectAllChecked();

        void setFilePreviewEnabled(boolean filePreviewEnabled);

        void setParentPresenter(DiskResourceView.Presenter parentPresenter);

        void setSelectedDiskResourcesById(List<? extends HasId> diskResourcesToSelect);

        void unRegisterHandler(EventHandler handler);

    }

    HandlerRegistration addBeforeLoadHandler(BeforeLoadEvent.BeforeLoadHandler<FolderContentsLoadConfig> handler);

    Element findGridRow(Element eventTargetElement);

    int findGridRowIndex(Element targetRow);

    DiskResourceColumnModel getColumnModel();

    PagingLoader<FolderContentsLoadConfig,PagingLoadResult<DiskResource>> getGridLoader();

    LiveGridCheckBoxSelectionModel getSelectionModel();

    void setSingleSelect();
}
