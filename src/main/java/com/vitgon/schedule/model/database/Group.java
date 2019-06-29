package com.vitgon.schedule.model.database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vitgon.schedule.model.database.translation.GroupTranslation;

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
@ToString(of = {"number","suffix","courseNum"})
@Entity
@Table(
		name = "_groups",
		uniqueConstraints = @UniqueConstraint(
			columnNames = {"major_id","number","suffix"},
			name = "UQ__groups_major_id_number"
		)	
)
public class Group extends BaseModel<Integer> {
	
	@Column(name = "number", nullable = false)
	private Integer number;
	
	@Column(name = "suffix", nullable = false)
	private String suffix;
	
	@Column(name = "course_num", nullable = false)
	private Integer courseNum;
	
	@JsonIgnore
	@ManyToOne(optional = false)
	@JoinColumn(name="major_id")
	private Major major;
	
	@OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
	private List<Schedule> schedules = new ArrayList<>();
	
	@OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
	private List<GroupTranslation> groupTranslations = new ArrayList<>();

	public Group(int courseNum, Major major) {
		this.courseNum = courseNum;
		this.major = major;
	}
}
