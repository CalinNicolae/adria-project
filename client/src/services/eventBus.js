import { reactive } from 'vue';
import { getNotifications } from '@/services/notification-service.js';
export const eventBus = reactive({
    numberOfUnreadMessages: 0,

    async fetchUnreadNotifications() {
        const notifications = await getNotifications();
        this.numberOfUnreadMessages = notifications.filter(notification => !notification.read).length;
    }
});
