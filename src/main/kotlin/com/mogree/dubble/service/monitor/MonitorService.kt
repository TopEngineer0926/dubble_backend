package com.mogree.dubble.service.monitor

import com.mogree.dubble.config.security.getCurrentUserId
import com.mogree.dubble.entity.db.MonitorEntity
import com.mogree.dubble.storage.repository.MonitorRepository
import com.mogree.dubble.storage.repository.MonitorRepositoryWithQuery
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class MonitorService(
        private val monitorRepository: MonitorRepository,
        private val monitorRepositoryWithQuery: MonitorRepositoryWithQuery
) {

    fun getMonitors(offset: Int, limit: Int): List<MonitorEntity> =
            monitorRepositoryWithQuery.findAll(offset, limit, getCurrentUserId())

    fun getSize(): Int =
            monitorRepositoryWithQuery.getSize(getCurrentUserId())

    fun addMonitor(monitor: MonitorEntity): ResponseEntity<MonitorEntity> =
            ResponseEntity.ok(monitorRepository.save(monitor))

    fun getMonitorById(monitorId: Long): ResponseEntity<MonitorEntity> =
            monitorRepository.findById(monitorId).map { task ->
                ResponseEntity.ok(task)
            }.orElse(ResponseEntity.notFound().build())

    fun putMonitor(monitorId: Long, newMonitor: MonitorEntity): ResponseEntity<MonitorEntity> =
            monitorRepository.findById(monitorId).map { currentTask ->
                val updatedTask: MonitorEntity =
                        currentTask
                                .copy(
                                        internalPageTitle = newMonitor.internalPageTitle,
                                        productId = newMonitor.productId,
                                        contactId = newMonitor.contactId,
                                        userId = newMonitor.userId,
                                        customerId = newMonitor.customerId,
                                        sender = newMonitor.sender,
                                        receiver = newMonitor.receiver,
                                        email = newMonitor.email,
                                        phone = newMonitor.phone,
                                        sendingDate = newMonitor.sendingDate,
                                        sentStatus = newMonitor.sentStatus
                                )
                ResponseEntity.ok().body(monitorRepository.save(updatedTask))
            }.orElse(ResponseEntity.notFound().build())

    fun deleteMonitor(monitorId: Long): ResponseEntity<Void> =
            monitorRepository.findById(monitorId).map { task ->
                monitorRepository.delete(task)
                ResponseEntity<Void>(HttpStatus.ACCEPTED)
            }.orElse(ResponseEntity.notFound().build())

    fun getAllSubAccounts(): ArrayList<Int> =
            monitorRepositoryWithQuery.getAllSubAccounts(getCurrentUserId())
}