<template>
  <div class="home">
    <EateryFinderFormVue v-on:submit.prevent="findEatery()" />
    <div class="eatery-grid" v-if="$store.state.showRestaurants">
      <EateryCard v-for="restaurant in restaurants" v-bind:key="restaurant.id" />
    </div>
  </div>
</template>

<script>
import EateryFinderFormVue from "../components/EateryFinderForm.vue";
import EateryCard from "../components/EateryCard.vue";
import RestaurantService from "../services/RestaurantService";


export default {
  data() {
    return {
      restaurants: [],
      isLoading: false,
    };
  },
  components: {
    EateryFinderFormVue,
    EateryCard,
  },
  methods: {
    findEatery() {
      let search = this.$store.state.currentSearch;
      RestaurantService.getRestaurants(search).then((response) => {
        console.log(response.data);
        this.restaurants = response.data;
      });
      this.$store.commit("TOGGLE_RESTAURANTS");
    },
  }


};
</script>
<style scoped>
.eatery-grid {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-template-rows: 1fr 1fr 1fr;
  gap: 20px;
  justify-items: center;
  align-items: stretch;
  height: 100%;
}

.home {
  display: grid;
  justify-items: center;
}
</style>
