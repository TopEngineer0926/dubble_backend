package com.mogree.dubble.storage.repository

import com.mogree.dubble.entity.db.MonitorEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional(Transactional.TxType.MANDATORY)
interface MonitorRepository : JpaRepository<MonitorEntity, Long>