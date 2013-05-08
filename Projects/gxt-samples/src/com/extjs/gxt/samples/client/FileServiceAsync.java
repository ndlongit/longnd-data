/*
 * Ext GWT 2.2.5 - Ext for GWT
 * Copyright(c) 2007-2010, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.extjs.gxt.samples.client;

import java.util.List;

import com.extjs.gxt.samples.client.examples.model.FileModel;
import com.extjs.gxt.ui.client.data.RemoteSortTreeLoadConfig;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Example <code>RemoteService</code>.
 */
public interface FileServiceAsync {

  /**
   * Returns the children of the given parent.
   * 
   * @param folder the parent folder
   * @param callback the callback to return the children
   */
  public void getFolderChildren(FileModel folder, AsyncCallback<List<FileModel>> callback);

  /**
   * Returns the children of the given parent.
   * 
   * @param loadConfig the load config
   * @param callback the callback to return the children
   */
  public void getFolderChildren(RemoteSortTreeLoadConfig loadConfig, AsyncCallback<List<FileModel>> callback);

}
