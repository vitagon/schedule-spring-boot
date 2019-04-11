package com.vitgon.schedule.model.database;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@MappedSuperclass
public abstract class BaseModel<T> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected T id;
}
