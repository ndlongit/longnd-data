package com.structis.vip.server.bean.core;

public abstract class AbstractShowAbleBean {

    private static final String SPACE = " ";
    private static final String OPEN_BRACKET = "[";
    private static final String CLOSE_BRACKET = "]";
    private static final String EQUAL = "=";
    private static final String COMMA = ",";

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName());
        sb.append(SPACE);
        sb.append(OPEN_BRACKET);
        this.beanToString(sb);
        sb.append(CLOSE_BRACKET);
        return sb.toString();
    }

    protected abstract void beanToString(StringBuffer sb);

    protected void checkDataToString(final String dataName, final String dataValue, StringBuffer sb) {
        sb.append(dataName);
        sb.append(EQUAL);
        sb.append(dataValue);
        sb.append(COMMA).append(SPACE);
    }

}
