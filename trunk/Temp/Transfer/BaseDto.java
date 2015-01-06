package com.posiba.button.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.posiba.button.api.v1.base.ApiUtil;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public abstract class BaseDto {

    /**
     * @see com.posiba.button.api.v1.base.ApiUtil.isNullOrEmpty(Object)
     */
    protected static boolean isNullOrEmpty(Object value) {
        return ApiUtil.isNullOrEmpty(value);
    }
}
