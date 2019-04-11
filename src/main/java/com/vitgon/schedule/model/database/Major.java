package com.vitgon.schedule.model.database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vitgon.schedule.dto.DegreeEnum;
import com.vitgon.schedule.model.database.translation.MajorTranslation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"url", "duration", "school"})
@Entity
@Table(
		name = "major",
		uniqueConstraints = @UniqueConstraint(
			   columnNames = {"name","url"},
			   name = "UQ_major_name_url"
	   )
)
public class Major extends BaseModel<Integer> {
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "url", nullable = false)
	private String url;
	
	@Column(name = "duration", nullable = false)
	private int duration;
	
	@JsonBackReference
	@ManyToOne(optional = false)
	@JoinColumn(name = "school_id")
	private School school;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "degree", nullable = false)
	private DegreeEnum degree;
	
	@OneToMany(mappedBy = "major", fetch = FetchType.LAZY)
	private List<Group> groups = new ArrayList<>();
	
	@OneToMany(mappedBy = "major", fetch = FetchType.LAZY)
	private List<MajorTranslation> translations = new ArrayList<>();
}
