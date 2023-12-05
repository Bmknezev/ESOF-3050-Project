//-----------------------------------------------------------------
// Listable.java
// Group 2
// Description: Interface defining methods for retrieving information
//              used for listing items.
// Created By:
// Edited By:
// Approved By: Braydon, Francisco, Liam
// Variables:
//   - getNameListable(): String - Retrieves the name of the listable item.
//   - getCategoryListable(): String - Retrieves the category of the listable item.
//   - getIDListable(): int - Retrieves the ID of the listable item.
//-----------------------------------------------------------------

package messages.client;

public interface Listable {

    public abstract String getNameListable();
    // username - user/admin
    // device name - smart devices

    public abstract String getCategoryListable();
    // user/admin - user/admin
    // device type - smart device

    public abstract int getIDListable();
}
