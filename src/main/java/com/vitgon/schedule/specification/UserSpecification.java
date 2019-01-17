package com.vitgon.schedule.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.vitgon.schedule.model.auth.User;

public class UserSpecification implements Specification<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3179430881970395959L;
	
	private String keyword;

	public UserSpecification(String keyword) {
		super();
		this.keyword = keyword;
	}

	@Override
	public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		Predicate predicate = criteriaBuilder.disjunction();
		
		if (keyword != null) {
			predicate.getExpressions()
				.add(criteriaBuilder.or(
						criteriaBuilder.like(root.get("mail"), keyword)
//						criteriaBuilder.like(root.get("teacherTranslations.firstname"), keyword),
//						criteriaBuilder.like(root.get("teacherTranslations.lastname"), keyword),
//						criteriaBuilder.like(root.get("teacherTranslations.middlename"), keyword)
					)
				);
						
		}
		
		return predicate;
	}
}
