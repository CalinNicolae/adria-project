import { get, patch } from '@/assets/utils/api/api-methods.js';

async function getNotifications() {
    const adriaId = localStorage.getItem('AdriaId');
    return await get(`users/${adriaId}/notifications`);
}

async function markNotificationAsRead(index) {
    await patch(`notifications/${index}`, { read: true });
}


export { getNotifications, markNotificationAsRead };
