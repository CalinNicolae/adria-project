import { get } from '@/assets/utils/api/api-methods.js';

export default class PlantedCropsService {
    async getPlantedCrops(farmId, farmFieldId) {
        return get(`plantedCrops/${farmId}/${farmFieldId}`);
    }
}
