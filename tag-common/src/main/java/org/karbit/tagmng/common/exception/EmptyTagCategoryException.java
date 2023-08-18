package org.karbit.tagmng.common.exception;

import org.karbit.tagmng.common.ResultStatus;

public class EmptyTagCategoryException extends BaseTagServiceException{
	public EmptyTagCategoryException(String message) {
		super(message);
	}

	public EmptyTagCategoryException(Throwable cause) {
		super(cause);
	}

	public EmptyTagCategoryException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public ResultStatus getResult() {
		return ResultStatus.EX_EMPTY_TAG_CATEGORY;
	}
}
