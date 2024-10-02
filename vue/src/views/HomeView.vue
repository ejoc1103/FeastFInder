<template>
  <div class="home">
    <EateryFinderFormVue v-on:submit.prevent="findEatery()" />
    <div v-if="$store.state.showRestaurants" class="eatery-grid-container">
      <EateryCard :restaurants="restaurants" />
      <button v-on:click="nextPage">Next Card</button>
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
      currentResponse: [],
      isLoading: false,
      start: 0,
      end: 8,
    };
  },
  components: {
    EateryFinderFormVue,
    EateryCard,
  },
  methods: {
    findEatery() {
      this.restaurants = [];
      this.currentResponse = [];
      let search = this.$store.state.currentSearch;
      RestaurantService.getRestaurants(search).then((response) => {
        console.log(response.data);
        this.currentResponse = response;
        for(let i = this.start; i < this.end; i++) {
          this.restaurants.push(response.data[i]);
        }
      });
      this.$store.commit("TOGGLE_RESTAURANTS", true);
    },
    nextPage(){
      console.log(this.currentResponse.data.length);
      this.start += 8;
      this.end += 8;
      this.restaurants = [];
      for(let i = this.start; i < this.end; i++) {
          this.restaurants.push(this.currentResponse.data[i]);
        }
    },
    }
  }



</script>
<style scoped>
.home {
  display: grid;
  justify-items: center;
  align-content: start;
  height: 100%;
}

.eatery-grid-container {
  display: grid;
  grid-template-columns: 1fr;
  justify-items: center;
  width: 95%;
}
</style>
