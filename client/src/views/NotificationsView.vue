<template>
  <div class="notifications">
    <header class="enable-notifications-box">
      <h2>Receive realtime updates</h2>
      <button @click="enablePush" class="enable-btn" :disabled="isDisabled"> {{ this.buttonMessage }}</button>
    </header>

    <h1 class="notifications-title">Notifications</h1>

    <ul v-if="notifications.length">
      <li v-for="(notification, index) in notifications" :key="notification.id" class="notification-item"
        @click="openNotificationDetail(notification.id)">
        <div class="notification-text">
          <span :class="{ unread: !notification.read, read: notification.read }">{{ notification.title }}</span>
        </div>

        <div class="notification-actions">
          <button @click.stop="markAsRead(notification.id)"
            :class="['mark-read-btn', { 'read-btn': notification.read }]" :data-id="notification.id">{{
            notification.read ? 'Already Read' : 'Read Notification' }}</button>
        </div>
      </li>
    </ul>

    <p v-else>No notifications</p>

    <div v-if="this.selectedNotification" class="notification-modal">
      <div class="modal-content">
        <h2>{{ selectedNotification.title }}</h2>
        <p>{{ selectedNotification.message }}</p>
        <button @click="closeNotificationDetail" class="close-btn">Close</button>
      </div>
    </div>
  </div>
</template>

<script>
import {
  getNotifications,
  markNotificationAsRead,
} from '@/services/notification-service';
import { eventBus } from '@/services/eventBus.js';
import { enablePushNotifications, isAlreadySubscribed } from "@/services/push-notification-service.js";

export default {
  data() {
    return {
      notifications: [],
      selectedNotification: null,
      buttonMessage: "Enable Notifications",
      isDisabled: false
    };
  },
  methods: {
    async loadNotifications() {
      try {
        this.notifications = await getNotifications();
      } catch (error) {
        console.error(error);
      }
    },

    async markAsRead(index) {
      try {
        this.openNotificationDetail(index);
        await markNotificationAsRead(index);
        await this.loadNotifications();
        await eventBus.fetchUnreadNotifications();
      } catch (error) {
        console.error(error);
      }
    },

    enablePush() {
      enablePushNotifications();
      this.buttonMessage = "Already Subscribed";
      this.isDisabled = true;
    },

    openNotificationDetail(index) {
      this.selectedNotification = { ...this.notifications.find(notification => notification.id === index) };
    },

    closeNotificationDetail() {
      this.selectedNotification = null;
    },

    async checkNotificationStatus() {
      if (await isAlreadySubscribed()) {
        this.buttonMessage = "Already Subscribed";
        this.isDisabled = true;
      }
    }
  },

  async mounted() {
    await this.loadNotifications();
    this.checkNotificationStatus();
  }
};
</script>

<style scoped>
ul {
  display: flex;
  flex-direction: column;
  align-items: center;
}

li {
  width: 75rem;
  cursor: pointer;
}

header {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 2rem;
}

.notifications {
  padding: 2rem;
  background-color: #f9f9f9;
  border-radius: 0.5rem;
  box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.1);
}

.notification-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem;
  margin-bottom: 1rem;
  background-color: #fff;
  border-radius: 0.5rem;
  box-shadow: 0 0.2rem 0.5rem rgba(0, 0, 0, 0.1);
}

.notification-text {
  flex-grow: 1;
}

.notification-text .unread {
  font-weight: bold;
  color: #27ae60;
}

.notification-text .read {
  color: #888;
  opacity: 0.6;
}

.notification-message {
  font-size: 1rem;
  color: #555;
  margin-top: 0.5rem;
}

.notification-actions {
  display: flex;
  justify-content: space-between;
}

.notification-actions button {
  margin-left: 0.5rem;
}

.notification-modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal-content {
  background-color: #fff;
  padding: 2rem;
  border-radius: 0.5rem;
  box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.1);
  width: 90%;
  max-width: 500px;
  text-align: center;
}

p {
  text-align: center;
  font-size: 1.25rem;
  color: #888;
  margin: 1rem;
}

.remove-btn {
  background-color: #bb7e77;
  color: white;
  border: none;
  padding: 0.5rem 1rem;
  cursor: pointer;
  transition: background-color 0.3s, transform 0.3s;
  border-radius: 0.5rem;
  font-size: 1rem;
}

.remove-btn:hover {
  background-color: #c0392b;
  transform: scale(1.05);
}

.enable-notifications-box {
  padding: 1rem;
  background-color: #e0ffe0;
  border: 1px solid #b2f2b2;
  border-radius: 0.5rem;
  text-align: center;
  margin-bottom: 2rem;
  width: 45rem;
  margin-left: auto;
  margin-right: auto;
}

.mark-read-btn {
  width: 10rem;
  max-width: 10rem;
}

.read-btn {
  background-color: #ccc;
  color: #666;
  cursor: pointer;
}
</style>
