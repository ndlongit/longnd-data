// [LICENCE-HEADER]
//
package vn.pyco.tinycms.model;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.security.GrantedAuthority;

import vn.pyco.commons.model.impl.NumericIdEntity;
import vn.pyco.tinycms.utils.CacheConstants;
import vn.pyco.tinycms.utils.EntityConstants;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
@Entity
@Table(name=EntityConstants.TBL_AUTHORITIES)
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE,region=CacheConstants.REGION_CONTENTS)
public class Authority extends NumericIdEntity implements GrantedAuthority {
    private static final long serialVersionUID = 1L;
    
    public static final String PROP_AUTHORITY = "authority";
    
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_WRITER = "ROLE_WRITER";
    public static final String ROLE_PUBLISHER = "ROLE_PUBLISHER";
    public static final String ROLE_BOUSER = "ROLE_BOUSER";
    public static final String ROLE_FOUSER = "ROLE_FOUSER";
    
    private String _authority;
    private List<User> _users;
    
    public Authority() {
    }
    
    public Authority(String authority) {
        setAuthority(authority);
    }
    
    /*
     * @see org.springframework.security.GrantedAuthority#getAuthority()
     */
    @Override
    @Basic
    @Column(name="AUTHORITY", unique=true, length=24, nullable=false)
    public String getAuthority() {
        return _authority;
    }
    
    /**
     * @param authority the authority to set
     */
    public void setAuthority(String authority) {
        _authority = authority;
    }
    
    /**
     * @return the users
     */
    @ManyToMany(mappedBy="authorities", targetEntity=User.class, fetch=FetchType.LAZY)
    public List<User> getUsers() {
        return _users;
    }
    
    /**
     * @param users the users to set
     */
    public void setUsers(List<User> users) {
        _users = users;
    }
    
    /*
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Object other) {
        return _authority.compareTo(((Authority)other).getAuthority());
    }
}
