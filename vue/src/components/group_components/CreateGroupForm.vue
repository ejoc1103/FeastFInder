<template>
    <div id="create-group-container">
        <form id="create-group-form" v-on:submit.prevent="submitGroup">
            <label for="group-name">Group Name</label>
            <input type="text" id="group-name" name="groupName" v-model="groupName">
            
            <label for="group-description">Description</label>
            <input type="text" id="group-description" name = "groupDescription" v-model="groupDescription">
            
            <label for="start-date">Start Date</label>
            <input type="date" id="start-date" name="startDate" v-model="startDate" required>
            
            <label for = "end-date">End Date</label>
            <input type="date" id="end-date" name="endDate" v-model="endDate">
            
            <button type="submit">Create This Group</button>
        </form>
        <h1>{{ currentDate }}</h1>
        <button  v-on:click="hideGroup">Hide Create Form</button>
    </div>
</template>

<script>
export default {
    data() {
        return {
            groupName: '',
            groupDescription: '',
            startDate: '',
            endDate: '',
            isActive : true
        }
    
    },
    computed:{
        currentDate() {
            console.log(new Date());
            return new Date().toISOString().split('T')[0];
        }
    },
    methods: {
        submitGroup() {
            console.log(this.endDate.getTime()<this.startDate.getTime());
            console.log(this.endDate.getTime()< new Date().getTime());
        },
        hideGroup() {
            this.isActive = false;
        }
    }
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