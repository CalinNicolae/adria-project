<template>
    <section>
        <form @submit.prevent="submitForm">

            <h1>You currently have: {{ currentDrones }} drones</h1>

            <label for="nr-of-drones">Number of drones you want to rent</label>
            <input type="number" id="nr-of-drones" min="1" max="10" v-model="nrOfDronesToRent">


            <h2>Total: {{ cost }} AdriaCoins</h2>

            <button type="submit">Rent</button>
            <p v-if="message!==null" class="message messageFadeIn">{{ this.message }}</p>

        </form>
    </section>
</template>

<script>
import DroneService from "../services/drone-service.js";
export default {
    name: 'RentDroneView',
    data() {
        return {
            nrOfDronesToRent: 0,
            cost: 0,
            currentDrones: 0,
            dronePrice: 8000,
            message: null,
            droneService: new DroneService()
        };
    },
    created() {
        this.fetchDrones();
    },
    methods: {
        async submitForm() {

            let nrOfSuccessfulDroneRents = 0;
            for (let i = 0; i < this.nrOfDronesToRent; i++) {
                try {
                    await this.droneService.rentDrone();
                    nrOfSuccessfulDroneRents++;
                } catch (error) {
                    console.error("Error renting drones:", error);
                    break;
                }
            }

            const payment = nrOfSuccessfulDroneRents * this.dronePrice;

            this.message = "";
            if (nrOfSuccessfulDroneRents !== this.nrOfDronesToRent) {
                this.message += `Sorry, not all drones were available. `;
            }
            this.message += `You rented ${nrOfSuccessfulDroneRents} drones for ${payment} AdriaCoins`;

            await this.fetchDrones();
        },
        async fetchDrones() {
            try {
                const drones = await this.droneService.getAllDrones();
                this.currentDrones = drones.length;
            } catch (error) {
                console.error("Error fetching drones:", error);
            }
        }
    },
    watch: {
        nrOfDronesToRent() {
            if (this.nrOfDronesToRent > 10) {
                this.nrOfDronesToRent = 10;
            }
            this.cost = this.nrOfDronesToRent * this.dronePrice;
        },
    },
};

</script>

<style scoped>
button {
    margin-top: 1rem;
    padding: 0.6rem 1.2rem;
    background-color: #4CAF50;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 1rem;
    transition: background-color 0.3s ease;
}

button:hover {
    background-color: #45a049;
}

form {
    width: fit-content;
    margin: auto;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin-top: 3rem;
    padding: 2rem;
    background-color: #f5f5f5;
}

input {
    margin-bottom: 2rem;
}

h2 {
    cursor: default;
}
</style>
