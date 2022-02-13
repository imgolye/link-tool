package org.springlink.core.secure;

import lombok.Data;

/**
 * @Author: Gol
 */
@Data
public class TokenInfo {

    /**
     * 令牌值
     */
    private String token;

    /**
     * 过期秒数
     */
    private int expire;

}
