package org.karbit.tagmng.common.exception;

import org.karbit.skeleton.base.result.exception.BaseException;

public abstract class BaseTagServiceException extends BaseException {

	public BaseTagServiceException(String message) {
		super(message);
	}

	public BaseTagServiceException(Throwable cause) {
		super(cause);
	}

	public BaseTagServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
