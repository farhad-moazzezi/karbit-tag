package org.karbit.tagmng.core.model;

import java.util.Objects;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.karbit.skeleton.mongo.model.BaseMongoEntity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "tag")
@NoArgsConstructor
@AllArgsConstructor
public class Tag extends BaseMongoEntity {

	@NotEmpty
	@Indexed(unique = true, background = true)
	private String caption;

	@NotNull
	private Status status;

	public void setStatus(Status status) {
		if (Objects.nonNull(this.status)) {
			this.getStatus().validateNextState(status);
		}
		this.status = status;
	}
}
