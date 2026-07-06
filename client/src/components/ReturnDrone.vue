<template>
  <div class="return-drone-form">
    <h2>Return Drone</h2>
    <form class="form-group" @submit.prevent="submitForm">
      <div class="form-container">
        <div class="form-group">
          <label for="droneId">Drone ID:</label>
          <select id="droneId" v-model="droneId" required>
            <option disabled value="">Select a drone</option>
            <option v-for="drone in drones" :key="drone.id" :value="drone.id">{{ drone.id }}</option>
          </select>
        </div>
        <div class="form-group">
          <label for="reason">Reason for Return:</label>
          <select id="reason" v-model="reason" required>
            <option disabled value="">Select a reason</option>
            <option value="maintenance">Maintenance Required</option>
            <option value="battery">Battery Issue</option>
            <option value="damage">Physical Damage</option>
            <option value="software">Software Malfunction</option>
            <option value="other">Other</option>
          </select>
        </div>
        <label for="extraDetails">Extra Details:</label>
        <textarea id="extraDetails" v-model="extraDetails" placeholder="Provide any additional information here"
          required></textarea>
        <button class="submit-btn">Submit</button>
      </div>
      <div v-if="message" class="message messageFadeIn">{{ message }}</div>
    </form>
  </div>
</template>

<script>
import DroneService from '@/services/drone-service.js';
export default {
  data() {
    return {
      droneId: "",
      reason: "",
      extraDetails: "",
      message: null,
      drones: [],
      droneService: new DroneService(),
    };
  },
  async created() {
    await this.getDrones();
  },
  methods: {
    async getDrones() {
      this.drones = await this.droneService.getAllDrones();
    },
    async submitForm() {
      this.message = null;
      try {
        await this.droneService.returnDrone(this.droneId);
        this.message = `We have received your feedback for drone ${this.droneId}. Thank you!`;
      } catch (error) {
        console.error("Error returning drone:", error);

      } finally {
        this.droneId = "";
        this.reason = "";
        this.extraDetails = "";
      }
    },
  },
};
</script>

<style scoped>
.return-drone-form {
  width: 100%;
  max-width: 25rem;
  margin: 2rem auto;
  padding: 1.5rem;
  background-color: #f9f9f9;
  border-radius: 0.5rem;
  box-shadow: 0 0.125rem 0.3125rem rgba(0, 0, 0, 0.1);
  font-family: 'Arial', sans-serif;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.form-container {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.form-group {
  margin-bottom: 1rem;
  width: 100%;
}

input[type="text"],
select,
textarea {
  padding: 0.75rem;
  border: 0.0625rem solid #ccc;
  border-radius: 0.25rem;
  font-size: 1rem;
}

textarea {
  resize: vertical;
  min-height: 6.25rem;
}
</style>
