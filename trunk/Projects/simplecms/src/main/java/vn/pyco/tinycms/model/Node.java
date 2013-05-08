// [LICENCE-HEADER]
//
package vn.pyco.tinycms.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import vn.pyco.commons.model.Inheritable;
import vn.pyco.commons.model.impl.StatefulEntity;
import vn.pyco.tinycms.utils.CacheConstants;
import vn.pyco.tinycms.utils.EntityConstants;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
@Entity
@Table(name=EntityConstants.TBL_NODES)
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region=CacheConstants.REGION_CONTENTS)
@Inheritance(strategy=InheritanceType.JOINED)
public class Node extends StatefulEntity implements Inheritable<Node>, Comparable<Node> {
    private static final long serialVersionUID = 1L;
    
    public static final String PROP_PATH = "path";
    public static final String PROP_ALIAS = "alias";
    public static final String PROP_PARENT = "parent";
    
    private String _path;
    private String _alias;
    private String _title;
    private Node _parent;
    private List<Node> _childNodes;
    private boolean _secured;
    
    /**
     * @return the alias
     */
    @Column(name="ALIAS", length=24, nullable=false)
    public String getAlias() {
        return _alias;
    }
    
    /**
     * @param alias the alias to set
     */
    public void setAlias(String alias) {
        _alias = alias;
    }
    
    /**
     * @return the title
     */
    @Column(name="TITLE", length=255)
    public String getTitle() {
        return _title;
    }
    
    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        _title = title;
    }
    
    /**
     * @return the path
     */
    @Column(name="PATH", length=255, unique=true, nullable=false)
    public String getPath() {
        return _path;
    }
    
    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        _path = path;
    }
    
    /*
     * @see vn.pyco.commons.model.Inheritable#getParent()
     */
    @Override
    @ManyToOne(targetEntity=Node.class, fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="PARENT_ID")
    public Node getParent() {
        return _parent;
    }
    
    /*
     * @see vn.pyco.commons.model.Inheritable#setParent(java.lang.Object)
     */
    @Override
    public void setParent(Node parent) {
        _parent = parent;
    }
    
    /**
     * @return the childNodes
     */
    @OneToMany(targetEntity=Node.class, mappedBy="parent", fetch=FetchType.LAZY)
    public List<Node> getChildNodes() {
        return _childNodes;
    }
    
    /**
     * @param childNodes the childNodes to set
     */
    public void setChildNodes(List<Node> childNodes) {
        _childNodes = childNodes;
    }
    
    /**
     * @return the secured
     */
    @Column(name="IS_SECURED", nullable=false)
    public boolean isSecured() {
        return _secured;
    }
    
    /**
     * @param secured the secured to set
     */
    public void setSecured(boolean secured) {
        _secured = secured;
    }
    
    /*
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Node o) {
        return getPath().compareTo(o.getPath());
    }
}
