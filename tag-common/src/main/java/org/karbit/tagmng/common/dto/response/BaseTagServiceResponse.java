package org.karbit.tagmng.common.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.karbit.tagmng.common.ResultStatus;
import org.karbit.skeleton.base.result.dto.BaseResponse;
import org.karbit.skeleton.base.result.dto.ResultSummary;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseTagServiceResponse extends BaseResponse {
	public BaseTagServiceResponse() {
		this(
				ResultSummary.of(ResultStatus.SUCCESS)
		);
	}

	public BaseTagServiceResponse(ResultSummary result) {
		setResult(result);
	}
}
