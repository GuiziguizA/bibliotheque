package sid.org.service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import sid.org.classe.Livre;
import sid.org.classe.SearchCriteria;

public class LivreSpecificationImpl implements Specification<Livre> {
	
	@Autowired
	private SearchCriteria criteria;
	
	
	public LivreSpecificationImpl(SearchCriteria criteria) {
		super();
		this.criteria = criteria;
	}
	public LivreSpecificationImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Predicate toPredicate(Root<Livre> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThanOrEqualTo(
              root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
		 else if (criteria.getOperation().equalsIgnoreCase("<")) {
	            return builder.lessThanOrEqualTo(
	              root.<String> get(criteria.getKey()), criteria.getValue().toString());
	        }
		 else if (criteria.getOperation().equalsIgnoreCase(":")) {
	            if (root.get(criteria.getKey()).getJavaType() == String.class) {
	                return builder.like(
	                  root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
	            } else {
	                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
	            }
		 }
		return null;
	}

	
}