package org.karbit.tagmng.core.model;

import java.util.Arrays;
import java.util.Iterator;

import org.springframework.util.CollectionUtils;

public enum Status {
	ACTIVE(0),
	REMOVED(1);

	private final int id;

	Status(int id) {
		this.id = id;
	}

	public void validateNextState(Status targetState) {
		Iterator<Status> validNextStates = Arrays.stream(getNextState(this)).iterator();
		if (Boolean.FALSE.equals(CollectionUtils.contains(validNextStates, targetState))) {
			throw new IllegalStateException("Can not change state of tag to -> " + targetState);
		}
	}

	public Status[] getNextState(Status status) {
		return switch (status) {
			case ACTIVE -> new Status[] { REMOVED };
			case REMOVED -> new Status[] { ACTIVE };
		};
	}
}
