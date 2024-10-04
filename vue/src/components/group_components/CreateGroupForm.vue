<template>
    <div id="create-group-container">
        <form id="create-group-form">
            <label for="group-name">Group Name</label>
            <input type="text" id="group-name" name="groupName">
            <button type="submit">Create This Group</button>
        </form>
        <button v-if="showGroupForm" v-on:click="hideGroup">Hide Create Form</button>
    </div>
</template>

<script>
export default {
    data() {
        return {
            groupName: '',
        }
    },
}
</script>

<style>
#create-group-container {
    display: grid;
    grid-template-columns: 1fr;
    background-color: #E6E6FA;
    gap: 10px;;
    border-radius: 10px;
    padding: 5px;
    margin: 5px;
    color: #8A2BE2;
}

#create-group-form {
    display: flex;
    flex-wrap: wrap;
    max-width: 50vw;
    justify-content: center;
    align-items: center;
    gap: 10px;
}
</style>