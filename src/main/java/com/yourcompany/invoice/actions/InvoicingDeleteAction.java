package com.yourcompany.invoice.actions;

import java.util.*;

import org.openxava.actions.*;
import org.openxava.model.*;

public class InvoicingDeleteAction extends ViewBaseAction {

    public void execute() throws Exception {
        if(!getView().getMetaModel() // Metadata about the current entity
                .containsMetaProperty("deleted")) { // Is there a 'deleted' property?
            executeAction("CRUD.delete");
            return;
        }
        Map<String, Object> values =
                new HashMap<>(); // The values to modify in the entity
        values.put("deleted", true); // We set true to 'deleted' property
        MapFacade.setValues( // Modifies the values of the indicated entity
                getModelName(), // A method from ViewBaseAction
                getView().getKeyValues(), // The key of the entity to modify
                values
        ); // The values to change
        resetDescriptionsCache(); // Clears the caches for combos
        addMessage("object_deleted", getModelName());
        getView().clear();
        getView().setKeyEditable(true);
        getView().setEditable(false); // The view is left as not editable
    }
}
