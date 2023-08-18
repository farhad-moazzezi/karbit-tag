package org.karbit.tagmng.common;

import java.util.Set;

import org.karbit.skeleton.base.result.DefaultResultStatus;

public class ResultStatus extends DefaultResultStatus {

	public static final ResultStatus EX_EMPTY_TAG_CATEGORY = new ResultStatus(422004001, "ex.empty.tag.category");

	public static final ResultStatus EX_EMPTY_TAG_CAPTION = new ResultStatus(422004002, "ex.empty.tag.caption");

	public static final ResultStatus EX_TAG_NOT_FOUND = new ResultStatus(422004003, "ex.tag.not.found");

	protected ResultStatus(int statusCode, String bundleId) {
		super(statusCode, bundleId);
	}

	@Override
	protected Set<String> getBundleName() {
		return Set.of("message.properties");
	}
}
