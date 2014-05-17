package jp.co.inte.crm.web.service;

import java.util.Date;

import jp.co.inte.crm.web.dto.McalendarDto;

/**
 * <pre>
 * mcalendar(カレンダマスタ)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface McalendarService {

    /**
     * カレンダ情報
     * 日付指定でカレンダマスタ情報を返す。
     * 
     * @return McalendarDto カレンダマスタ情報
     */
    McalendarDto getCalDate();

    /**
     * カレンダ情報
     * 日付指定でカレンダマスタ情報を返す。
     * 
     * @param date 日付
     * @return McalendarDto カレンダマスタ情報
     */
    McalendarDto getCalDate(Date date);

    /**
     * 営業日消化(週単位）
     * 営業日数と消化率を返す
     * 
     * @param date 対象日付
     * @param divdatewcal Div年月週（表示用）
     * @return McalendarDto 営業日数と消化率
     */
    McalendarDto calcBizDaysOfWeek(Date date, int divdatewcal);

    /**
     * 営業日消化(月単位)
     * 営業日数と消化率を返す
     * 
     * @param date 対象日付
     * @param divcaldate Divカレンダー年月
     * @return McalendarDto 営業日数と消化率
     */
    McalendarDto calcBuzDaysOfMonth(Date date, String divcaldate);

}
