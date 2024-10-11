<template>
<<<<<<< HEAD
    <h2 class="restaurant-name">{{ restaurantDetails.eatery_name }}</h2>
    <div class="more-status" :style="{ backgroundImage: `${restaurantDetails.image_url}` }">
        <h3>
            Currently:{{ restaurantDetails.isClosed ? "Open Now" : "Closed" }}
        </h3>
    </div>

    <div>
        <h3>
            {{ restaurantDetails.category }}
        </h3>
        <h3>
            Opens: {{ formatTime(restaurantDetails.open_time) }}
        </h3>
        <h3>
            Closes: {{ formatTime(restaurantDetails.close_time) }}
        </h3>
        <h3>
            {{ restaurantDetails.phone }}
        </h3>
    </div>
    <div>
        <h3>
            <span v-for="n in restaurantDetails.price" :key="n">
                <i class="fa-solid fa-dollar-sign"></i>
            </span>
        </h3>
        <h3>
            {{ restaurantDetails.has_takeout ? "They have takeout!" : "No takeout " }} 😢
        </h3>
        <h3>
            <span v-for="n in Math.floor(restaurantDetails.rating)" :key="n">
                <i class="fa-solid fa-star"></i>
            </span>
            <span v-if="restaurantDetails.rating % 1 !== 0">
                <i class="fa-solid fa-star-half-alt"></i> <!-- Half-star for non-integer ratings -->
            </span>
        </h3>
        <a v-if="restaurantDetails.website !== null" :href="restaurantDetails.website" target="blank"
            class="website-link">Link to their
            online menu!</a>
    </div>
    <div>
        <h3>
            {{ restaurantDetails.eatery_address }}
        </h3>

        <button @click="hideInfo()">Show All</button>
=======
    <div class="more-details-view">

        <h2 class="restaurant-name">{{ restaurantDetails.eatery_name }}</h2>
        <div class="more-status" :style="{ backgroundImage: `url(${restaurantDetails.image_url})` }">
            <h3>
                {{ restaurantDetails.isClosed ? "Open Yay!" : "Sorry They're Closed" }}
            </h3>
        </div>

        <div>
            <h3>
                Opens: {{ formatTime(restaurantDetails.open_time) }}
            </h3>
            <h3>
                Closes: {{ formatTime(restaurantDetails.close_time) }}
            </h3>
            <h3>{{ restaurantDetails.category }}</h3>
            <a v-if="restaurantDetails.website !== null" :href="restaurantDetails.website" target="blank"
                class="website-link">See Their Online Menu!</a>
        </div>

        <div class="quick-info">

            <h3>{{ restaurantDetails.price }}</h3>
            <h3>{{ restaurantDetails.has_takeout ? "They have takeout!" : "No takeout " }} 😢</h3>
            <h3>
                <span v-for="n in Math.floor(restaurantDetails.rating)" :key="n">
                    <i class="fa-solid fa-star"></i>
                </span>
                <span v-if="restaurantDetails.rating % 1 !== 0">
                    <i class="fa-solid fa-star-half-alt"></i> <!-- Half-star for non-integer ratings -->
                </span>
            </h3>
            <h3>{{ restaurantDetails.phone }}</h3>
        </div>
        <div class="details-address">
            <h3>{{ restaurantDetails.eatery_address }}</h3>

            <button @click="hideInfo()">Back</button>
        </div>
>>>>>>> 81dee641a255c8cdc93296b2d7cd0df0a7a9f0ac
    </div>
</template>

<script>
export default {
    props: ['restaurantDetails'],
    methods: {
        formatTime(time) {
            let [hours, minutes] = time.split(":");
            if (!minutes) {
                hours = time.slice(0, 2);
                minutes = time.slice(2, 4);
            }
            hours = parseInt(hours, 10);
            let suffix = hours >= 12 ? "PM" : "AM";
            hours = hours % 12 || 12;
            return `${hours}:${minutes} ${suffix}`;
        },
        hideInfo() {
            this.$store.commit('TOGGLE_DETAILS_VIEW');
        },
    }
}
</script>

<style scoped>
<<<<<<< HEAD
/* Grid layout for 3 columns */
.grid-container {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    /* 3 equal columns */
    gap: 20px;
}

/* Style for the header and address to span 3 columns */
.restaurant-name {
    grid-column: span 3;
    text-align: center;
    font-size: 2em;
    font-weight: bold;
}

.more-status,
.address {
    grid-column: span 3;
    /* Span all 3 columns */
    text-align: center;
    padding: 10px;
    border-radius: 8px;
}

img {
    width: 100px;
    height: 100px;
    border-radius: 50%;
}

/* Style for single-column divs */
div {
    padding: 10px;
    border-radius: 8px;
    text-align: left;
}

button {
    grid-column: span 3;
    margin-top: 20px;
    padding: 10px;
    background-color: #228B22;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

button:hover {
    background-color: #1e7d1e;
}

.more-status {
    background-size: cover;
=======
.more-details-view {
    grid-column: span 3;
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    max-height: none;
    /* Allow it to grow based on content */
    text-align: center;
    margin: 20px;
    overflow: hidden;
    /* Hide any overflowing content */
    padding: 10px;
    /* Optional: add some padding for better spacing */
}

.more-details-view div {
    border: 5px solid #8A2BE2;
}

.more-status {
    display: grid;
    background-color: rgba(138, 43, 226, 0.5);
    border-radius: 30px;
    padding: 4px;
    justify-content: center;
    align-items: center;
    display: grid;
    background-size: cover;
}

.more-status h3 {
    background-color: rgba(138, 43, 226, 0.5);
    color: white;
    font-weight: bold;
    border-radius: 30px;
}

.details-address {
    display: grid;
    grid-template-columns: 3fr 1fr;
    grid-column: span 3;
    border-radius: 5px;
    justify-items: center;
    margin: 0;
    background-color: #22b6b2;
    border-radius: 30px;
>>>>>>> 81dee641a255c8cdc93296b2d7cd0df0a7a9f0ac
}
</style>