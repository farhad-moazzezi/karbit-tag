package org.karbit.tagmng.common.exception;

import org.karbit.tagmng.common.ResultStatus;

public class TagNotFoundException extends BaseTagServiceException{
	public TagNotFoundException(String message) {
		super(message);
	}

	public TagNotFoundException(Throwable cause) {
		super(cause);
	}

	public TagNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public ResultStatus getResult() {
		return ResultStatus.EX_TAG_NOT_FOUND;
	}
}
