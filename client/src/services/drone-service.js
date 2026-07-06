import { get, patch } from '@/assets/utils/api/api-methods';
import { IOT_DEVICE_URL } from '@/assets/utils/config/config';

export default class DroneService {
    async getAllDrones() {
        const adriaId = localStorage.getItem('AdriaId');
        return get(`users/${adriaId}/drones`);
    }

    async blinkDrone() {
        return get(`api/blink`, {}, IOT_DEVICE_URL);
    }
    async changeStatus(droneId, activity) { 
        return patch(`drones/${droneId}/status`, { activity });
    }
    async returnDrone(droneId) { 
        return patch(`drones/${droneId}/return`);
    }
    async rentDrone() {
        const adriaId = localStorage.getItem('AdriaId');
        return patch(`users/${adriaId}/drones/rent`);
    }
}
