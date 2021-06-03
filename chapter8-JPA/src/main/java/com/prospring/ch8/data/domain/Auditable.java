package com.prospring.ch8.data.domain;

import java.io.Serializable;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

import org.springframework.data.domain.Persistable;

public interface Auditable<U, ID extends Serializable,
 T extends TemporalAccessor> extends Persistable<ID> {
	 Optional<U> getCreatedBy();
	 void setCreatedBy(U createdBy);
	 Optional<T> getCreatedDate();
	 void setCreatedDate(T creationDate);
	 Optional<U> getLastModifiedBy();
	 void setLastModifiedBy(U lastModifiedBy);
	 Optional<T> getLastModifiedDate();
	 void setLastModifiedDate(T lastModifiedDate);

}
