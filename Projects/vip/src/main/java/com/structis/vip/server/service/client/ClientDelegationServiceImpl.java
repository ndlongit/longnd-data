package com.structis.vip.server.service.client;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.structis.vip.client.service.ClientDelegationService;
import com.structis.vip.server.bean.domain.Delegation;
import com.structis.vip.server.core.Constants;
import com.structis.vip.server.core.DelegationConstants;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.core.ManagerCallBack;
import com.structis.vip.server.mapper.ModelBeanMapperIfc;
import com.structis.vip.server.service.domain.DomDelegationDocumentService;
import com.structis.vip.server.service.domain.DomDelegationService;
import com.structis.vip.server.service.domain.DomDelegationStatusService;
import com.structis.vip.server.service.domain.DomDomDelService;
import com.structis.vip.server.util.CatalinaPropertiesUtil;
import com.structis.vip.shared.DelegationFilter;
import com.structis.vip.shared.exception.DelegationException;
import com.structis.vip.shared.exception.ExceptionType;
import com.structis.vip.shared.model.BaseModelDataActivable;
import com.structis.vip.shared.model.DelegationDelegataireModel;
import com.structis.vip.shared.model.DelegationModel;
import com.structis.vip.shared.model.DelegationNatureModel;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.PerimetreModel;

