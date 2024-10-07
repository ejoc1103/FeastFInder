<template>

  <div v-if="$store.state.showRestaurants" class="eatery-grid-container">
    <!-- for now this can be helpful for debugging api calls may also be part
    of an added feature later -->
    <!-- <h1>{{ this.search }}</h1> -->
    <EateryCard :restaurants="restaurants" />
    <button v-on:click="nextPage">Next Card</button>
  </div>

</template>

<script>
import EateryCard from "./EateryCard.vue";
import RestaurantService from "../../services/RestaurantService";

export default {
  data() {
    return {
      restaurants: [],
      currentResponse: [],
      start: 0,
      end: 3,
      search: '',
    };
  },
  components: {
    EateryCard,
  },
  methods: {
    findEatery() {
      this.isLoading = true;
      this.restaurants = [];
      this.currentResponse = [];
      this.start = 0;
      this.end = 3;

      this.search = ``

      let location = this.$store.state.currentSearch;
      let term = this.$store.state.term;
      let category = this.$store.state.category;

      if (location === ``) {
        this.search = `latitude=${this.$store.state.latitude}&longitude=${this.$store.state.longitude}`;
      } else {
        this.search = `location=${this.$store.state.currentSearch}`;
      }


      if (term !== `` && category !== ``) {
        this.search += `&term=${this.$store.state.category} ${this.$store.state.term}`;
      } else if (term !== ``) {
        this.search += `&term=${this.$store.state.term}`;
      } else if (category !== ``) {
        // Can definitely refine this later
        this.search += `&term=${this.$store.state.category}`;

      }
      this.$store.commit('SET_LOADING', true);
      RestaurantService.getRestaurants(this.search).then((response) => {
        this.currentResponse = response;
        this.updateRestaurants();
        this.$store.commit("TOGGLE_RESTAURANTS", true);
        this.$store.commit('SET_LOADING', false);
      });

    },
    nextPage() {
      this.start += 3;
      this.end += 3;
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
      if (mutation.type === 'SET_SEARCH_TERM' || mutation.type === 'SET_LOCATION') {
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
button {
  color: #f2fae6;
  background-color: #228B22;
  border-radius: 20px;
  padding: 15px;
  margin: 5px;
  box-shadow: 0px 0px 10px #8A2BE2;
}
</style>