import {get} from "@/assets/utils/api/api-methods.js";

export default class StatisticsService {

    async getStatistics() {
        const adriaId = localStorage.getItem("AdriaId");
        return await this.fetchStatisticsFromServer(adriaId);
    }

    async fetchStatisticsFromServer(adriaId) {
        const statistics = await get(`users/${adriaId}/statistics`);

        for (let i = 0; i < statistics.length; i++) {
            statistics[i].id = "stat-" + i;
        }

        return statistics;
    }

}


