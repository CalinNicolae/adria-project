import { get } from '@/assets/utils/api/api-methods.js';

export default class CropTypesService {
    async getCropTypes() {
        return get(`cropTypes/`);
    }
}
