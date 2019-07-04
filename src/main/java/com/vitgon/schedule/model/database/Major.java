package com.vitgon.schedule.model.database;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vitgon.schedule.dto.DegreeEnum;
import com.vitgon.schedule.model.database.translation.MajorTranslation;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(of = {"url", "duration", "school"})
@Entity
@Table(
		name = "majors",
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
	private Set<Group> groups = new HashSet<>();
	
	@OneToMany(mappedBy = "major", fetch = FetchType.LAZY)
	private List<MajorTranslation> translations = new ArrayList<>();
}
