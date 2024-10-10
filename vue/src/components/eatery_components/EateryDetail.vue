<template>
    <h2 class="restaurant-name">{{ restaurantDetails.eatery_name }}</h2>
    <div class="more-status">
        <h3>
            Currently:{{ restaurantDetails.isClosed ? "Open Now" : "Closed" }}
        </h3>
    </div>

    <div>
        <h3>
            Opens: {{ formatTime(restaurantDetails.open_time) }}
        </h3>
        <h3>
            Closes: {{ formatTime(restaurantDetails.close_time) }}
        </h3>
    </div>
    <div>
        <h3 v-if="!$store.state.moreDetailsView">
            {{ restaurantDetails.city }}
        </h3>
        <h3>
            {{ restaurantDetails.category }}
        </h3>
    </div>
    <h3>
        {{ restaurantDetails.eatery_address }}
    </h3>
    <h3>
        {{ restaurantDetails.phone }}
    </h3>

    <a v-if="restaurantDetails.website !== null" :href="restaurantDetails.website" target="blank" class="website-link">Link to their
        online menu!</a>

    <h3>
        {{ restaurantDetails.price }}
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
    <button @click="hideInfo()">Show All</button>
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

<style></style>