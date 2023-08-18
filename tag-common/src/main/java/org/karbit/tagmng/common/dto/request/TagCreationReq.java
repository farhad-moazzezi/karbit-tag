package org.karbit.tagmng.common.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.karbit.skeleton.base.result.dto.BaseRequest;

@Data
@EqualsAndHashCode(callSuper = true)
public class TagCreationReq extends BaseRequest {

	private String category;

	private String caption;
}
