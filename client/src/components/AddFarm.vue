<template>
    <section>
        <p class="errorMessage" v-if="errorMessage">{{ errorMessage }}</p>
        <form @submit.prevent="addFarm">
            <div class="form-group">
                <label for="name">Farm name</label>
                <input type="text" id="name" v-model="farmName" required>
            </div>
            <button type="submit" class="submit-button">Add new</button>
        </form>
    </section>
</template>

<script>

export default {
    name: 'AddFarm',
    props: {
        farms: {
            type: Array,
            required: true
        }
    },
    data() {
        return {
            farmName: '',
            errorMessage: ''
        };
    },
    methods: {
        addFarm() {
            const farmExists = this.farms.some(farm => farm.name === this.farmName);
            if (farmExists) {
                this.errorMessage = 'There is already a farm with that name.';
                return;
            }
            this.$emit('add-farm', this.farmName);
            this.farmName = '';
            this.errorMessage = '';
    
        }
    }
};
</script>

<style scoped>
section {
    max-width: 20rem;
    margin: 2rem auto;
    padding: 1.5rem;
    background-color: #f9f9f9;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    text-align: center;
}

label {
    display: block;
    margin-bottom: 0.5rem;
    font-weight: bold;
    color: #333;
}

input[type="text"]{
    display: inline;
    width: 100%;
    border: 1px solid #ddd;
    border-radius: 5px;
    font-size: 1rem;
    max-width: 100%;
}

.errorMessage {
    color: red;
    margin-bottom: 1rem;
}

.submit-button {
    padding: 0.6rem 1.2rem;
    background-color: #4CAF50;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 1rem;
    transition: background-color 0.3s ease;
}

.submit-button:hover {
    background-color: #10ec56;
}
</style>