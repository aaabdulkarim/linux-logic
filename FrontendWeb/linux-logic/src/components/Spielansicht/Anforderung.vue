<template>
    <div>
        <h1>Level1</h1>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. </p>
    </div>
    <Terminal/>
</template>

<script>
import Terminal from './Terminal.vue';
export default {
    name: 'Anforderung',
    components: {
        Terminal
    }
}

</script>

<style scoped>
h1 {
    color: black;
}
p {
    color: #569191;
}
</style>