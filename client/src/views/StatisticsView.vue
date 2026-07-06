<script>
import Recommendations from "@/components/Recommendations.vue";
import PieChart from "@/components/PieChart.vue";
import StatisticsService from "@/services/statistics-service.js";
import DroneService from "@/services/drone-service.js";

export default {
  components: {
    PieChart,
    Recommendations
  },
  data() {
    return {
      numberOfDrones: 0,
      numberOfActiveDrones: 0,
      selectedFarm: this.$route.query.name || '',
      statistics: [],
      currentSlide: 0,
    };
  },
  computed: {
    totalSlides() {
      return this.statistics.length + 1;
    }
  },
  async created() {
    this.statistics = await this.getStatistics();
    this.numberOfDrones = await this.getAllDrones();
    this.numberOfActiveDrones = await this.getActiveDrones();

    const queryStatName = this.$route.query.name;
    if(queryStatName) {
      this.currentSlide = this.getStatIdx(queryStatName);
    }
  },
  methods: {
    getStatIdx(statName) {
      for(let statIdx = 0; statIdx < this.statistics.length; statIdx++) {
        const stat = this.statistics[statIdx];
        if(stat.name === statName) {
          return statIdx;
        }
      }
    },
    async getStatistics() {
      const statisticsService = new StatisticsService();
      return await statisticsService.getStatistics();
    },
    async getAllDrones() {
      const droneService = new DroneService();
      const drones = await droneService.getAllDrones();
      return drones.length;
    },
    async getActiveDrones() {
      const droneService = new DroneService();
      const drones = await droneService.getAllDrones();
      return drones.filter(drone => !["CHARGING", "PASSIVE"].includes(drone.activity)).length;
    },
    nextSlide() {
      this.currentSlide = (this.currentSlide + 1) % this.totalSlides;
    },
    prevSlide() {
      this.currentSlide = (this.currentSlide - 1 + this.totalSlides) % this.totalSlides;
    }
  }
};

</script>

<template>
  <section class="statistics-page">
    <div class="statistics-header">
      <h2>Working drones: {{this.numberOfActiveDrones}}/{{ this.numberOfDrones }}</h2>
    </div>
    <div class="statistics-slideshow">
      <div class="statistics-container" :style="{ transform: `translateX(-${currentSlide * 100}%)` }">
        <div class="statistics-card" v-for="statistic in statistics">
          <h3>{{statistic.name}}</h3>
          <PieChart :statistics="statistic.values" class="pieChart" :canvas-id="statistic.id" />
        </div>
        <div class="statistics-card">
          <h3>Recommendations</h3>
          <Recommendations />
        </div>
      </div>
      <div class="slideshow-controls">
        <button class="slideshow-button" @click="prevSlide">Back</button>
        <button class="slideshow-button" @click="nextSlide">Next</button>
      </div>
    </div>
  </section>
</template>

<style scoped>
.container {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  box-sizing: border-box;
}

.pieChart {
  width: 30rem !important;
  height: 30rem !important;
}

.sharedView {
  display: flex;
  flex-direction: row;
  align-content: center;
  align-items: center;
  justify-content: center;
  width: 100%;
}

hr {
  width: 50%;
  border: none;
  height: 2px;
  background: linear-gradient(to right, #2ecc71, #27ae60);
  margin: 2rem 0;
  border-radius: 1px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
</style>
