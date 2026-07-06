<template>
    <header>
        <div id="droneInput">
            <label for="allDrones">Choose the drone</label>
            <select name="drones" v-model="selectedDroneId">
                <option disabled value="">Select a drone</option>
                <option v-for="drone in drones" :key="drone.id" :value="drone.id"> {{ drone.id }} </option>
            </select>
        </div>
    </header>
    <section v-if="selectedDrone">
        <div id="functions">
            <h2>Change the status of the drone to:</h2>
            <div class="circle-view">
                <button @click="changeStatus('AUTO')">Auto</button>
                <ul class="functions-list">
                    <li v-for="(action, index) in actions" :key="index" @click="changeStatus(action)">
                        {{ formattedActivity(action) }}
                    </li>
                    <li @click="findDrone">Find me</li>
                </ul>
            </div>
            <p v-if="message" :class="this.messageAnimation + ' message'">{{ message }}</p>
        </div>
        <div id="drone">
            <Drone :drone="selectedDrone" />
        </div>
    </section>
    <LoadingScreen v-if="loading" @cancel-loading="loading=false" />
</template>

<script>
import Drone from '../components/DroneDetails.vue';
import DroneService from '@/services/drone-service';
import { firstLetterUpperFormat } from '@/assets/utils/util-functions.js';
import LoadingScreen from '@/components/LoadingScreen.vue';

export default {
    name: 'DroneFunctionView',
    components: {
        Drone,
        LoadingScreen
    },
    data() {
        return {
            selectedDroneId: this.$route.query.id || '',
            drones: [],
            // Drone actions defined on the server
            actions: ['PASSIVE', 'PLANTING', 'WATERING', 'HARVESTING', 'CHARGING'],
            message: null,
            messageAnimation: null,
            loading: false,
        };
    },
    methods: {
        async changeStatus(activity) {
            const droneService = new DroneService();
            this.loading = true;
            try {
                this.messageAnimation = "messageFadeIn";
                this.message = null;
                await droneService.changeStatus(this.selectedDrone.id, activity);
                this.selectedDrone.activity = activity; // Update locally
                this.message = `Drone ${this.selectedDrone.id} activity changed to ${activity}`;
            } catch (error) {
                this.message = `Error changing status: ${error.message}`;
            } finally {
                this.loading = false;
                const messageDuration = 5000;
                setTimeout(() => {
                    this.messageAnimation = "messageFadeOut";
                    setTimeout(() => {
                        this.message = null;
                    }, 1000);
                }, messageDuration);
            }
        },
        async fetchDrones() {
            const droneService = new DroneService();
            this.drones = await droneService.getAllDrones();
        },
        async findDrone() {
            const droneService = new DroneService();
            this.loading = true;
            try {
                await droneService.blinkDrone(this.selectedDrone.id);
                this.message = `Drone ${this.selectedDrone.id} was successfully located!`;
            } catch (error) {
                this.message = `Error finding drone ${this.selectedDrone.id}: ${error.message}`;
            } finally {
                this.loading = false;
            }
        },
        formattedActivity(action) {
            return firstLetterUpperFormat(action);
        }
    },
    computed: {
        selectedDrone() {
            return this.drones.find(drone => drone.id === this.selectedDroneId);
        }
    },
    async created() {
        this.fetchDrones();
    }
};
</script>

<style scoped>
section {
    display: grid;
    grid-template-columns: 25% 1fr 25%;

}

#functions {
    grid-column: 2;
    justify-self: center;
    text-align: center;
}

#drone {
    grid-column: 3;
}

#droneInput {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin-top: 2rem;
}

ul.functions-list {
    list-style-type: none;
    padding: 0;
    display: flex;
    flex-direction: column;
    align-items: center;
}

li {
    justify-self: center;
    background-color: #c8e6c9;
    width: fit-content;
    padding: 0.5rem 1rem;
    margin: 0.5rem;
    border-radius: 0.5rem;
    cursor: pointer;
    transition: background-color 0.3s, transform 0.3s;
}

h2 {
    margin-bottom: 1rem;
}

li:hover {
    background-color: #a5d6a7;
    transform: scale(1.05);
}

li:active {
    background-color: #aacbab;
}

.circle-view {
    position: relative;
    width: 15rem;
    height: 15rem;
    border: 0.25rem solid var(--green-primary);
    border-radius: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 2rem auto;
    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.1);
    transition: transform 0.3s;
}

.circle-view:hover {
    transform: scale(1.05);
}

.circle-view button {
    position: absolute;
    width: 4rem;
    height: 4rem;
    border: 0.25rem solid var(--green-primary);
    border-radius: 50%;
    background-color: var(--green-primary);
    color: white;
    font-size: 1rem;
    cursor: pointer;
    transition: background-color 0.3s, transform 0.3s;
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    z-index: 1;
}

.circle-view button:hover {
    background-color: var(--green-hover);
    transform: scale(1.1);
}

.functions-list {
    position: absolute;
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    list-style-type: none;
    padding: 0;
    margin: 0;
}

.functions-list li {
    position: absolute;
    width: 3rem;
    height: 3rem;
    background-color: #c8e6c9;
    border-radius: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
    transition: background-color 0.3s, transform 0.3s;
}

.functions-list li:hover {
    background-color: #a5d6a7;
    transform: scale(1.1);
}

.functions-list li:nth-child(1) {
    top: 0;
    left: 50%;
    transform: translate(-50%, -50%);
}

.functions-list li:nth-child(2) {
    top: 25%;
    left: 85%;
    transform: translate(-50%, -50%);
}

.functions-list li:nth-child(3) {
    top: 75%;
    left: 85%;
    transform: translate(-50%, -50%);
}

.functions-list li:nth-child(4) {
    top: 100%;
    left: 50%;
    transform: translate(-50%, -50%);
}

.functions-list li:nth-child(5) {
    top: 75%;
    left: 15%;
    transform: translate(-50%, -50%);
}

.functions-list li:nth-child(6) {
    top: 25%;
    left: 15%;
    transform: translate(-50%, -50%);
}
</style>
