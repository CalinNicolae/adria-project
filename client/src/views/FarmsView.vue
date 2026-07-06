<template>
    <div class="container">
        <h1>Farms</h1>
        <div class="farms-container">
            <Farm v-for="(farm, index) in farms" :key="farm.id" :farm="farm" />
        </div>

        <AddFarm @add-farm="handleAddFarm" :farms="farms" />

    </div>
</template>

<script>
import Farm from '@/components/Farm.vue';
import FarmService from '@/services/farms-service.js';
import AddFarm from '@/components/AddFarm.vue';
export default {
    name: 'FarmsView',
    components: {
        Farm,
        AddFarm
    },
    emits: ['add-farm'],
    data() {
        return {
            farms: [{ name: 'Farm 1', adriaId: 'adriaId example' }],
            adriaId: localStorage.getItem('AdriaId')
        };
    },
    async created() {
        const farmService = new FarmService();
        farmService.getFarms().then((response) => {
            for(const farm of response) {
                farm.statName = "Crop Types - " + farm.name;
            }
            this.farms = response;
        });
    },
    methods: {
        handleAddFarm(farmName) {
            const newFarm = {
                name: farmName
            };
            this.farms.push(newFarm);
        }
    }
};
</script>

<style scoped>
.farms-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    align-items: center;
    gap: 1rem;
    padding: 0 1rem;
}
</style>
