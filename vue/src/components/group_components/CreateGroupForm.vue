<template>
    <div id="create-group-container">
        <button v-if="$store.state.showGroupForm" v-on:click="toggleGroup">Hide Create Form</button>
        <form id="create-group-form" v-on:submit.prevent="submitGroup">
            <div class="event-container">
                <div class="sub-container">

                    <label for="group-name">
                        <h3>Group Name:</h3>
                    </label>
                    <input type="text" id="group-name" name="groupName" v-model="newGroup.vote_name">
                </div>
            </div>
            <div class="event-container">
                <div class="sub-container">
                    <label for="group-description">
                        <h3>Description:</h3>
                    </label>
                    <input type="text" id="group-description" name="groupDescription"
                        v-model="newGroup.group_description">
                </div>
            </div>
            <div class="event-container">
                <div class="sub-container">

                    <label for="vote-date">
                        <h3>Vote Date:</h3>
                    </label>
                    <input type="date" id="vote-date" name="voteDate" v-model="newGroup.vote_date">
                </div>
            </div>
            <div class="event-container">
                <div class="sub-container">
                    <label for="event-date">
                        <h3>
                            Event Date:
                        </h3>
                    </label>
                    <input type="date" id="event-date" name="eventDate" v-model="newGroup.event_date">
                </div>
            </div>

            <button id="create-group-button" type="submit">Create This Group</button>
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
        toggleGroup() {
            this.$store.commit("TOGGLE_GROUP_FORM", !this.$store.state.showGroupForm);
        },
        submitGroup() {
            let currentDate = new Date();
            let newEventDate = new Date(this.newGroup.event_date);
            let newVoteDate = new Date(this.newGroup.vote_date);

            // if (currentDate > newEventDate || currentDate > newVoteDate) {
            //     console.log("Date cannot be in the past");
            // } else {
            // From here to next comment is a placeholder for the actual endpoint
            // the id part wont be needed when we have an endpoint

            // I believe this will work when there is an end point for creating a vote
            VoteService.createGroup(this.newGroup)
                .then(response => {
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
    }
}
</script>

<style>
h3 {
    font-size: 1.2rem;
    color: #8A2BE2;
    background-color: #E6E6FA;
    padding: 1vh;
    border-radius: 10px;
}

#create-group-container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    border-radius: 30px;
}

#create-group-form {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    align-items: center;
    padding: 1rem;
    gap: 15px;
    border-radius: 30px;
    background-color: #ea6190;
    max-width: 50vw;
    justify-content: space-evenly;
    box-shadow: 0px 0px 10px 5px #20d3ed;
}

.event-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    background-image: url('../../borderImage.png');
    border-radius: 30px;
    padding: 2vw;
    width: 15vw;
}

.sub-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    background-color: #fdac2a;
    border-radius: 30px;
    padding: 20px;
}
</style>