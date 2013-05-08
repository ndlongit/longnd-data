// [LICENCE-HEADER]
//

package vn.pyco.commons.model.impl;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


/**
 * Entity with ID is Long type
 * 
 */
@MappedSuperclass
public class NumericIdEntity extends AbstractEntity<Integer> {
	private static final long serialVersionUID = -1914706351796260333L;

	private Integer _id;
    
    /*
     * (non-Javadoc)
     * @see vn.pyco.commons.model.Identifiable#getId()
     */
    @Override
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer getId() {
        return _id;
    }
    
    /*
     * (non-Javadoc)
     * @see vn.pyco.commons.model.Identifiable#setId(java.io.Serializable)
     */
    @Override
    public void setId(Integer id) {
        _id = id;
    }
}
