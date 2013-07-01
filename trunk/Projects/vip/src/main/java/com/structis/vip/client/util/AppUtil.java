package com.structis.vip.client.util;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ComponentManager;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.core.client.GWT;
import com.structis.vip.client.constant.ClientConstant;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.navigation.NavigationFactory;
import com.structis.vip.client.navigation.NavigationManager;
import com.structis.vip.shared.model.LanguageModel;

public class AppUtil {

    public static final Messages messages = GWT.create(Messages.class);

    private static MessageBox box = new MessageBox();

    public static List<String> getPagingValue() {
        List<String> l = new ArrayList<String>();
        l.add(ClientConstant.PAGE_SIZE_10 + "");
        l.add(ClientConstant.PAGE_SIZE_20 + "");
        l.add(ClientConstant.PAGE_SIZE_30 + "");
        l.add(ClientConstant.PAGE_SIZE_40 + "");
        l.add(ClientConstant.PAGE_SIZE_50 + "");
        // l.add(messages.commonTous());
        return l;
    }

    public static List<LanguageModel> getLanguages() {
        List<LanguageModel> l = new ArrayList<LanguageModel>();
        LanguageModel french = new LanguageModel();
        french.setName(messages.commonlanguageFrench());
        french.setCode(ClientConstant.LANGUAGE_CODE_FRENCH);

        LanguageModel english = new LanguageModel();
        english.setName(messages.commonlanguageEnglish());
        english.setCode(ClientConstant.LANGUAGE_CODE_ENGLISH);

        l.add(french);
        l.add(english);
        return l;
    }

    public static boolean checkToShowConfirmInEditMode(Listener<MessageBoxEvent> callback) {
        if (((NavigationManager) NavigationFactory.getNavigation()).getContext().getIsInActionEdit()) {
            if (box != null && !box.isVisible()) {
                box.setButtons(MessageBox.YESNO);
                box.setIcon(MessageBox.QUESTION);
                box.setTitle(messages.commonConfirmation());
                box.setMessage(messages.commonineditmode());
                ((Button) box.getDialog().getButtonBar().getItem(0)).setText(messages.commonDialogOuiButton());
                ((Button) box.getDialog().getButtonBar().getItem(1)).setText(messages.commonDialogNonButton());
                box.addCallback(callback);
                box.show();
                return true;
            }
        }
        return false;
    }

    public static boolean checkToShowConfirmInAdminEditMode(Listener<MessageBoxEvent> callback) {
        if (box != null && !box.isVisible()) {
            if (((NavigationManager) NavigationFactory.getNavigation()).getContext().getIsInAdminEdit()) {
                box.setButtons(MessageBox.YESNO);
                box.setIcon(MessageBox.QUESTION);
                box.setTitle(messages.commonConfirmation());
                box.setMessage(messages.commonineditmode());
                ((Button) box.getDialog().getButtonBar().getItem(0)).setText(messages.commonDialogOuiButton());
                ((Button) box.getDialog().getButtonBar().getItem(1)).setText(messages.commonDialogNonButton());
                box.addCallback(callback);
                box.show();
                return true;
            }
        }
        return false;
    }

    public static boolean checkToShowWarningInEditMode() {
        if (box != null && !box.isVisible()) {
            if (((NavigationManager) NavigationFactory.getNavigation()).getContext().getIsInActionEdit()) {
                box.setButtons(MessageBox.OK);
                box.setIcon(MessageBox.WARNING);
                box.setTitle(messages.commonErreurHeader());
                box.setMessage(messages.commonineditmode());
                ((Button) box.getDialog().getButtonBar().getItem(0)).setText(messages.commonClosebutton());
                box.show();
                return true;
            }
        }
        return false;
    }

    public static void putInEditMode() {
        ((NavigationManager) NavigationFactory.getNavigation()).getContext().setIsInActionEdit(true);
    }

    public static void removeInEditMode() {
        ((NavigationManager) NavigationFactory.getNavigation()).getContext().setIsInActionEdit(false);
    }

