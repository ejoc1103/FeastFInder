<template>
    <div class="groups-container">
        <div class="group-banner">
            <h1 v-if="!showGroupForm">My Groups</h1>
            <h1 v-else>Create New Group</h1>
        </div>
        <create-group-form v-if="showGroupForm"/>
        <button v-if="idToShow !== null" v-on:click="showGroup(idToShow)" class="show-all-groups">See All
            Groups</button>
        <button v-if="!showGroupForm" v-on:click="showGroupForm = !showGroupForm">Create a New Group</button>
        <div v-if="showOneGroup" class="voting">
            <!-- this is where I am working on the group view -->
            <!-- <EateryList :restaurants="makeRestaurantArray" /> -->
            <button v-on:click="hideGroup(idToShow)" class="view-group">Show All Groups</button>
        </div>

        <div class="groups" v-else>
            <div v-for="group in groups" v-bind:key="group" class="group">
                <h1>{{ group.name }}</h1>
                <button v-if="idToShow === null" v-on:click="showGroup(group.id)" class="view-group">View Group</button>
            </div>
        </div>

    </div>
</template>

<script>
import EateryList from '../components/eatery_components/EateryList.vue';
import CreateGroupForm from '../components/group_components/CreateGroupForm.vue';
export default {

    data() {
        return {
            groups: [
                { id: 0, name: 'Dinner with friends', showFull: false },
                { id: 1, name: 'Dinner with co-workers', showFull: false },
                { id: 2, name: 'Dinner with family', showFull: false },
                { id: 3, name: 'Blah blah blha', showFull: false },
                { id: 4, name: 'Dad, Dad, Dad', showFull: false },
            ],
            restaurant: {
                id: "c3fv6l74jJcoppmV43RrSw",
                name: "The Poached Pear",
                imageUrl: "https://s3-media1.fl.yelpcdn.com/bphoto/BzXy5EeF71nx54PBokFKRA/o.jpg",
                address: "816 Arnold Ave   Point Pleasant Beach 08742 US NJ",
                category: "Desserts New American Steakhouses ",
                openTime: "1700 1700 1700 1700 1700 1700 ",
                isClosed: "false",
                closeTime: "2030 2030 2030 2130 2130 2030 ",
                hasTakeout: "delivery",
                rating: "4.6",
                phoneNumber: "(732) 701-1700",
                price: "$$$",
            },
            idToShow: null,
            showGroupForm: false,
            colorScheme: [
                "#FF69B4", // Bright Pink
                "#00FFFF", // Aqua Blue
                "#E6E6FA", // Lavender
                "#8A2BE2", // Bright Purple
                "#36454F"  // Charcoal (for text)
            ]
        }
    },
    components: {
        EateryList,
        CreateGroupForm
    },
    //probably needs to be "created" on actual data
    computed: {
        showOneGroup() {
            for (let i = 0; i < this.groups.length; i++) {
                if (this.groups[i].showFull) {
                    return true;
                }
            }
            return false;
        },
        makeRestaurantArray() {
            let restaurantArray = [];
            for (let i = 0; i < 4; i++) {
                restaurantArray.push(this.restaurant);
            }
            return restaurantArray;
        }
    },
    methods: {
        showGroup(id) {
            this.idToShow = id;
            this.groups[id].showFull = !this.groups[id].showFull;
            this.$store.commit("TOGGLE_RESTAURANTS", true);
        },
        hideGroup(id) {
            this.idToShow = null;
            this.groups[id].showFull = !this.groups[id].showFull;
            this.$store.commit("TOGGLE_RESTAURANTS", false);
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