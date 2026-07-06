<template>
    <div class="recommendations-container">
        <h3>Overall health: <span>{{ cropHealth }}</span></h3>
        <ul>
            <li v-for="(recommendation, index) in recommendations" :key="index">
                {{ recommendation }}
            </li>
        </ul>
    </div>
</template>

<script>
export default {
    name: "Recommendations",
    data() {
        return {
            cropHealth: "Bad",
            recommendations: [
                "Water the crops",
                "Use pesticides",
                "Use fertilizers"
            ]
        };
    }
};
</script>

<style scoped>
.recommendations-container {
    background: linear-gradient(135deg, #e6ecdae0, #8bc274);
    padding: 2rem;
    border-radius: 1rem;
    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.1);
    text-align: center;
    transition: transform 0.3s, box-shadow 0.3s;
}

.recommendations-container:hover {
    transform: scale(1.02);
    box-shadow: 0 1rem 2rem rgba(0, 0, 0, 0.2);
}

h3 {
    font-size: 1.8rem;
    margin-bottom: 1rem;
    text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.1);
}

span {
    color: #f65353;
    font-weight: bold;
}

ul {
    list-style-type: none;
    padding: 0;
}

li {
    margin: 0.5rem 0;
    padding: 0.75rem 1rem;
    background-color: #ffffff;
    border-radius: 0.5rem;
    box-shadow: 0 0.1rem 0.3rem rgba(0, 0, 0, 0.1);
    transition: transform 0.3s, box-shadow 0.3s;
}

li:hover {
    transform: scale(1.05);
    box-shadow: 0 0.2rem 0.5rem rgba(0, 0, 0, 0.2);
}

li::before {
    content: "❗";
    color: #2ecc71;
    margin-right: 0.5rem;
}
</style>
