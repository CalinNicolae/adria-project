<template>
  <h1>Number of drones: {{ drones.length }}</h1>
  <section>
    <div v-for="drone in drones" :key="drone.id" class="drone-container">
      <Drone :drone="drone" />
    </div>
  </section>
</template>

<script>
import DroneService from '@/services/drone-service.js';
import Drone from '../components/DroneOverview.vue';

export default {
  name: 'DroneListView',
  components: {
    Drone,
  },
  data() {
    return {
      drones: [],
    };
  },
  methods: {
    async fetchDrones() {
      const droneService = new DroneService();
      this.drones = await droneService.getAllDrones();
    }
  },
  async created() {
    this.fetchDrones();
  }
};
</script>
<style scoped>
section {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}

.drone-container {
  width: 100%;
  max-width: 40rem;
  border-bottom: 2px dashed #2ecc71;
  padding-bottom: 1rem;
  margin-bottom: 2rem;
  position: relative;
}

.drone-container::after {
  content: '';
  position: absolute;
  bottom: -1rem;
  left: 50%;
  transform: translateX(-50%);
  width: 50%;
  height: 2px;
  background-color: #2ecc71;
  transition: width 0.3s;
}

.drone-container:hover::after {
  width: 100%;
}
</style>
