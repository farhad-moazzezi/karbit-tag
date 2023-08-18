package org.karbit.tagmng.common.exception;

import org.karbit.tagmng.common.ResultStatus;

public class EmptyTagCaptionException extends BaseTagServiceException {
	public EmptyTagCaptionException(String message) {
		super(message);
	}

	public EmptyTagCaptionException(Throwable cause) {
		super(cause);
	}

	public EmptyTagCaptionException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public ResultStatus getResult() {
		return ResultStatus.EX_EMPTY_TAG_CAPTION;
	}
}
