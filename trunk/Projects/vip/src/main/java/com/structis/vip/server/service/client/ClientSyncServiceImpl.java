package com.structis.vip.server.service.client;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.vip.client.service.ClientSyncService;
import com.structis.vip.server.bean.domain.Collaborateur;
import com.structis.vip.server.bean.domain.Entite;
import com.structis.vip.server.bean.domain.PayCode;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.service.domain.DomCollaborateurService;
import com.structis.vip.server.service.domain.DomPayCodeService;
import com.structis.vip.server.util.AddressHandler;
import com.structis.vip.server.util.CatalinaPropertiesUtil;
import com.structis.vip.server.util.CollaborateurHandler;
import com.structis.vip.shared.SharedConstant;
import com.structis.vip.shared.model.AddressModel;
import com.structis.vip.shared.model.CollaborateurModel;
import com.structis.vip.shared.model.SynETDEModel;

@Service("clientSyncService")
public class ClientSyncServiceImpl extends DependencyInjectionRemoteServiceServlet implements ClientSyncService {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(ClientSyncServiceImpl.class);

    @Autowired
    private DomCollaborateurService domCollaborateurService;

    @Autowired
    private DomPayCodeService domPayCodeService;

    @Override
    public List<CollaborateurModel> syncRUBIS(String entityId) {
        List<CollaborateurModel> ret = new ArrayList<CollaborateurModel>();

        List<SynETDEModel> codes = this.getRubsiCodesNameEtde();
        if (codes.isEmpty() == false) {
            for (int i = 0; i < codes.size(); i++) {
                if (codes.get(i) != null) {
                    ret.addAll(this.syncRUBISWithCode(entityId, codes.get(i).getCode()));
                }
            }
        }

        return ret;
    }

