<template>
    <div class="groups-container">
        <div class="group-banner">
            <h1 v-if="!$store.state.showGroupForm">My Groups</h1>
            <h1 v-else>Create New Group</h1>
        </div>
        <create-group-form v-if="$store.state.showGroupForm" />
        <button v-if="$store.state.showVoteView" v-on:click="showGroup(idToShow)" class="show-all-groups">See All
            Groups</button>
        <button v-if="!$store.state.showGroupForm" v-on:click="toggleGroup">Create a New Group</button>

        <div v-if="!$store.state.showVoteView && groups.length > 0" class="groups">
            <div v-for="group in groups" v-bind:key="group.id" class="group">
                <h1>{{ group.vote_name }}</h1>
                <button v-on:click="getEateries(group.vote_id)" class="view-group">View Group</button>
            </div>
        </div>

        <div v-if="$store.state.showVoteView && $store.state.showVoteView" class="voting">
            <!-- this is where I am working on the group view -->
            <EateryCard :restaurants="restaurants"/>
            <button v-on:click="hideGroup(idToShow)" class="view-group">Back To Groups View</button>
        </div>
    </div>
</template>

<script>
import EateryCard from '../components/eatery_components/EateryCard.vue';
import CreateGroupForm from '../components/group_components/CreateGroupForm.vue';
import VoteService from '../services/VoteService.js';

export default {

    data() {
        return {
            groups: [],
            //here down is just for testing no for development
            colorScheme: [
                "#FF69B4", // Bright Pink
                "#00FFFF", // Aqua Blue
                "#E6E6FA", // Lavender
                "#8A2BE2", // Bright Purple
                "#36454F",  // Charcoal (for text)
                '#7FFF00', // Bright green
                '#228B22', // Darker Green
                '#f7a029', // Orange 
            ],
            restaurants : [],
        }
    },
    components: {
        EateryCard,
        CreateGroupForm
    },
    // computed: {
    //     getGroups() {
    //         return this.$store.state.groups;
    //     }
    // },
    // I commented this out fully and left it in place because We should be 
    // able to just remove the comments, delete the other method with the same name 
    // and it should work
    created() {
            //  This should work when we have a get all votes endpoint
             return VoteService.getVotes().then(response => {
                console.log(response.data);
                 this.groups = response.data;
             }).catch(e => {
                 console.log(e);
             }); 
    },
    methods: {
        getEateries(id) {
            this.restaurants =  this.$store.state.groups[id].eateries;
            this.showGroup();
            console.log(this.restaurants);
        },
        toggleGroup() {
            this.$store.commit("TOGGLE_GROUP_FORM", !this.$store.state.showGroupForm);
        },
        showGroup() {
            this.$store.commit("TOGGLE_VOTE_VIEW", true);
        },
        hideGroup() {
            this.$store.commit("TOGGLE_VOTE_VIEW", false);
        }
    }
}
</script>

<style>
.groups-container {
    color: #E6E6FA;
    display: grid;
    grid-template-columns: 1fr;
    justify-items: center;
    gap: 40px;
    max-height: 92vh;
    padding-top: 2vh;
}

.groups {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-evenly;
    gap: 20px;
}

.group {
    display: flex;
    flex-direction: column;
    align-items: center;
    background-color: #00FFFF;
    color: #36454F;
    width: 25vw;
    padding: 5px;
    border-radius: 50px;
}

.group h1 {
    color: #8A2BE2;
    font-size: 1.4em;
}

.group-banner {
    color: #8A2BE2;
    font-size: 1.2em;
    background-color: #E6E6FA;
    border-radius: 30%;
    border-image: url('../../public/borderImage.png') 30 round;
    border: 10px solid;
}

.group-banner h1 {
    margin: 20px;
}

.view-group {
    border: none;
    padding: 10px;
    border-radius: 5px;
    cursor: pointer;
    width: 50%;
    background-color: #8A2BE2;
    color: #E6E6FA;
}

.show-all-groups {
    border: none;
    padding: 10px;
    border-radius: 5px;
    cursor: pointer;
    background-color: #FF69B4;
    color: #E6E6FA;
}

.voting {
    display: grid;
    grid-template-columns: 1fr;
    justify-items: center;
    gap: 20px;
    background-color: rgb(255, 105, 180, 0.5);
    padding: 5%;
    border-radius: 10%;
}
</style>