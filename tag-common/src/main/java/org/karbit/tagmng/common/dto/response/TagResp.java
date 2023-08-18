package org.karbit.tagmng.common.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TagResp extends BaseTagServiceResponse {

	private String caption;
}
