package com.mogree.dubble.storage.specification

import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

/**
 * The class should contain abstract methods which can be used by all inherited classes.
 */
abstract class AbstractSpecification<T> : Specification<T> {

    companion object { // here should be common attribute names

        protected object Attribute {
            const val USER = "user"
        }

    }

    // initialize empty list of predicates
    protected val mainPredicates: MutableList<Predicate> = mutableListOf()

    /**
     * Set sorting.
     */
    protected fun sortBy(
        sortColumn: String?,
        sortOrder: String?,
        root: Root<T>,
        query: CriteriaQuery<*>,
        builder: CriteriaBuilder
    ) {
        // if sort column was set -> sort
        // if was not set -> skip sorting and use default
        if (sortColumn != null) {
            val direction = Sort.Direction.valueOf(sortOrder?.toUpperCase() ?: Sort.Direction.ASC.name)
            val order = if (direction.isAscending) {
                builder.asc(root.get<Any>(sortColumn))
            } else {
                builder.desc(root.get<Any>(sortColumn))
            }
            query.orderBy(order);
        }
    }

    /**
     * Common filtering by user.
     */
    protected fun byUser(root: Root<T>, builder: CriteriaBuilder, userId: Long): Predicate {
        return builder.equal(root.get<Long>(Attribute.USER), userId)
    }

}
