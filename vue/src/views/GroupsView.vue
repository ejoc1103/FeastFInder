<template>
    <div class="groups-container">

        <h1>Group View</h1>


        <div class="voting" v-if="showOneGroup">
            <!-- this is where I am working on the group view -->
            <EateryList :restaurants="makeRestaurantArray" />
            <button v-on:click="hideGroup(idToShow)" class="view-group">Show All Groups</button>
        </div>

        <div class="groups" v-else>
            <div v-for="group in groups" v-bind:key="group" class="group">
                <h1>{{ group.name }}</h1>
                <button v-on:click="showGroup(group.id)" class="view-group">View Group</button>
            </div>
        </div>


    </div>
</template>

<script>
import EateryList from '../components/eatery_components/EateryList.vue';

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
            colorScheme: [
                "#FF69B4", // Bright Pink
                "#FFFF66", // Neon Yellow
                "#00FFFF", // Aqua Blue
                "#E6E6FA", // Lavender
                "#8A2BE2", // Bright Purple
                "#7FFF00", // Electric Green
                "#2F4F4F", // Dark Slate Gray (for text)
                "#36454F"  // Charcoal (for text)
            ]
        }
    },
    components: {
        EateryList,
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
            for (let i = 0; i < 8; i++) {
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
    display: grid;
    grid-template-columns: 1fr;
    justify-items: center;
}

.groups {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    justify-items: center;
    gap: 20px;
}

.group {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
    background-color: #FF6F61;
}

.view-group {
    background-color: lightblue;
    color: white;
    border: none;
    padding: 10px;
    margin: 10px;
    border-radius: 5px;
    cursor: pointer;
    width: 50%;
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