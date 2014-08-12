package jp.co.inte.crm.common.dto.web;

import java.util.List;

import jp.co.inte.cspfw.util.FwListUtils;

/**
 * Pagination Information utility class
 */
public final class PagingInfo {

    /** Total items found */
    private int totalItems;

    /** Number of items per pagination page */
    private int itemsPerPage; // default: 20

    /** Total page */
    private int totalPages;

    /** Current pagination page */
    private int currentPage; // default: 1

    /** Starting index of current page */
    private int startIndex;

    /** End index of current page */
    private int endIndex;

    /** itemsDisplay String. Ex: 1-20 */
    private String itemsDisplay = "0";

    /** Constructor */
    public PagingInfo() {
    }

    /**
     * Constructor
     * 
     * @param totalItems Total Items
     */
    public PagingInfo(int totalItems) {
        this(totalItems, 20);
    }

    /**
     * Constructor
     * 
     * @param totalItems Total Items
     * @param itemsPerPage Items per Page
     */
    public PagingInfo(int totalItems, int itemsPerPage) {
        this(totalItems, itemsPerPage, 1);
    }

    /**
     * Constructor
     * 
     * @param totalItems Total Items
     * @param itemsPerPage Items per Page
     * @param currentPage Current page
     */
    public PagingInfo(int totalItems, int itemsPerPage, int currentPage) {
        this.totalItems = totalItems;
        this.itemsPerPage = itemsPerPage;
        this.currentPage = currentPage;

        reCalculateAll();
    }

    /** Re Calculate all values */
    public void reCalculateAll() {
        this.totalPages = (int) Math.ceil(totalItems * 1.0 / itemsPerPage);

        // Re-calculate currentPage
        int temp = Math.min(totalPages, currentPage);
        temp = Math.max(1, temp);
        this.currentPage = temp;

        // Set start and end indexes item for current page, use this.currentPage in stead after re-calculate currentPage
        this.startIndex = (currentPage - 1) * itemsPerPage;
        this.startIndex = Math.max(0, startIndex);
        this.startIndex = Math.min(totalItems, startIndex);

        this.endIndex = Math.min(totalItems, startIndex + itemsPerPage);
        this.endIndex = Math.max(startIndex, endIndex);

        // Build ItemsDisplay string
        this.itemsDisplay = String.valueOf(Math.min(totalItems, startIndex + 1));

        if (endIndex > (startIndex + 1)) {
            this.itemsDisplay += "-" + endIndex;
        }
    }

    /**
     * @return Total pagination pages
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * @return Current pagination page
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * Setter method
     * 
     * @param currentPage Current page
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * Getter method
     * 
     * @return Total Items
     */
    public int getTotalItems() {
        return totalItems;
    }

    /**
     * Setter method
     * 
     * @param totalItems Total items
     */
    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
        reCalculateAll();
    }

    /**
     * Getter method
     * 
     * @return Items per page
     */
    public int getItemsPerPage() {
        return itemsPerPage;
    }

    /**
     * Setter method
     * 
     * @param itemsPerPage Items per page
     */
    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    /**
     * Getter method
     * 
     * @return Start index
     */
    public int getStartIndex() {
        return startIndex;
    }

    /**
     * Getter method
     * 
     * @return End index
     */
    public int getEndIndex() {
        return endIndex;
    }

    /**
     * Getter method
     * 
     * @return Items display String. Ex: 1-20
     */
    public String getItemsDisplay() {
        return itemsDisplay;
    }

    /**
     * Get sub-list of a list
     * 
     * @param allItems Original list
     * @return Sub list of Original, from startIndex to endIndex
     */
    public List<TapproachdtlDto> getSubList(List<TapproachdtlDto> allItems) {
        if (FwListUtils.isEmpty(allItems)) {
            return allItems;
        }

        return allItems.subList(startIndex, endIndex);
    }
}
