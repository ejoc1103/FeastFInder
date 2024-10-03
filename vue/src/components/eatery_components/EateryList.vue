<template>
    <div v-if="$store.state.showRestaurants" class="eatery-grid-container">
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
        end: 8,
      };
    },
    components: {
      EateryCard,
    },
    watch: {
      "$store.state.currentSearch": "findEatery",
      "$store.state.category": "findEatery",
    },
    methods: {
      findEatery() {
        this.restaurants = [];
        this.currentResponse = [];
        this.start = 0;
        this.end = 8;
        let search = `${this.$store.state.currentSearch}&categories=${this.$store.state.category}`;
        RestaurantService.getRestaurants(search).then((response) => {
          this.currentResponse = response;
          this.updateRestaurants();
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
  };
  </script>
  
  <style scoped>
  .eatery-grid-container {
    display: grid;
    grid-template-columns: 1fr;
    justify-items: center;
    width: 95%;
  }
  </style>
  