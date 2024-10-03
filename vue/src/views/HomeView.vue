<template>
  <div class="home">
    <div class="category-container">
      <div 
        class="category" 
        v-for="category in categories" 
        :key="category"
      >
        {{ category }}
      </div>
    </div>
    <EateryFinderFormVue v-on:submit.prevent="findEatery" />
    <EateryList :restaurants="restaurants" />
  </div>
</template>

<script>
import EateryFinderFormVue from "../components/eatery_components/EateryFinderForm.vue";
import EateryList from "../components/eatery_components/EateryList.vue";

export default {
  data() {
    return {
      restaurants: [],
      categories: [
        "Restaurants",
        "Bars",
        "Coffee & Tea",
        "Bakeries",
        "Pizza",
        "Sushi Bars",
        "Vegan",
        "Ice Cream & Frozen Yogurt",
        "Burgers",
        "Seafood",
      ],
    };
  },
  components: {
    EateryFinderFormVue,
    EateryList,
  },
  methods: {
    findEatery() {
      this.restaurants = [];
      this.$store.commit("TOGGLE_RESTAURANTS", true);
    },
  },
};
</script>

<style scoped>
.home {
  display: grid;
  justify-items: center;
  align-content: start;
  height: 100vh;
}
.category-container {
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  gap: 10px;
  justify-items: center;
  align-content: start;
  margin-top: 20px;
}

.category {
  font-size: 1.2em;
  color: white;
  background-color: rgba(41, 41, 173, 0.8);
  margin: 0;
}
</style>
