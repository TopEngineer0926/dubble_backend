package com.mogree.dubble.service.monitor

import com.mogree.dubble.entity.db.MonitorEntity
import com.mogree.dubble.service.monitor.payload.MonitorListResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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
        val list: List<MonitorEntity> = monitorService.getMonitors(offset, limit)
        val response = MonitorListResponse(offset, list, limit, list.size)
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