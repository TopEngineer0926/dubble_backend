package com.mogree.dubble.service.monitor

import com.mogree.dubble.entity.db.MonitorEntity
import com.mogree.dubble.service.monitor.payload.MonitorListResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.mogree.dubble.config.security.getCurrentUserId

@RestController
@RequestMapping("monitor")
class MonitorController (
        private val monitorService: MonitorService
) {

    @GetMapping
    fun getMonitors(
            @RequestParam(value = "offset") offset: Int,
            @RequestParam(value = "limit") limit: Int
    ): ResponseEntity<MonitorListResponse> {
//        var subAccounts: ArrayList<Int> = monitorService.getAllSubAccounts()
//        subAccounts.add(getCurrentUserId().toInt())
        val list: List<MonitorEntity> = monitorService.getMonitors(offset, limit)
        val response = MonitorListResponse(offset, list, limit, monitorService.getSize())
        return ResponseEntity.ok<MonitorListResponse>(response)
    }

    @PostMapping
    fun addMonitor(@RequestBody monitor: MonitorEntity): ResponseEntity<MonitorEntity> =
            monitorService.addMonitor(monitor)

    @GetMapping("/{id}")
    fun getMonitorById(@PathVariable(value="id") monitorId: Long): ResponseEntity<MonitorEntity> =
            monitorService.getMonitorById(monitorId)

    @PutMapping("/{id}")
    fun updateMonitorById(
            @PathVariable(value = "id") monitorId: Long,
            @RequestBody newMonitor: MonitorEntity): ResponseEntity<MonitorEntity> =
            monitorService.putMonitor(monitorId, newMonitor)

    @DeleteMapping("/{id}")
    fun deleteMonitor(@PathVariable(value = "id") monitorId: Long): ResponseEntity<Void> =
            monitorService.deleteMonitor(monitorId)
}