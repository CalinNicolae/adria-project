<template>
    <div v-if="eventBus.numberOfUnreadMessages > 0" class="unread-notifications">
        <p>{{ eventBus.numberOfUnreadMessages }}</p>
    </div>
</template>

<script>
import { eventBus } from '@/services/eventBus.js';
export default {
    name: 'UnreadNotifications',
    data() {
        return {
            eventBus
        };
    },
    async created() {
        eventBus.fetchUnreadNotifications();
    },
};
</script>

<style scoped>
.unread-notifications {
    background-color: red;
    border-color: red;
    color: white;
    border-radius: 50%;
    width: 1.25rem;
    height: 1.25rem;
    display: flex;
    align-items: center;
    justify-content: center;
    float: right;
}
</style>
