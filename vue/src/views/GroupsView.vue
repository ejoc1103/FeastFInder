<template>
    <div class="groups-container">
        <h1>Group View</h1>
        <div>

            <div class="groups" v-if="showOneGroup">
                <h1>{{ groups[idToShow] }}</h1>
            </div>

            <div class="groups" v-else>
                <div v-for="group in groups" v-bind:key="group" class="group">
                    <h1>{{ group.name }}</h1>
                    <button v-on:click="showGroup(group.id)" class="view-group">View Group</button>
                </div>
            </div>

        </div>
    </div>
</template>

<script>

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

            restaurants: [{
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
            }],
            idToShow: null,
        }
    },
    computed: {
        showOneGroup() {
            for (let i = 0; i < this.groups.length; i++) {
                if (this.groups[i].showFull) {
                    return true;
                }
            }
            return false;
        }
    },
    methods: {
        showGroup(id) {
            this.idToShow = id;
            this.groups[id].showFull = !this.groups[id].showFull;
        }
    }
}
</script>

<style>
.groups-container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.groups {
    display: grid;
    justify-items: center;
    align-content: start;
    grid-template-columns: 1fr 1fr 1fr;
    gap: 20px;
}

.group {
    display: flex;
    flex-direction: column;
    width: 90%;
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
}
</style>