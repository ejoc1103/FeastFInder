<template>
  <div>
    <div v-if="$store.state.showRestaurants" class="eatery-grid-container">
      <LoadingComponent v-if="isLoading"/>
      <EateryCard :restaurants="restaurants" />
      <button v-if="!isLoading" v-on:click="nextPage">Next Card</button>
    </div>
  </div>
</template>

<script>
import EateryCard from "./EateryCard.vue";
import RestaurantService from "../../services/RestaurantService";
import LoadingComponent from '../loading_components/LoadingComponent.vue';

export default {
  data() {
    return {
      restaurants: [],
      currentResponse: [],
      start: 0,
      end: 8,
      isLoading: false,
    };
  },
  components: {
    EateryCard,
    LoadingComponent
  },
  methods: {
    findEatery() {
      this.isLoading = true;
      this.restaurants = [];
      this.currentResponse = [];
      this.start = 0;
      this.end = 8;
      let searchTerm = this.$store.state.currentSearch;
      if (searchTerm === "") {
        searchTerm = this.getLocation;
      }
    
      let search = `${this.$store.state.currentSearch}&categories="${this.$store.state.category}`;

      RestaurantService.getRestaurants(search).then((response) => {
        console.log(response);
        this.currentResponse = response;
        this.updateRestaurants();
        this.$store.commit("TOGGLE_RESTAURANTS", true);
        this.isLoading = false;
      });
    },
    nextPage() {
      this.start += 8;
      this.end += 8;
      this.updateRestaurants();
    },
    updateRestaurants() {
      this.restaurants = [];
      for (let i = this.start; i < this.end; i++) {
        if (this.currentResponse.data[i]) {
          this.restaurants.push(this.currentResponse.data[i]);
        }
      }
    },
  },
  created() {
    this.$store.subscribe((mutation, state) => {
      if (mutation.type === 'SET_CATEGORY' || mutation.type === 'SET_SEARCH_TERM') {
        this.findEatery();
      }
    });
  }
};
</script>

<style scoped>
.eatery-grid-container {
  display: grid;
  grid-template-columns: 1fr;
  justify-items: center;
  max-width: 95%;
}
</style>