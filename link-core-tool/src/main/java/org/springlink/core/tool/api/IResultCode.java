package org.springlink.core.tool.api;

import java.io.Serializable;

/**
 * @Author: Goly
 * @Date: 2022/1/19 下午2:32
 */
public interface IResultCode extends Serializable {

    String getMessage();

    int getCode();


}
