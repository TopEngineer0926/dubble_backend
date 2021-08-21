package com.mogree.dubble.storage.specification

import com.mogree.dubble.entity.db.CustomerEntity
import com.mogree.dubble.validator.ValidatorHelper
import com.mogree.server.gen.param.ParamPaging
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class CustomerSpecification(
    private val userId: Long?,
    private val param: ParamPaging
) : AbstractSpecification<CustomerEntity>() {

    override fun toPredicate(
        root: Root<CustomerEntity>,
        query: CriteriaQuery<*>,
        builder: CriteriaBuilder
    ): Predicate {
        this.buildPredicates(root, builder) // fill needed predicates

        // if sorting was set -> set sorting
        super.sortBy(param.sortColumn, param.sortOrder, root, query, builder)

        // build predicates into one
        return builder.and(*mainPredicates.toTypedArray())
    }

    /* Private Methods */

    private fun buildPredicates(root: Root<CustomerEntity>, builder: CriteriaBuilder) {
        if (ValidatorHelper.isValidEntityId(userId)) {
            mainPredicates.add(super.byUser(root, builder, this.userId!!))
        }
    }

}
