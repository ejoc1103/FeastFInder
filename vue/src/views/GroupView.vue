<template>
    <div class="groups">
        <h1>Group View</h1>
        <div v-for="group in groups" v-bind:key="group" class="group">
            <h1>{{ group }}</h1>
            <button>View Group</button>
        </div>
    </div>
</template>

<script>

export default {

    data() {
        return {
            groups: ["group1", "group2", "group3"],
        }
    }
}
</script>

<style>
.groups {
    display: grid;
    justify-items: center;
    align-content: start;
    gap: 20px;
    height: 100vh;
}
.group {
    display: flex;
    flex-direction: column;
    width: 90%;
    background-color: #FF6F61;
}
</style>