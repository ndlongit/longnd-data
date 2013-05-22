package com.structis.vip.server.bean.core;

import java.util.Date;

/**
 * Interface pour les types de donn�es activables qui contient une date de suppression
 * 
 * @author b.brotosumpeno
 * 
 */
public interface Activable {

    public Date getDateSuppr();

    public void setDateSuppr(Date dateSuppr);

}
