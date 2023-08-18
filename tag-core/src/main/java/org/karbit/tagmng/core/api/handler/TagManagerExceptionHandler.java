package org.karbit.tagmng.core.api.handler;

import lombok.extern.slf4j.Slf4j;
import org.karbit.skeleton.base.result.dto.BaseResponse;
import org.karbit.skeleton.base.result.dto.ResultSummary;
import org.karbit.skeleton.base.result.exception.ServiceInvocationException;
import org.karbit.tagmng.common.dto.response.BaseTagServiceResponse;
import org.karbit.tagmng.common.exception.BaseTagServiceException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class TagManagerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BaseTagServiceException.class)
	public final ResponseEntity<BaseResponse> handleInvalidParameterException(BaseTagServiceException exception) {
		logger.warn("invalid param error", exception);
		return ResponseEntity.unprocessableEntity().body(
				new BaseTagServiceResponse(ResultSummary.of(exception.getResult()))
		);
	}

	@ExceptionHandler(ServiceInvocationException.class)
	public final ResponseEntity<BaseResponse> handleServiceInvocationException(ServiceInvocationException exception) {
		logger.warn("invoking service was failure", exception);
		return ResponseEntity.internalServerError().body(
				new BaseTagServiceResponse(ResultSummary.of(exception.getResult()))
		);
	}
}
