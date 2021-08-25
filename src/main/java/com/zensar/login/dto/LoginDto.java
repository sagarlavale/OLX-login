package com.zensar.login.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Contains the User Credentials")
public class LoginDto {
	@ApiModelProperty(value = "UserName of the User")
	private String userName;
	
	@ApiModelProperty(value = "Password of the User")
	private String password;

}
