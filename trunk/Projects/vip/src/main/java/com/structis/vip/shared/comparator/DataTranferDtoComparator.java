package com.structis.vip.shared.comparator;

import java.util.Comparator;

import com.structis.vip.client.tools.DataTranferDTO;

public class DataTranferDtoComparator implements Comparator<DataTranferDTO> {

	private int column; // 1:nomFournisseur 2:famille 3:departRegion  4:entite
	private boolean isSort;  // isSort = true -> sort asc   else sort desc
	
	public int compare(DataTranferDTO sub1, DataTranferDTO sub2) {
		String value1 = null;
		String value2 = null;
		int result = 0;
		
		switch(column){
			case 1:
				value1 = sub1.getPerimetre()==null?"":sub1.getPerimetre();
				value2 = sub2.getPerimetre()==null?"":sub2.getPerimetre();
			break;			
			case 2:
				value1 = sub1.getNature()==null?"":sub1.getNature();
				value2 = sub2.getNature()==null?"":sub2.getNature();
			break;
			case 3:
				value1 = sub1.getDelegant()==null?"":sub1.getDelegant();
				value2 = sub2.getDelegant()==null?"":sub2.getDelegant();
			break;	
			case 4:
				value1 = sub1.getDebutdevalidite()==null?"":sub1.getDebutdevalidite();
				value2 = sub2.getDebutdevalidite()==null?"":sub2.getDebutdevalidite();
			break;
			case 5:
				value1 = sub1.getFindevalidite()==null?"":sub1.getFindevalidite();
				value2 = sub2.getFindevalidite()==null?"":sub2.getFindevalidite();
			break;	
			case 6:
				value1 = sub1.getDelegsignee()==null?"":sub1.getDelegsignee();
				value2 = sub2.getDelegsignee()==null?"":sub2.getDelegsignee();
			break;	
		}
		
		if(isSort) result = value1.compareToIgnoreCase(value2);                         //ascending order
		else 	   result = value2.compareToIgnoreCase(value1);							//descending order
		
		return result;
	}
	
	public boolean isSort() {
		return isSort;
	}

	public void setSort(boolean isSort) {
		this.isSort = isSort;
	}



	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	
	
}
