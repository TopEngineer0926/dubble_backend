package com.mogree.dubble.storage.specification

import com.mogree.dubble.entity.db.UserEntity
import com.mogree.server.gen.param.ParamPaging
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class UserSpecification(
    private val param: ParamPaging
) : AbstractSpecification<UserEntity>() {

    override fun toPredicate(
        root: Root<UserEntity>,
        query: CriteriaQuery<*>,
        builder: CriteriaBuilder
    ): Predicate {
        // if sorting was set -> set sorting
        super.sortBy(param.sortColumn, param.sortOrder, root, query, builder)

        // build predicates into one
        return builder.and(*mainPredicates.toTypedArray())
    }

}
