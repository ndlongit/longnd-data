package jp.co.inte.crm.common.service;

/**
 * 採番サービス
 * 
 */
public interface CrmSequenceService {

    /**
     * 法人担当者IDの採番処理
     * 
     * @return String 採番された法人担当者ID
     */
    String createSeqNextValHocgid();
}
