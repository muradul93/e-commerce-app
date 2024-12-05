package com.murad.ecommerce.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {

  private final String msg;

  public BusinessException(String msg) {
    this.msg = msg;
  }

  public String getMsg() {
    return msg;
  }
}
