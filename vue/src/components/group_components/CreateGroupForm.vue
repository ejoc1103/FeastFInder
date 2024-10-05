<template>
    <div id="create-group-container">
        <form id="create-group-form" v-on:submit.prevent="submitGroup">
            <label for="group-name">Group Name</label>
            <input type="text" id="group-name" name="groupName" v-model="groupName">

            <label for="group-description">Description</label>
            <input type="text" id="group-description" name="groupDescription" v-model="groupDescription">

            <label for="vote-date">Vote Date</label>
            <input type="date" id="vote-date" name="voteDate" v-model="voteDate" required>

            <label for="event-date">Event Date</label>
            <input type="date" id="event-date" name="eventDate" v-model="eventDate">

            <button type="submit">Create This Group</button>
        </form>
        <button v-on:click="hideGroup">Hide Create Form</button>
    </div>
</template>

<script>
import VoteService from '../../services/VoteService.js';
export default {
    data() {
        return {
            groupName: '',
            groupDescription: '',
            eventDate: '',
            voteDate: '',
            isActive: true
        }

    },
    methods: {
        submitGroup() {
            let currentDate = new Date();
            let newEventDate = new Date(this.eventDate);
            let newVoteDate = new Date(this.voteDate);
            if (currentDate > newEventDate || currentDate > newVoteDate) {
                console.log("Date cannot be in the past");
            } else {


                let newEvent = {
                    name: this.groupName,
                    description: this.groupDescription,
                    eventDate: this.eventDate,
                    voteDate: this.voteDate,
                    isActive: this.isActive
                }

                VoteService.createGroup(newEvent)
                    .then(response => {
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            }
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
    gap: 10px;
    ;
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