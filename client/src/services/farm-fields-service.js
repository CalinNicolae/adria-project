import { get } from '@/assets/utils/api/api-methods.js';

export default class FarmFieldsService {
    async getFarmFields(farmId) {
        return get(`farmFields/${farmId}`);
    }
}
