package com.structis.vip.server.util;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.structis.argos.ws.WsArgosPort;
import com.structis.argos.ws.WsArgosPortServiceLocator;
import com.structis.argos.ws.WsArgosPortSoapBindingStub;
import com.structis.argos.ws.WsOrganisationVIPResultType;
import com.structis.argos.ws.WsOrganisationVIPType;
import com.structis.argos.ws.WsParametreVIPETDE;
import com.structis.vip.server.service.client.ClientPerimetreServiceImpl.Holder;

public class ArgosUtil {

    public static int wsTimeOut = 3600000;

    public static WsOrganisationVIPType[] getData(String type, String parent) throws Exception {
        WsArgosPortServiceLocator locator = new WsArgosPortServiceLocator();
        WsArgosPort binding = locator.getwsArgosPort(new URL(CatalinaPropertiesUtil.getArgosUrl()));
        System.out.println("Porting : " + binding);
        ((WsArgosPortSoapBindingStub) binding).setUsername(CatalinaPropertiesUtil.getArgosUsername());
        ((WsArgosPortSoapBindingStub) binding).setPassword(CatalinaPropertiesUtil.getArgosPassword());
        ((WsArgosPortSoapBindingStub) binding).setTimeout(wsTimeOut);

        WsParametreVIPETDE wsParametreVIPETDE = new WsParametreVIPETDE();
        wsParametreVIPETDE.setTabOrganizationType(type);
        wsParametreVIPETDE.setIdParent(parent);

        WsOrganisationVIPResultType result = binding.getListVIPETDE(wsParametreVIPETDE);

        result = binding.getListVIPETDE(wsParametreVIPETDE);

        return result.getListeRetour();
    }

    public static List<List<Holder>> getSolvedData(String parent, List<Holder> orgs) {
        List<List<Holder>> rs = new ArrayList<List<Holder>>();
        List<Holder> rsParent = new ArrayList<Holder>();

        boolean isStart = true;
        boolean isRun = true;

        while (isRun) {
            isRun = false;
            List<Holder> rsIn = new ArrayList<Holder>();
            if (isStart) {
                // for first step
                for (Holder arg0 : orgs) {
                    if (arg0.getValue().getIdOrgParente().equals(parent)) {
                        rsIn.add(arg0);
                        // enable is run
                        isRun = true;
                    }
                }
            } else {
                // for second step
                for (Holder arg0 : rsParent) {
                    for (Holder arg1 : orgs) {
                        if (arg1.getValue().getIdOrgParente().equals(arg0.getValue().getIdArgos())) {
                            rsIn.add(arg1);
                            // enable is run
                            isRun = true;
                        }
                    }
                }
            }
            // add to result
            rs.add(rsIn);
            // set parent
            rsParent = rsIn;
            isStart = false;
        }
        return rs;
    }

    public static String transcodification(String str) {
        if (str.lastIndexOf(" - ") != -1) {
            String strEdited = str.substring(0, str.lastIndexOf(" - "));
            if (strEdited.lastIndexOf(" - ") != -1) {
                return str.substring(strEdited.lastIndexOf(" - ") + 3, str.length()).trim();
            } else {
                return str;
            }
        } else {
            return str.trim();
        }
    }
}
