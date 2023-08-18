package org.karbit.tagmng.common.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.karbit.tagmng.common.dto.common.TagInfo;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FoundTagResp extends BaseTagServiceResponse {
	private List<TagInfo> tags;
}
