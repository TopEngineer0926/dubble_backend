package com.mogree.dubble.notification

import com.mogree.dubble.service.account.helper.AccountNotificationHelper
import com.mogree.dubble.service.product.helper.ProductNotificationHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

/**
 * Bridge to send emails asynchronously.
 */
@Component
class AsyncNotificationHelper @Autowired(required = false) constructor(
    private var accountNotificationHelper: AccountNotificationHelper,
    private var productNotificationHelper: ProductNotificationHelper
) {

    @Async
    fun sendActivationEmail(userId: Long) {
        accountNotificationHelper.sendActivationEmail(userId)
    }

    @Async
    fun sendResetPasswordEmail(userId: Long) {
        accountNotificationHelper.sendPasswordResetEmail(userId)
    }

    @Async
    fun sendInviteEmail(userId: Long) {
        accountNotificationHelper.sendInviteEmail(userId)
    }

    @Async
    fun sendProductPublishedEmail(productId: Long, userId: Long) {
        productNotificationHelper.sendProductPublishedEmail(productId, userId)
    }

    @Async
    fun sendProductPublishedSms(productId: Long, userId: Long) {
        productNotificationHelper.sendProductPublishedSms(productId, userId)
    }

}
