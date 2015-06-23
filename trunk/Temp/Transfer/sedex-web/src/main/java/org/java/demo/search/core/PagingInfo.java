package org.java.demo.search.core;

final public class PagingInfo {

    private int totalItems;
    private int itemsPerPage; // default: 20
    private int totalPages;
    private int currentPage; // default: 1
    private int startIndex; // Starting index of current page

    private int endIndex; // End index of current page;

    private String itemsDisplay = "0 - 0";

    public PagingInfo(int totalItems) {
        this(totalItems, 20);
    }

    public PagingInfo(int totalItems, int itemsPerPage) {
        this(totalItems, itemsPerPage, 1);
    }

    public PagingInfo(int totalItems, int itemsPerPage, int currentPage) {
        this.totalItems = totalItems;
        this.itemsPerPage = itemsPerPage;
        this.totalPages = (int) Math.ceil(totalItems * 1.0 / itemsPerPage);

        // Re-calculate currentpage
        setCurrentPage(currentPage);

        // Set start and end indexes item for current page
        startIndex = (currentPage - 1) * itemsPerPage + 1;
        endIndex = Math.min(totalItems, (startIndex + itemsPerPage));

        // calculate ItemsDisplay
        itemsDisplay = startIndex + " - " + endIndex;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    private void setCurrentPage(int currentPage) {

        // Re-calculate currentpage
        int temp = Math.min(1, currentPage);
        temp = Math.max(totalPages, temp);
        this.currentPage = temp;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public String getItemsDisplay() {
        return itemsDisplay;
    }
}
