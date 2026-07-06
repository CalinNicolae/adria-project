<template>
    <form @submit.prevent="login">
        <input type="text" v-model="password" :placeholder="this.adriaId" :name="passwordName" required :value="this.adriaId" disabled>
        <button type="submit">Login</button>
        <p class="error">{{ this.error }}</p>
    </form>
</template>

<script>

// FIXED ADRIAID FOR POC
const ADRIA_ID = '50b1871f-e600-4791-9566-787b029854b7';

import { fetchLogin } from '@/services/login.js';
export default {
    props: {
        successfulLoginPath: {
            type: String,
            required: true
        },
        passwordName: {
            type: String,
            required: true
        }
    },
    data() {
        return {
            adriaId: ADRIA_ID,
            password: '',
            error: ''
        };
    },
    methods: {
        async login() {
            const response = await (fetchLogin(this.password));
            localStorage.setItem("AdriaId", this.adriaId);
            if (response || response.Success) {
                this.$router.push(this.successfulLoginPath);
            } else {
                this.error = 'Invalid Adria ID';
            }
        }
    }
};
</script>

<style scoped>
input {
    padding: 0.5rem;
    margin: 0.5rem;
    border: 1px solid #10ec56;
    border-radius: 5px;
}
</style>
