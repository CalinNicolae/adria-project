import { get, post } from '@/assets/utils/api/api-methods.js';

export default class FarmService {
    async getFarms() {
        const adriaId = localStorage.getItem('AdriaId');
        return get(`users/${adriaId}/farms`);
    }

    async addFarm(adriaId, farmName) {
        return post(`users/${adriaId}/farms`, { name: farmName });
    }
}
