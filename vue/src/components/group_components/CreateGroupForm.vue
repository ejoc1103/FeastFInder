<template>
    <div id="create-group-container">
        <form id="create-group-form" v-on:submit.prevent="submitGroup">
            <label for="group-name">Group Name</label>
            <input type="text" id="group-name" name="groupName" v-model="newGroup.vote_name">

            <label for="group-description">Description</label>
            <input type="text" id="group-description" name="groupDescription" v-model="newGroup.group_description">

            <label for="vote-date">Vote Date</label>
            <input type="date" id="vote-date" name="voteDate" v-model="newGroup.vote_date">

            <label for="event-date">Event Date</label>
            <input type="date" id="event-date" name="eventDate" v-model="newGroup.event_date">

            <button type="submit">Create This Group</button>
        </form>

    </div>
</template>

<script>
import VoteService from '../../services/VoteService.js';
export default {
    data() {
        return {
            newGroup: {
                vote_name: '',
                vote_description: '',
                event_date: '',
                vote_date: '',
                is_active: true,
                eateries: [],
            }

        }

    },
    methods: {
        submitGroup() {
            let currentDate = new Date();
            let newEventDate = new Date(this.newGroup.event_date);
            let newVoteDate = new Date(this.newGroup.vote_date);
            this.newGroup.event_date = newEventDate;
            this.newGroup.vote_date = newVoteDate;
            // if (currentDate > newEventDate || currentDate > newVoteDate) {
            //     console.log("Date cannot be in the past");
            // } else {
            // From here to next comment is a placeholder for the actual endpoint
            // the id part wont be needed when we have an endpoint
          
            // I believe this will work when there is an end point for creating a vote
            console.log(this.newGroup);
            VoteService.createGroup(this.newGroup)
                .then(response => {
                    console.log(response.data);
                    if (response.status === 201) {
                        alert("New Group Created");
                        this.newGroup = {};
                        this.$store.commit('TOGGLE_GROUP_FORM', !this.$store.state.showGroupForm);
                    }
                })
                .catch(e => {
                    console.log(e);
                });
            this.resetForm();
        },
        resetForm() {
            this.newGroup = {
                vote_name: '',
                vote_description: '',
                event_date: '',
                vote_date: '',
                is_active: true
            }
        }
    },

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