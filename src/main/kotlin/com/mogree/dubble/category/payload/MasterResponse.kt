package com.mogree.dubble.category.payload
import com.mogree.dubble.entity.db.UserEntity

class MasterResponse {
    var isMaster: Boolean
    var masterInfo: UserEntity? = null

    constructor(isMaster: Boolean, masterInfo: UserEntity?) {
        this.isMaster = isMaster
        this.masterInfo = masterInfo
    }

}