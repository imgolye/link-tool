package org.springlink.core.secure;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Gol
 */
@Data
@ApiModel(description = "认证信息")
public class AuthInfo {
    @ApiModelProperty(value = "令牌")
    private String accessToken;
    @ApiModelProperty(value = "令牌类型")
    private String tokenType;
    @ApiModelProperty(value = "刷新令牌")
    private String refreshToken;
    @ApiModelProperty(value = "用户ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    @ApiModelProperty(value = "第三方系统ID")
    private String oauthId;
    @ApiModelProperty(value = "头像")
    private String avatar;
    @ApiModelProperty(value = "角色名")
    private String authority;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "账号名")
    private String account;
    @ApiModelProperty(value = "过期时间")
    private long expiresIn;
    @ApiModelProperty(value = "许可证")
    private String license = "powered by link";
}

