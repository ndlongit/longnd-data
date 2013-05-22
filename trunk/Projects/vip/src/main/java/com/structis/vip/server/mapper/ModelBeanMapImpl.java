package com.structis.vip.server.mapper;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ModelBeanMapImpl implements ModelBeanMap, InitializingBean {

    private static Logger logger = Logger.getLogger(ModelBeanMapImpl.class);

    private DozerBeanMapper mapper;
    @SuppressWarnings("rawtypes")
    private Map<Class, Class> map = new HashMap<Class, Class>();

    public void setMapper(DozerBeanMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public Class get(Object objet) {
        return this.map.get(objet.getClass());
    }

    /**
     * Cr�ation la liste de mapping des types
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        // Recuperer les fichiers de mapping
        List<String> locations = this.mapper.getMappingFiles();
        DefaultResourceLoader resourcesLoader = new DefaultResourceLoader();

        // Pour chaque fichier, on ajoute la class dans le map
        for (String location : locations) {

            // Recuperer le fichier
            Resource resource = resourcesLoader.getResource(location);
            InputStream stream = resource.getURL().openStream();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(stream);
            doc.getDocumentElement().normalize();

            // Recuperer le node mapping
            NodeList nodeLst = doc.getElementsByTagName("mapping");
            for (int i = 0; i < nodeLst.getLength(); i++) {
                Node fstNode = nodeLst.item(i);
                Element fstElmnt = (Element) fstNode;

                // Recuperer le class-a
                NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("class-a");
                Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
                NodeList fstNm = fstNmElmnt.getChildNodes();
                String classA = fstNm.item(0).getNodeValue();

                // Recuperer le class-b
                NodeList lstNmElmntLst = fstElmnt.getElementsByTagName("class-b");
                Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
                NodeList lstNm = lstNmElmnt.getChildNodes();
                String classB = lstNm.item(0).getNodeValue();

                // Ajouter dans le map, les deux class
                this.map.put(Class.forName(classA), Class.forName(classB));
                this.map.put(Class.forName(classB), Class.forName(classA));

                if (logger.isDebugEnabled()) {
                    logger.debug("mapped : " + classA + " <-> " + classB);
                }
            }
        }
    }

}