@Service("clientDelegationService")
public class ClientDelegationServiceImpl extends DependencyInjectionRemoteServiceServlet implements
		ClientDelegationService {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(ClientDelegationServiceImpl.class);

	@Autowired
	private DomDelegationService domDelegationService;
	
	@Autowired
	private DomDelegationStatusService domDelegationStatusService;
	
	@Autowired
	private DomDomDelService domDomDelService; 
	
	@Autowired
	private DomDelegationDocumentService domDelegationDocumentService; 

	@Autowired
	ModelBeanMapperIfc modelBeanMapper;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DelegationModel> getAllDelegations() {
		ManagerCallBack callBack = new ManagerCallBack() {
			public Object execute(Object... inputs) {
				return domDelegationService.getAllDelegations();
			}
		};
		return (List<DelegationModel>) callManager(callBack);
	}

	@Override
	public DelegationModel insert(DelegationModel delegationModel) throws DelegationException{		
		if (checkRulesOfNewUpdateDelegation(delegationModel)) {
				Delegation delegation = (Delegation) modelBeanMapper.map(delegationModel);				
				// 2 Jan 2013			
				List<DelegationDelegataireModel> dds = delegationModel.getLstDelegataires();			
				
				delegation = domDelegationService.insert(delegation, dds);
				return (DelegationModel) modelBeanMapper.map(delegation);
		} else {
				return null;
		}			
	}
	
	public Boolean update(DelegationModel delegationModel) throws DelegationException{
		// apply rule "we cannot have 2 primary delegation with same parameter & nature"
		boolean result = false;
		if (checkRulesOfNewUpdateDelegation(delegationModel)) {
			Delegation delegation = (Delegation) modelBeanMapper.map(delegationModel);
			// 2 Jan 2013			
			List<DelegationDelegataireModel> dds = delegationModel.getLstDelegataires();						
			boolean ret = domDelegationService.update(delegation, dds);
			return ret;			
		} else {
			result = false;			
		} 
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<DelegationModel> getValidDelegations(final DelegationFilter filter) {			
		ManagerCallBack callBack = new ManagerCallBack() {
			public Object execute(Object... inputs) {
				String perimetreId = (filter.getPerimetre() != null) ? filter.getPerimetre().getPerId(): null;
				Integer natureId = (filter.getNature() !=  null) ? filter.getNature().getId() : null;
				Integer typeId = (filter.getType() != null) ? filter.getType().getId() : null;
				Integer statusId = (filter.getStatus() != null) ? filter.getStatus().getId() : null;
				Integer delegantId = (filter.getDelegant() != null) ? filter.getDelegant().getId() : null;
				Integer delegataireId = (filter.getDelegataire() != null) ? filter.getDelegataire().getId() : null;
				
				return domDelegationService.getValidDelegations(perimetreId, natureId, 
						typeId, statusId, delegantId, 
						delegataireId, filter.getStartDate(), filter.getEndDate(), filter.getSep(), filter.getConjointe(), filter.getIsDisplayAllLevel());
			}
		};
		return (List<DelegationModel>) callManager(callBack);
	}
	
	private <E> List<Integer> getKeyList(List<E> list) {
		List<Integer> keys = new ArrayList<Integer>();
		for (E l: list) {
			keys.add(((Integer)((BaseModelDataActivable)l).getId()));
		}
		return keys;
	}
	
	@SuppressWarnings("unchecked")
	public List<DelegationModel> getValidDelegationsByEntite(final DelegationFilter filter) {			
		ManagerCallBack callBack = new ManagerCallBack() {
			public Object execute(Object... inputs) {
				String enId = filter.getEntite().getEntId();
				String perimetreId = (filter.getPerimetre() != null) ? filter.getPerimetre().getPerId(): null;				
				return domDelegationService.getDelegationsByEntite(enId, perimetreId, getKeyList(filter.getNatures()), 
						getKeyList(filter.getTypes()), getKeyList(filter.getStatuses()), getKeyList(filter.getDelegants()), 
						getKeyList(filter.getDelegataires()), filter.getStartDate(), filter.getEndDate(), filter.getSep(), 
						filter.getConjointe(), filter.getIsDisplayAllLevel(), filter.getPerimetreTreeModel(), filter.getUserRoles());
//				return domDelegationService.getValidDelegationsByEntite(enId, perimetreId, getKeyList(filter.getNatures()), 
//						getKeyList(filter.getTypes()), getKeyList(filter.getStatuses()), getKeyList(filter.getDelegants()), 
//						getKeyList(filter.getDelegataires()), filter.getStartDate(), filter.getEndDate(), filter.getSep(), filter.getConjointe(), filter.getIsDisplayAllLevel());
			}
		};
		return (List<DelegationModel>) callManager(callBack);
	}

	@Override
	public DelegationModel findById(Integer id) {
		Delegation delegation = domDelegationService.findById(id);
		return (DelegationModel) modelBeanMapper.map(delegation);
	}

	@Override
	public Boolean delete(DelegationModel dl){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		ServletContext context = attr.getRequest().getSession().getServletContext();
		String pathContext = context.getRealPath(File.separator);

		Delegation delegation = domDelegationService.findById(dl.getId());
		
		// check if parent is not null
		// delete the relative documents too, include signed documents and other documents
		// if parent is null, check relationship with other delegation
		
		List<DelegationModel> children = getChildrenById(delegation.getId());
		if (children.isEmpty() == false) {
			throw new DelegationException(ExceptionType.DEL_DELETE_DELEGATION_IS_PARENT);
		}
		if (domDomDelService.deleteByDelId(delegation.getId(), CatalinaPropertiesUtil.getVipDirectory(pathContext))
				&& domDelegationDocumentService.deleteByDelId(delegation.getId(), CatalinaPropertiesUtil.getVipDirectory(pathContext))) {
			domDelegationService.delete(delegation);
			return true;
		} else {
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<DelegationModel> getChildrenById(final Integer delId) {
		ManagerCallBack callBack = new ManagerCallBack() {
			public Object execute(Object... inputs) {
				return domDelegationService.getChildrenById(delId);
			}
		};
		return (List<DelegationModel>) callManager(callBack);
	}
		
	/*
	 * Check if there is a principle delegation with nature and perimetre. Used for checking when creating/update a new delegation 
	 */
	public Boolean hasDelegation (DelegationNatureModel dn, PerimetreModel p){
		return domDelegationService.hasDelegation(dn.getId(), p.getPerId());		
	}
	
	/*
	 * Check if there is a principle delegation with nature and perimetre. Used for checking when creating/update a new delegation 
	 */	
	public Boolean hasOtherDelegation (DelegationModel d, DelegationNatureModel dn, PerimetreModel p, EntiteModel en){
		return domDelegationService.hasOtherDelegation(d.getId(), dn.getId(), p.getPerId(), en.getEntId());		
	}
	
	private Boolean checkRulesOfNewUpdatePrincipleDelegation(DelegationModel delegationModel) throws DelegationException{
		//R6 - Delegatant & delegataire not the same
		if (delegationModel.getEntite().getEntId().equals(Constants.ENTITE_ID_ETDE)) {
			if (delegationModel.getDelegant().getId().intValue() == delegationModel.getDelegataire().getId().intValue()) {
				throw new DelegationException(ExceptionType.DEL_SAME_DELEGANT_DELEGANTAIRE);
			}
		} else {
			for (DelegationDelegataireModel dd : delegationModel.getLstDelegataires()) {
				if (dd.getColId().equals(delegationModel.getDelegant().getId())) {
					throw new DelegationException(ExceptionType.DEL_SAME_DELEGANT_DELEGANTAIRE);
				}
			}
		}
		//RG7 - start date is before end date
		if (delegationModel.getStartDate() != null && delegationModel.getEndDate() != null &&
				delegationModel.getStartDate().compareTo(delegationModel.getEndDate()) > 0) {
			throw new DelegationException(ExceptionType.DEL_START_AFTER_END_DATE);
		}
		//RG3 - no 2 delegation with same nature & parameter
		if (delegationModel.getParent() == null && domDelegationService.hasOtherDelegation(delegationModel.getId(), delegationModel.getDelegationNature().getId(), 
				delegationModel.getPerimeter().getPerId(), delegationModel.getPerimeter().getEntite().getEntId())) {
			throw new DelegationException(ExceptionType.DEL_INSERT_DUPLICATE);
		}		
		return true;
	}
	private Boolean checkRulesOfNewUpdateTemporaryDelegation(DelegationModel delegationModel) throws DelegationException{
		//R6 - Delegatant & delegataire not the same
		if (delegationModel.getDelegant().getId().intValue() == delegationModel.getDelegataire().getId().intValue()) {
			throw new DelegationException(ExceptionType.DEL_SAME_DELEGANT_DELEGANTAIRE);
		}
		//RG7 - start date is before end date
		if (delegationModel.getStartDate() != null && delegationModel.getEndDate() != null &&
				delegationModel.getStartDate().compareTo(delegationModel.getEndDate()) > 0) {
			throw new DelegationException(ExceptionType.DEL_START_AFTER_END_DATE);
		}
		//RG10 check from ui, not display from menu item
//		//RG10 : cannot create temporary delegation from a temporary delegation
//		if (isTemporaryDelegation(delegationModel.getParent()))	{
//			throw new DelegationException(ExceptionType.DEL_TEMP_OF_TEMP);
//		}		
		return true;
	}
	private Boolean checkRulesOfNewUpdateSubDelegation(DelegationModel delegationModel) throws DelegationException{
		//R6 - Delegatant & delegataire not the same
		if (delegationModel.getDelegant().getId().intValue() == delegationModel.getDelegataire().getId().intValue()) {
			throw new DelegationException(ExceptionType.DEL_SAME_DELEGANT_DELEGANTAIRE);
		}
		//RG7 - start date is before end date
		if (delegationModel.getStartDate() != null && delegationModel.getEndDate() != null &&
				delegationModel.getStartDate().compareTo(delegationModel.getEndDate()) > 0) {
			throw new DelegationException(ExceptionType.DEL_START_AFTER_END_DATE);
		}
		//RG17-18 : If the delegataire of parent delegation is the delegant of sub, the delegant is not alternated
		
		Integer currentDelegantId = delegationModel.getDelegant().getId();
		Integer oldDelegantId = domDelegationService.subDelegantIsDelegataireOfParent(delegationModel.getId());
		if ((oldDelegantId != null) && (currentDelegantId != oldDelegantId)) {
			throw new DelegationException(ExceptionType.DEL_UNIQUE_DELEGANT);
		}		
			
		return true;
	}
	private Boolean checkRulesOfNewUpdateDelegation(DelegationModel delegationModel) throws DelegationException{
		if (isPrincipleDelegation(delegationModel)) {
			return checkRulesOfNewUpdatePrincipleDelegation(delegationModel);
		} else if (isSubDelegation(delegationModel)) {
			return checkRulesOfNewUpdateSubDelegation(delegationModel);
		} else if (isTemporaryDelegation(delegationModel)) {
			return checkRulesOfNewUpdateTemporaryDelegation(delegationModel);
			
		} else {
			throw new DelegationException(ExceptionType.DEL_TYPE_NOT_SET);
		}
		
	}
	
	private Boolean isPrincipleDelegation(DelegationModel dl) {
		return dl.getDelegationType().getId().equals(DelegationConstants.DEL_TYPE_PRINCIPLE);
	}
	
	private Boolean isSubDelegation(DelegationModel dl) {
		return dl.getDelegationType().getId().equals(DelegationConstants.DEL_TYPE_SUB);
	}
	private Boolean isTemporaryDelegation(DelegationModel dl) {
		return dl.getDelegationType().getId().equals(DelegationConstants.DEL_TYPE_TEMPO);
	}

	@Override
	public DelegationModel renewDelegation(DelegationModel delegationModel) {
		Delegation del = (Delegation) modelBeanMapper.map(delegationModel);
		Delegation newDel = domDelegationService.renewDelegation(del); 
		return (DelegationModel) modelBeanMapper.map(newDel);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DelegationModel> updateStatus() throws DelegationException {
		ManagerCallBack callBack = new ManagerCallBack() {
			public Object execute(Object... inputs) {
				return domDelegationService.updateStatus();
			}
		};
		return (List<DelegationModel>) callManager(callBack);
	}

	@Override
	public DelegationModel insertRenew(DelegationModel delegationModel) throws DelegationException {
		if (checkRulesOfNewUpdateDelegation(delegationModel)) {
			if (delegationModel.getParent() != null) {
				update(delegationModel.getParent());
			}
			Delegation delegation = (Delegation) modelBeanMapper.map(delegationModel);			
			// 2 Jan 2013			
			List<DelegationDelegataireModel> dds = delegationModel.getLstDelegataires();			
			
			delegation = domDelegationService.insert(delegation, dds);
			return (DelegationModel) modelBeanMapper.map(delegation);
		} else {
			return null;
		}	
	}

	@Override
	public Boolean updateStatusAuto(DelegationModel delegationModel)
			throws DelegationException {
		// apply rule "we cannot have 2 primary delegation with same parameter & nature"
		boolean result = false;
		if (checkRulesOfNewUpdateDelegation(delegationModel)) {
			Delegation delegation = (Delegation) modelBeanMapper.map(delegationModel);
			
			if (delegationModel.getDelegationStatus() != null && delegationModel.getDelegationStatus().getId() == DelegationConstants.DEL_STATUS_ESTABLISH) {
				delegation.setDelegationStatus(domDelegationStatusService.findById(DelegationConstants.DEL_STATUS_VENIR));
				
				boolean isSigned = false;
				boolean isObsolete = false;
				if (delegationModel.getStartDate() != null) {
					Calendar currentDate = Calendar.getInstance();
				    Calendar startDate = Calendar.getInstance();
				    startDate.setTime(delegationModel.getStartDate());
				    
				    currentDate.set(Calendar.HOUR_OF_DAY, 0);
				    currentDate.set(Calendar.MINUTE, 0);
				    currentDate.set(Calendar.SECOND, 0);
				    currentDate.set(Calendar.MILLISECOND, 0);
				    
				    startDate.set(Calendar.HOUR_OF_DAY, 0);
				    startDate.set(Calendar.MINUTE, 0);
				    startDate.set(Calendar.SECOND, 0);
				    startDate.set(Calendar.MILLISECOND, 0);
	
				    currentDate.set(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH));
				    startDate.set(startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH), startDate.get(Calendar.DAY_OF_MONTH));
				    
					if (currentDate.before(startDate)) {
					} else if(currentDate.after(startDate)) {
						isSigned = true;
					} else {
						isSigned = true;
					}
				}
				
				if (delegationModel.getEndDate() != null) {
					Calendar currentDate = Calendar.getInstance();
				    Calendar endDate = Calendar.getInstance();
				    endDate.setTime(delegationModel.getEndDate());
				    
				    currentDate.set(Calendar.HOUR_OF_DAY, 0);
				    currentDate.set(Calendar.MINUTE, 0);
				    currentDate.set(Calendar.SECOND, 0);
				    currentDate.set(Calendar.MILLISECOND, 0);
				    
				    endDate.set(Calendar.HOUR_OF_DAY, 0);
				    endDate.set(Calendar.MINUTE, 0);
				    endDate.set(Calendar.SECOND, 0);
				    endDate.set(Calendar.MILLISECOND, 0);
	
				    currentDate.set(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH));
				    endDate.set(endDate.get(Calendar.YEAR), endDate.get(Calendar.MONTH), endDate.get(Calendar.DAY_OF_MONTH));
				    
					if (currentDate.before(endDate)) {
					} else if(currentDate.after(endDate)) {
						isObsolete = true;
					} else {
					}
				}
			
				if (isSigned || isObsolete) {
					if (isSigned) {
						delegation.setDelegationStatus(domDelegationStatusService.findById(DelegationConstants.DEL_STATUS_SIGNEE));
						delegation.setIsSigned(1);
						
						if (delegation.getParent() != null) {
							if (DelegationConstants.DEL_TYPE_PRINCIPLE.equals(delegation.getDelegationType().getId())) {
								delegation.getParent().setDelegationStatus(domDelegationStatusService.findById(DelegationConstants.DEL_STATUS_OBSOLETE));
							} else if (DelegationConstants.DEL_TYPE_TEMPO.equals(delegation.getDelegationType().getId())) {
								delegation.getParent().setDelegationStatus(domDelegationStatusService.findById(DelegationConstants.DEL_STATUS_TEMPORARY));
							}
							domDelegationService.update(delegation.getParent());
						}
					} 
					if (isObsolete) {
						delegation.setDelegationStatus(domDelegationStatusService.findById(DelegationConstants.DEL_STATUS_OBSOLETE));
						delegation.setIsSigned(1);
						
						if (delegation.getParent() != null) {
							delegation.getParent().setDelegationStatus(domDelegationStatusService.findById(DelegationConstants.DEL_STATUS_SIGNEE));
							delegation.getParent().setIsSigned(1);
							domDelegationService.update(delegation.getParent());
						}
					}
				}
			}
			
			return domDelegationService.update(delegation);			
		} else {
			result = false;			
		} 
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DelegationModel> findByPerimetre(final String perimetreId) {
		ManagerCallBack callBack = new ManagerCallBack() {
			public Object execute(Object... inputs) {
				return domDelegationService.findByPerimetre(perimetreId);
			}
		};
		return (List<DelegationModel>) callManager(callBack);
	}

	@Override
	public PagingLoadResult<DelegationModel> getValidDelegationsByEntiteByPaging(DelegationFilter config) {
		List<DelegationModel> all = new ArrayList<DelegationModel>();
		all = getValidDelegationsByEntite(config);
		
		if (config.getSortInfo().getSortField() != null) {
		      final String sortField = config.getSortInfo().getSortField();
		      if (sortField != null) {
		    	  
		        Collections.sort(all, config.getSortInfo().getSortDir().comparator(new Comparator<DelegationModel>() {
		          public int compare(DelegationModel p1, DelegationModel p2) {
		            if (sortField.equals("perimeter.name") && p1.getPerimeter() != null && p2.getPerimeter() != null) {
		              return p1.getPerimeter().getName().compareTo(p2.getPerimeter().getName());
		            } else if (sortField.equals("delegationNature.name") && p1.getDelegationNature() != null && p2.getDelegationNature() != null) {
		              return p1.getDelegationNature().getName().compareTo(p2.getDelegationNature().getName());
		            } else if (sortField.equals("delegant.fullname") && p1.getDelegant() != null && p1.getDelegant() != null) {
		              return p1.getDelegant().getFullname().compareTo(p2.getDelegant().getFullname());
		            } else if (sortField.equals("delegataire.fullname") && p1.getDelegataire() != null && p2.getDelegataire() != null) {
		              return p1.getDelegataire().getFullname().compareTo(p2.getDelegataire().getFullname());
		            } else if (sortField.equals("startDate") && p1.getStartDate() != null && p2.getStartDate() != null) {
		              return p1.getStartDate().compareTo(p2.getStartDate());
		            } else if (sortField.equals("endDate") && p1.getEndDate() != null && p2.getEndDate() != null) {
		              return p1.getEndDate().compareTo(p2.getEndDate());
		            } else if (sortField.equals("delegationStatus.name") && p1.getDelegationStatus() != null && p2.getDelegationStatus() != null) {
		              return p1.getDelegationStatus().getName().compareTo(p2.getDelegationStatus().getName());
		            } else if (sortField.equals("delegationType.name") && p1.getDelegationType() != null && p2.getDelegationType() != null) {
		              return p1.getDelegationType().getName().compareTo(p2.getDelegationType().getName());
		            }
		            return 0;
		          }
		        }));
		      }
		    }
		
		ArrayList<DelegationModel> sublist = new ArrayList<DelegationModel>();
	    int limit = all.size();
	    
	    if (config.getLimit() > 0) {
	      limit = Math.min(config.getLimit(), limit);
	    }
	    
	    DelegationModel model = null;
	    for (int i = config.getOffset(); i < limit; i++) {
	    	model = all.get(i);
	    	model.removeUnusedOnList();
	    	sublist.add(model);
	    }

	    return new BasePagingLoadResult<DelegationModel>(sublist, config.getOffset(), all.size());
	}

	@Override
	public List<DelegationDelegataireModel> findDelegataires(
			final Integer delId,final String perId, final String entId) {
		ManagerCallBack callBack = new ManagerCallBack() {
			public Object execute(Object... inputs) {
				return domDelegationService.findDelegataires(delId, perId, entId);
			}
		};
		return (List<DelegationDelegataireModel>) callManager(callBack);
	}

	@Override
	public String getDelegataires(final Integer delId) {
		ManagerCallBack callBack = new ManagerCallBack() {
			public Object execute(Object... inputs) {
				return domDelegationService.findDelegataireNames(delId);
			}
		};
		return (String) callManager(callBack);
	}
}
