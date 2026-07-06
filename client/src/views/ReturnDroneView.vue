<template>
  <div class="return-drone-view uniform-input">
    <h1>Return Drone</h1>
    <ReturnDrone />
  </div>
</template>

<script>
import ReturnDrone from '@/components/ReturnDrone.vue';

export default {
  components: {
    ReturnDrone,
  },
};
</script>

<style scoped>
.uniform-input {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.uniform-input input,
.uniform-input select,
.uniform-input textarea {
  width: 100%;
  max-width: 18.75rem; /* 300px to rem */
  margin: 0.5rem 0; /* Add margin for spacing */
}
</style>