    public static boolean checkToShowWarningInAdminEditMode(boolean checkPerimetre) {
        if (box != null && !box.isVisible()) {
            if (((NavigationManager) NavigationFactory.getNavigation()).getContext().getIsInAdminEdit()) {
                if ((!checkPerimetre) || (checkPerimetre && inPerimetreEditMode())) {
                    box.setButtons(MessageBox.OK);
                    box.setIcon(MessageBox.WARNING);
                    box.setTitle(messages.commonErreurHeader());
                    box.setMessage(messages.commonineditmode());
                    ((Button) box.getDialog().getButtonBar().getItem(0)).setText(messages.commonClosebutton());
                    box.show();
                    return true;
                }
            }
        }
        return false;
    }

    public static void putInAdminEditMode() {
        ((NavigationManager) NavigationFactory.getNavigation()).getContext().setIsInAdminEdit(true);
    }

    public static void removeAdminInEditMode() {
        ((NavigationManager) NavigationFactory.getNavigation()).getContext().setIsInAdminEdit(false);
    }

    public static void putInPerimetreEditMode() {
        ((NavigationManager) NavigationFactory.getNavigation()).getContext().setIsInPerimetreEdit(true);
    }

    public static void removePerminetreEditMode() {
        ((NavigationManager) NavigationFactory.getNavigation()).getContext().setIsInPerimetreEdit(false);
    }

    public static boolean inPerimetreEditMode() {
        return (((NavigationManager) NavigationFactory.getNavigation()).getContext().getIsInPerimetreEdit());
    }

    public static Component getPanel(String id) {
        Component component = ComponentManager.get().get(id);
        return component;
    }

    public static void showConfirmMessageBox(String message, Listener<MessageBoxEvent> callback) {
        MessageBox confirmBox = new MessageBox();
        confirmBox.setButtons(MessageBox.YESNO);
        confirmBox.setIcon(MessageBox.QUESTION);
        confirmBox.setTitle(messages.commonConfirmation());
        confirmBox.setMessage(message);
        ((Button) confirmBox.getDialog().getButtonBar().getItem(0)).setText(messages.commonDialogOuiButton());
        ((Button) confirmBox.getDialog().getButtonBar().getItem(1)).setText(messages.commonDialogNonButton());
        confirmBox.addCallback(callback);
        confirmBox.show();
        confirmBox = null;

    }

    public static void showConfirmMessageBox(String message, String ouiButtonText, String nonButtonText, Listener<MessageBoxEvent> callback) {
        MessageBox confirmBox = new MessageBox();

        confirmBox.setButtons(MessageBox.YESNO);
        confirmBox.setIcon(MessageBox.QUESTION);
        confirmBox.setTitle(messages.commonConfirmation());
        confirmBox.setMessage(message);
        ((Button) confirmBox.getDialog().getButtonBar().getItem(0)).setText(ouiButtonText);
        ((Button) confirmBox.getDialog().getButtonBar().getItem(1)).setText(nonButtonText);
        confirmBox.addCallback(callback);
        confirmBox.show();
        confirmBox = null;

    }

    public static void showWarning(String message) {
        MessageBox warningBox = new MessageBox();
        warningBox.setButtons(MessageBox.OK);
        warningBox.setIcon(MessageBox.WARNING);
        warningBox.setTitle(messages.commonErreurHeader());
        warningBox.setMessage(message);
        ((Button) warningBox.getDialog().getButtonBar().getItem(0)).setText(messages.commonClosebutton());
        warningBox.show();
        warningBox = null;

    }

    public static void showError(String message) {
        MessageBox warningBox = new MessageBox();
        warningBox.setButtons(MessageBox.OK);
        warningBox.setIcon(MessageBox.ERROR);
        warningBox.setTitle(messages.commonErreurHeader());
        warningBox.setMessage(message);
        ((Button) warningBox.getDialog().getButtonBar().getItem(0)).setText(messages.commonClosebutton());
        warningBox.show();
        warningBox = null;

    }
}
