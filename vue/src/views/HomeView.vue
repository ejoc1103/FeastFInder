<template>
  <div class="home">
    <EateryFinderFormVue v-on:submit.prevent="findEatery()" />
    <div v-if="$store.state.showRestaurants">
      <EateryCard :restaurants="restaurants" />
    </div>
  </div>
</template>

<script>
import EateryFinderFormVue from "../components/eatery_components/EateryFinderForm.vue";
import EateryCard from "../components/eatery_components/EateryCard.vue";
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
.home {
  display: grid;
  justify-items: center;
}
</style>
