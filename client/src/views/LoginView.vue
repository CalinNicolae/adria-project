<template>
    <h1>Croptek</h1>
    <h2>Authenticate with your Croptek employee ID</h2>
    <LoginComponent successfulLoginPath="/farms" passwordName="password" />
</template>

<script>
import LoginComponent from '@/components/Login.vue';
export default {
    components: {
        LoginComponent
    },
};
</script>