    private List<CollaborateurModel> syncRUBISWithCode(String entityId, String code) {
        // String rubisUrl = CatalinaPropertiesUtil.getRubisEtdeUrl();
        // int idx = rubisUrl.indexOf("?");
        // if (idx < 0) idx = rubisUrl.length();
        // rubisUrl = rubisUrl.substring(0,idx);
        // if (Constants.ENTITE_ID_ETDE.equals(entityId)) {
        // rubisUrl = rubisUrl + "?type=etde&code=" + code;
        // } else if (Constants.ENTITE_ID_BYEFE.equals(entityId)) {
        // rubisUrl = rubisUrl + "?type=byefe&code=" + code;
        // } else {
        // rubisUrl = rubisUrl + "?type=bytp&code=" + code;
        // }

        GetMethod method = new GetMethod(CatalinaPropertiesUtil.getRubisEtdeUrl() + code);
        // GetMethod method = new GetMethod(rubisUrl);
        method.setRequestHeader("Accept", MediaType.APPLICATION_XML);
        HttpClient client = new HttpClient();

        Credentials creds = new UsernamePasswordCredentials(CatalinaPropertiesUtil.getRubisUsername(), CatalinaPropertiesUtil.getRubisPassword());
        client.getState().setCredentials(AuthScope.ANY, creds);

        CollaborateurHandler handler = new CollaborateurHandler();

        try {
            @SuppressWarnings("unused")
            int status = client.executeMethod(method);
            InputStream stream = method.getResponseBodyAsStream();

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(stream, handler);

            if (!handler.getCollaborateurs().isEmpty()) {
                Collaborateur collaborateur = null;
                AddressModel address = null;

                Entite entite = new Entite();
                entite.setEntId(entityId);

                for (Collaborateur item : handler.getCollaborateurs()) {
                    item.setEntite(entite);

                    LOGGER.info("syn with RUBIS by id = " + item.getIdBycn());

                    collaborateur = this.domCollaborateurService.findByIdBycn(item.getIdBycn());
                    address = this.getAddress(item.getIdBycn());

                    if (address != null) {
                        item.setAddress(this.getAddress(address));
                    }

                    try {
                        if (collaborateur == null) {
                            this.domCollaborateurService.insert(item);
                        } else {
                            item.setId(collaborateur.getId());
                            item.setIsDelegant(collaborateur.getIsDelegant());
                            item.setIsDelegataire(collaborateur.getIsDelegataire());
                            item.setPerimetreDelegant(collaborateur.getPerimetreDelegant());
                            item.setPerimetreDelegataire(collaborateur.getPerimetreDelegataire());

                            this.domCollaborateurService.update(item);
                        }
                    } catch (Exception e) {
                        LOGGER.error(e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            method.releaseConnection();
        }
        return new ArrayList<CollaborateurModel>();
    }

    @Override
    public AddressModel getAddress(String idbycn) {
        GetMethod method = new GetMethod(CatalinaPropertiesUtil.getRubisIdBycnUrl() + idbycn);
        method.setRequestHeader("Accept", MediaType.APPLICATION_XML);
        HttpClient client = new HttpClient();

        Credentials creds = new UsernamePasswordCredentials(CatalinaPropertiesUtil.getRubisUsername(), CatalinaPropertiesUtil.getRubisPassword());
        client.getState().setCredentials(AuthScope.ANY, creds);

        AddressHandler handler = new AddressHandler();

        try {
            client.executeMethod(method);
            InputStream stream = method.getResponseBodyAsStream();

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(stream, handler);

            return handler.getAddress();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }
        return null;
    }

    private String getAddress(final AddressModel address) {
        final StringBuffer buffer = new StringBuffer();
        if (address.getNumeroRue() != null) {
            buffer.append(address.getNumeroRue() + ", ");
        }
        if (address.getComplementAdresse() != null) {
            buffer.append(address.getComplementAdresse() + ", ");
        }
        if (address.getBureauDistributeur() != null) {
            buffer.append(address.getBureauDistributeur() + ", ");
        }
        if (address.getPays() != null) {
            PayCode payCode = this.domPayCodeService.findByCode(address.getPays());
            if (payCode != null) {
                buffer.append(payCode.getName());
            } else {
                buffer.append(address.getPays());
            }
        }
        return buffer.toString();
    }

    @Override
    public List<SynETDEModel> getRubsiCodesNameEtde() {
        List<SynETDEModel> ret = new ArrayList<SynETDEModel>();
        String pro = CatalinaPropertiesUtil.getRubisCodesNamesEtde();
        String[] items = pro.split(",");
        if (items != null && items.length > 0) {
            SynETDEModel model = null;
            for (int i = 0; i < items.length; i++) {
                String[] codeName = items[i].trim().split("<>");
                if (codeName != null && codeName.length == 2) {
                    model = new SynETDEModel();
                    model.setCode(codeName[0].trim());
                    model.setName(codeName[1].trim());

                    ret.add(model);
                }
            }
        }
        return ret;
    }

    @Override
    public List<CollaborateurModel> syncRUBISWithItems(String entId, List<SynETDEModel> models) {
        List<CollaborateurModel> ret = new ArrayList<CollaborateurModel>();

        if (models != null && models.isEmpty() == false) {
            for (SynETDEModel synETDEModel : models) {
                LOGGER.info("Synchronizing ETDE with code = " + synETDEModel.getCode());
                ret.addAll(this.syncRUBISWithCode(entId, synETDEModel.getCode()));
            }
        }
        return null;
    }

    @Override
    public List<SynETDEModel> getRubsiCodesName(String entId, String entName) {
        List<SynETDEModel> ret = new ArrayList<SynETDEModel>();
        String pro = null;
        if (SharedConstant.ENTITE_ID_ETDE.equals(entId)) {
            pro = CatalinaPropertiesUtil.getRubisCodesNamesEtde();
        } else if (SharedConstant.ENTITE_ID_BYEFE.equals(entId)) {
            pro = CatalinaPropertiesUtil.getRubisCodesNamesByefe();
        } else {
            pro = CatalinaPropertiesUtil.getRubisCodesNamesBytp();
        }
        String[] items = pro.split(",");
        if (items != null && items.length > 0) {
            SynETDEModel model = null;
            for (int i = 0; i < items.length; i++) {
                String[] codeName = items[i].trim().split("<>");
                if (codeName != null && codeName.length == 2) {
                    model = new SynETDEModel();
                    model.setCode(codeName[0].trim());
                    model.setName(codeName[1].trim());

                    ret.add(model);
                }
            }
        }
        return ret;
    }
}
