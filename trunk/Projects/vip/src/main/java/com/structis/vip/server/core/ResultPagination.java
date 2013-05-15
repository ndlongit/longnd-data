package com.structis.vip.server.core;

import java.util.List;

/**
 * Class pour stocker les r�sulats d'une appel pagination
 * 
 * @author b.brotosumpeno
 *
 * @param <B>
 */
public class ResultPagination<B> {

	/**
	 * Les resultats appel pagination
	 */
	private List<B> contents;
	
	/**
	 * La taille total
	 */
	private int count;
	
	/**
	 * La taille offset
	 */
	private int offset;

	public List<B> getData() {
		return contents;
	}

	public void setData(List<B> beans) {
		this.contents = beans;
	}

	public int getTotalLength() {
		return count;
	}

	public void setTotalLength(int count) {
		this.count = count;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	
}
