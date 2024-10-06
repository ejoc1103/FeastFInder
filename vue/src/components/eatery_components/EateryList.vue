<template>

  <div v-if="$store.state.showRestaurants" class="eatery-grid-container">
    <LoadingComponent v-if="isLoading" />
    <EateryCard :restaurants="restaurants" />
    <button v-if="!isLoading" v-on:click="nextPage">Next Card</button>
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
      end: 6,
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
      this.end = 6;

      let search = ``

      let location = this.$store.state.currentSearch;
      let term = this.$store.state.term;
      let category = this.$store.state.category;

      if (location === '') {
        search = `latitude=${this.$store.state.latitude}&longitude=${this.$store.state.longitude}`;
      } else {
        search = `location=${this.$store.state.currentSearch}`;
      }


      if (term !== '' && category !== '') {
        search += `&term=${this.$store.state.category}${this.$store.state.term}`;
      } else if (term !== '') {
        search += `&term=${this.$store.state.term}`;
      }
      if (category !== '') {
        // Can definitely refine this later
        search += `&term=${this.$store.state.category}`;

      }
      console.log(search);
      RestaurantService.getRestaurants(search).then((response) => {
        this.currentResponse = response;
        this.updateRestaurants();
        this.$store.commit("TOGGLE_RESTAURANTS", true);
        this.isLoading = false;
      });
    },
    nextPage() {
      this.start += 6;
      this.end += 6;
      this.updateRestaurants();
    },
    updateRestaurants() {
      this.restaurants = [];
      for (let i = this.start; i < this.end; i++) {
        if (this.currentResponse.data[i]) {
          this.currentResponse.data[i].eatery_id = i;
          this.restaurants.push(this.currentResponse.data[i]);
        }
      }
    },
  },
  created() {
    this.$store.subscribe((mutation, state) => {
      if (mutation.type === 'SET_CATEGORY' || mutation.type === 'SET_SEARCH_TERM' || mutation.type === 'SET_LOCATION') {
        this.findEatery();
      }
    });
    if (this.$store.state.longitude !== '') {
      this.findEatery();
    }
  }
};
</script>

<style scoped>
.eatery-grid-container {
  display: grid;
  grid-template-columns: 1fr;
  justify-items: center;
}
</style>