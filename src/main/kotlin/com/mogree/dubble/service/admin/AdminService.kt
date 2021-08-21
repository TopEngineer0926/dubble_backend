package com.mogree.dubble.service.admin

import com.mogree.dubble.mapper.toModels
import com.mogree.dubble.storage.repository.UserRepository
import com.mogree.dubble.storage.specification.UserSpecification
import com.mogree.server.gen.api.AdminApiDelegate
import com.mogree.server.gen.param.ParamGetUserList
import com.mogree.server.gen.param.ParamPaging
import com.mogree.spring.response.ListResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service

@Primary
@Service
class AdminService @Autowired constructor(
    private val userRepo: UserRepository
) : AdminApiDelegate {

    override fun getUserList(paramPaging: ParamPaging, paramGetUserList: ParamGetUserList?): Any {
        // find all by specification
        val mappedModels = this.userRepo.findAll(UserSpecification(paramPaging)).toModels()

        // map to response object
        return ListResponse(
            mappedModels, mappedModels.size, paramPaging.offset,
            if (paramPaging.limit != null && paramPaging.limit > 0) paramPaging.limit else null
        )
    }

}
