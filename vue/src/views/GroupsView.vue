<template>
    <div class="groups-container">
        <create-group-form v-if="$store.state.showGroupForm" />

        <button v-if="!$store.state.showGroupForm" v-on:click="toggleGroup" class="show-all-groups">
            Create a New Feast
        </button>
        <button v-if="$store.state.showVoteView" v-on:click="hideGroup(idToShow)" class="view-group">
            Back To Feasts View
        </button>

        <div v-if="!$store.state.showGroupForm" class="groups">
            <div v-for="group in groups" :key="group.id" :class="isActive(group.is_active)" class="group">
                <div v-if="!$store.state.showVoteView">
                    <h2>{{ group.vote_name }}</h2>
                    <button v-on:click="goToVoting(group.vote_id)" class="view-group"> Vote Now </button>
                    <button v-on:click="showGroup(group.id)" class="view-group">
                        View Feast
                    </button>
                </div>
                <div v-if="$store.state.showVoteView" class="voting">
                    <GroupCard :group="group" />
                </div>
            </div>
        </div>
    </div>
</template>


<script>
import CreateGroupForm from '../components/group_components/CreateGroupForm.vue';
import VoteService from '../services/VoteService.js';
import GroupCard from '../components/group_components/GroupCard.vue';

export default {
    data() {
        return {
            groups: [],
            idToShow: null,
        };
    },
    components: {
        CreateGroupForm,
        GroupCard,
    },
    created() {
        VoteService.getVotes().then(response => {
            this.groups = response.data;
        }).catch(e => {
            console.log(e);
        });
    },
    methods: {
        toggleGroup() {
            this.$store.commit("TOGGLE_GROUP_FORM", !this.$store.state.showGroupForm);
        },
        showGroup(id) {
            this.idToShow = id;
            this.$store.commit("TOGGLE_VOTE_VIEW", true);
        },
        hideGroup() {
            this.$store.commit("TOGGLE_VOTE_VIEW", false);
            this.idToShow = null;
        },
        isActive(isActive) {
            return isActive ? "active-group" : "inactive-group";
        },
        goToVoting(voteId) {
            // Use Vue Router to navigate to /voting/{voteId}
            this.$router.push(`/voting/${voteId}`);
        },
    }
};
</script>

<style scoped>
.groups-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    color: #E6E6FA;
    padding-top: 2vh;
    width: 90%;
    margin: 0 auto;
}

.group-banner {
    font-size: 1.5em;
    background-color: #8A2BE2;
    color: #E6E6FA;
    padding: 15px;
    border-radius: 10px;
    margin-bottom: 20px;
    text-align: center;
}

.groups {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 20px;
    width: 100%;
}

.group {
    display: flex;
    flex-direction: column;
    align-items: center;
    background-color: #00FFFF;
    color: #36454F;
    width: 22%; /* Adjusted for responsiveness */
    min-width: 250px; /* Minimum width to avoid cards becoming too small */
    max-width: 300px; /* Maximum width to control excessive card size */
    padding: 15px;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.group h2 {
    font-size: 1.3em;
    margin-bottom: 10px;
    text-align: center;
}

.active-group {
    background-color: #FF69B4;
    color: #E6E6FA;
    padding: 15px;
    border-radius: 10px;
}

.inactive-group {
    background-color: #00FFFF;
    color: #36454F;
    padding: 15px;
    border-radius: 10px;
}

.view-group, .show-all-groups {
    border: none;
    padding: 10px;
    border-radius: 5px;
    cursor: pointer;
    background-color: #8A2BE2;
    color: #E6E6FA;
    width: 100%; /* Ensures the button takes full width of its container */
    max-width: 180px; /* Limits button size */
    margin-top: 10px;
    transition: background-color 0.3s;
    text-align: center; /* Center-aligns button text */
}

.view-group:hover, .show-all-groups:hover {
    background-color: #6b1dbf;
}

.voting {
    display: grid;
    grid-template-columns: 1fr;
    justify-items: center;
    gap: 20px;
    background-color: rgba(255, 105, 180, 0.5);
    padding: 5%;
    border-radius: 10%;
}

.group-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 100%;
    padding: 10px 0;
}

.group-title {
    font-size: 1.5em;
    margin: 0;
    padding-right: 10px;
    flex-grow: 1;
}

.view-group {
    background-color: #8A2BE2;
    color: #E6E6FA;
    border: none;
    padding: 8px 12px;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
}

.view-group:hover {
    background-color: #6b1dbf;
}


</style>