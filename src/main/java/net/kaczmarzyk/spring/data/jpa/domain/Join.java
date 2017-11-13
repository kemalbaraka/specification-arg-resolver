package net.kaczmarzyk.spring.data.jpa.domain;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import net.kaczmarzyk.spring.data.jpa.utils.QueryContext;

/**
 *
 * @author Tomasz Kaczmarzyk
 */
public class Join<T> implements Specification<T>, Fake {

	private String pathToJoinOn;
	private String alias;
	private JoinType joinType;
	private QueryContext queryContext;
	private boolean distinctQuery;
	
	
	public Join(QueryContext queryContext, String pathToJoinOn, String alias, JoinType joinType, boolean distinctQuery) {
		this.pathToJoinOn = pathToJoinOn;
		this.alias = alias;
		this.joinType = joinType;
		this.queryContext = queryContext;
		this.distinctQuery = distinctQuery;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		query.distinct(distinctQuery);
		queryContext.put(alias, root.join(pathToJoinOn, joinType));
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alias == null) ? 0 : alias.hashCode());
		result = prime * result + (distinctQuery ? 1231 : 1237);
		result = prime * result + ((joinType == null) ? 0 : joinType.hashCode());
		result = prime * result + ((pathToJoinOn == null) ? 0 : pathToJoinOn.hashCode());
		result = prime * result + ((queryContext == null) ? 0 : queryContext.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Join other = (Join) obj;
		if (alias == null) {
			if (other.alias != null)
				return false;
		} else if (!alias.equals(other.alias))
			return false;
		if (distinctQuery != other.distinctQuery)
			return false;
		if (joinType != other.joinType)
			return false;
		if (pathToJoinOn == null) {
			if (other.pathToJoinOn != null)
				return false;
		} else if (!pathToJoinOn.equals(other.pathToJoinOn))
			return false;
		if (queryContext == null) {
			if (other.queryContext != null)
				return false;
		} else if (!queryContext.equals(other.queryContext))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Join [pathToJoinOn=" + pathToJoinOn + ", alias=" + alias + ", joinType=" + joinType + ", queryContext=" + queryContext
				+ ", distinctQuery=" + distinctQuery + "]";
	}
}