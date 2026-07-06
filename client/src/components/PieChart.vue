<template>
    <canvas :id="this.canvasId" />
</template>

<script>
import Chart from 'chart.js/auto';
export default {
    name: "Recommendations",
    props: {
        statistics: {
            type: Object,
            required: true
        },
        canvasId: {
            type: String,
            required: true
        }
    },
    watch: {
        statistics() {
            this.chart.destroy();
            this.makePieChart();
        }
    },
    mounted() {
        this.makePieChart();
    },
    methods: {
        makePieChart() {
            const canvasId = this.canvasId;
            const ctx = document.querySelector("#" + canvasId).getContext('2d');
            const labels = Object.keys(this.statistics);
            const data = Object.values(this.statistics);
            const backgroundColors = this.generateColors(data.length);
            const borderColors = backgroundColors.map(color => color.replace('0.2%', '1'));

            const options = {
                type: 'pie',
                data: {
                    labels: labels,
                    datasets: [{
                        label: 'Farm statistics',
                        data: data,
                        backgroundColor: backgroundColors,
                        borderColor: borderColors,
                        borderWidth: 1
                    }]
                },
            };
            this.chart = new Chart(ctx, options);
        },
        generateColors(count) {
            const colors = [];
            for (let i = 0; i < count; i++) {
                const maxHue = 360;
                const hue = Math.floor((i * maxHue) / count);
                colors.push(`rgba(${hue}, 162, 235, 0.2)`);
            }
            return colors;
        },
    }
};
</script>
